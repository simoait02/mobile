package com.ahmedapps.roomdatabase.metier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModelProvider
import com.ahmedapps.roomdatabase.data.AppPreferences
import com.ahmedapps.roomdatabase.data.FirestoreManager
import com.ahmedapps.roomdatabase.data.Student
import com.ahmedapps.roomdatabase.data.StudentDao

class StudentViewModelFactory(
    private val studentDao: StudentDao,
    private val appPreferences: AppPreferences,
    private val firestoreManager: FirestoreManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(studentDao, appPreferences, firestoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class StudentViewModel(
    private val studentDao: StudentDao,
    private val appPreferences: AppPreferences,
    private val firestoreManager: FirestoreManager
) : ViewModel() {
    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    init {
        viewModelScope.launch {
            studentDao.getAllStudents().collect { students ->
                _students.value = students.sortedBy { it.grade }
            }
            syncWithFirestore()
        }
    }

    private suspend fun syncWithFirestore() {
        val firestoreStudents = firestoreManager.getAllStudents()
        firestoreStudents.forEach { student ->
            if (!_students.value.any { it.id == student.id }) {
                studentDao.insert(student)
            }
        }
    }

    fun addStudent(student: Student) {
        viewModelScope.launch {
            studentDao.insert(student)
            firestoreManager.addStudent(student)
        }
    }

    fun getPassingGrade(): Double {
        return appPreferences.passingGrade
    }

    fun setPassingGrade(grade: Double) {
        appPreferences.passingGrade = grade
        updatePassingStatusForAllStudents(grade)
    }

    private fun updatePassingStatusForAllStudents(passingGrade: Double) {
        viewModelScope.launch {
            val updatedStudents = _students.value.map { student ->
                val isPassing = student.grade >= passingGrade
                student.copy(isPassing = isPassing)
            }
            updatedStudents.forEach { student ->
                studentDao.updatePassingStatus(student.id, student.isPassing)
                firestoreManager.updateStudent(student)
            }
            _students.value = updatedStudents
        }
    }

    fun getSortOrder(): String {
        return appPreferences.sortOrder
    }

    fun setSortOrder(order: String) {
        appPreferences.sortOrder = order
        viewModelScope.launch {
            studentDao.getAllStudents().collect { students ->
                _students.value = if (order == "asc") {
                    students.sortedBy { it.grade }
                } else {
                    students.sortedByDescending { it.grade }
                }
            }
        }
    }
}