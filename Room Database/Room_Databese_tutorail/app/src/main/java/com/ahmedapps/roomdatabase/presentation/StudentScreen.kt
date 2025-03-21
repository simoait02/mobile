package com.ahmedapps.roomdatabase.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahmedapps.roomdatabase.data.Student
import com.ahmedapps.roomdatabase.metier.StudentViewModel

@Composable
fun StudentScreen(viewModel: StudentViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }
    var passingGrade by remember { mutableStateOf(viewModel.getPassingGrade().toString()) }
    var sortOrder by remember { mutableStateOf(viewModel.getSortOrder()) }

    Column(modifier = Modifier.padding(16.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Blue,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Blue,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )

        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = grade,
            onValueChange = { grade = it },
            label = { Text("Grade") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Blue,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val studentName = name
            val studentEmail = email
            val studentGrade = grade.toDoubleOrNull() ?: 0.0
            val student = Student(name = studentName, email = studentEmail, grade = studentGrade,isPassing=studentGrade>=10.0)
            viewModel.addStudent(student)
            name = ""
            email = ""
            grade = ""

        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
            Text("Add Student", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = passingGrade,
            onValueChange = { passingGrade = it },
            label = { Text("Passing Grade") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Blue,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val newPassingGrade = passingGrade.toDoubleOrNull()
            if (newPassingGrade != null) {
                viewModel.setPassingGrade(newPassingGrade)
                passingGrade= newPassingGrade.toString()
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
            Text("Set Passing Grade", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Text to display the current sort order
            Text("Sort Order: ${sortOrder}")

            // IconButton to toggle the sort order
            IconButton(
                onClick = {
                    val newOrder = if (sortOrder == "asc") "desc" else "asc"
                    viewModel.setSortOrder(newOrder)
                    sortOrder = newOrder
                },
                modifier = Modifier.size(48.dp) // Adjust the size of the IconButton
            ) {
                Icon(
                    imageVector = Icons.Default.Sort, // Use a sort icon (or any other icon)
                    contentDescription = "Toggle Sort Order",
                    tint = Color.Blue
                )
            }
        }
        LazyColumn {
            items(viewModel.students.value) { student ->
                StudentItem(student)
            }
        }
    }
}

@Composable
fun StudentItem(student: Student) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (student.isPassing) Color.Green.copy(alpha = 0.2f) else Color.Red.copy(alpha = 0.2f))
        ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${student.name}")
            Text(text = "Email: ${student.email}")
            Text(text = "Grade: ${student.grade}")
        }
    }
}