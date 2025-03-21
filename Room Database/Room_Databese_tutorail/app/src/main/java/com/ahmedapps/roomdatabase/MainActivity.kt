package com.ahmedapps.roomdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.ahmedapps.roomdatabase.data.AppDatabase
import com.ahmedapps.roomdatabase.data.AppPreferences
import com.ahmedapps.roomdatabase.data.FirestoreManager
import com.ahmedapps.roomdatabase.presentation.StudentScreen
import com.ahmedapps.roomdatabase.metier.StudentViewModel
import com.ahmedapps.roomdatabase.metier.StudentViewModelFactory
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        val database = AppDatabase.getDatabase(this)
        val studentDao = database.studentDao()
        val appPreferences = AppPreferences(this)
        val firestoreManager = FirestoreManager()
        val viewModelFactory = StudentViewModelFactory(studentDao, appPreferences, firestoreManager)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(StudentViewModel::class.java)

        setContent {
            MaterialTheme {
                Surface {
                    StudentScreen(viewModel)
                }
            }
        }
    }
}