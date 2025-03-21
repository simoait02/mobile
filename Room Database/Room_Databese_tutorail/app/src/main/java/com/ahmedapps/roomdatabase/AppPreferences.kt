package com.ahmedapps.roomdatabase.data

import android.content.Context
import androidx.core.content.edit

class AppPreferences(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    var passingGrade: Double
        get() = sharedPreferences.getFloat("passing_grade", 10.0f).toDouble()
        set(value) = sharedPreferences.edit { putFloat("passing_grade", value.toFloat()) }

    var sortOrder: String
        get() = sharedPreferences.getString("sort_order", "asc") ?: "asc"
        set(value) = sharedPreferences.edit { putString("sort_order", value) }
}