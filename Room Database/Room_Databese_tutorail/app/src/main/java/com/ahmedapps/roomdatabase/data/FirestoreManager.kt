package com.ahmedapps.roomdatabase.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class FirestoreManager {
    private val firestore = FirebaseFirestore.getInstance()
    private val studentsCollection = firestore.collection("students")

    suspend fun addStudent(student: Student) {
        studentsCollection.add(student).await()
    }

    suspend fun getAllStudents(): List<Student> {
        val result = studentsCollection.get().await()
        return result.toObjects(Student::class.java)
    }
    suspend fun updateStudent(student :Student){
        studentsCollection.document(student.id.toString())
            .set(student)
            .await()
    }


}