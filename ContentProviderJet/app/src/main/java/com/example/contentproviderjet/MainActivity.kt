package com.example.contentproviderjet

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.*
import androidx.room.Insert
import androidx.room.Room
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
    fun getAll(): List<Student>
}

@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

class StudentViewModel(context: Context) : ViewModel() {
    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "students.db").build()
    private val studentDao = db.studentDao()
    private val firestore = FirebaseFirestore.getInstance()
    var students by mutableStateOf(listOf<Student>())
        private set

    init {
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
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel: StudentViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StudentViewModel(context) as T
        }
    })

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
            MainScreen()
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun UserInputScreenPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel = StudentViewModel(context)
    UserInputScreen(navController, viewModel)
}
