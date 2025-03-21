package com.example.contentprovider

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val note: String
)

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student)

    @Query("SELECT * FROM student ORDER BY note DESC")
    suspend fun getAll(): List<Student>
}

@Database(entities = [Student::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Application): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "students.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}



class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val studentDao = db.studentDao()
    private lateinit var firestore: FirebaseFirestore
    var students by mutableStateOf(listOf<Student>())
        private set

    init {
        FirebaseApp.initializeApp(application)
        firestore = FirebaseFirestore.getInstance()
        loadStudents()
    }

    fun addStudent(name: String, email: String, note: String) {
        val student = Student(name = name, email = email, note = note)
        viewModelScope.launch {
            studentDao.insert(student)
            firestore.collection("students").add(student)
            loadStudents()
        }
    }

    private fun loadStudents() {
        viewModelScope.launch {
            students = studentDao.getAll()
        }
    }
}

class StudentViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun UserInputScreen(navController: NavController, viewModel: StudentViewModel) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Ajouter une note", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nom") }, modifier = Modifier.fillMaxWidth())
        TextField(value = note, onValueChange = { note = it }, label = { Text("Note") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = { viewModel.addStudent(name, email, note) }, modifier = Modifier.fillMaxWidth()) { Text("Ajouter Note") }
        Button(onClick = { navController.navigate("notes_screen") }, modifier = Modifier.fillMaxWidth()) { Text("Afficher les notes") }
    }
}

@Composable
fun NotesScreen(viewModel: StudentViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(viewModel.students) { student ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Nom: ${student.name}", fontWeight = FontWeight.Bold)
                    Text("Email: ${student.email}")
                    Text("Note: ${student.note}")
                }
            }
        }
    }
}

@Composable
fun MainScreen(application: Application) {
    val navController = rememberNavController()
    val viewModel: StudentViewModel = viewModel(factory = StudentViewModelFactory(application))

    NavHost(navController = navController, startDestination = "user_input_screen") {
        composable("user_input_screen") { UserInputScreen(navController, viewModel) }
        composable("notes_screen") { NotesScreen(viewModel) }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            MainScreen(application)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInputScreenPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext as Application
    val viewModel = StudentViewModel(context)
    UserInputScreen(navController, viewModel)
}
