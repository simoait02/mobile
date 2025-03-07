package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"l'Activitee est cree",Toast.LENGTH_SHORT).show()
        Log.i("onCreate(): ","l'Activitee est cree")
    }

    override fun onStart(){
        super.onStart()
        Toast.makeText(this,"l'Activitee es demaree",Toast.LENGTH_SHORT).show()
        Log.i("onStart(): ","l'Activitee est demaree")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"l'Activitee es redemaree",Toast.LENGTH_SHORT).show()
        Log.i("onRestart(): ","l'Activitee est redemaree")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"l'Activitee est reprise",Toast.LENGTH_SHORT).show()
        Log.i("onResume(): ","l'Activitee est reprise")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this,"l'Activitee est en pause",Toast.LENGTH_SHORT).show()
        Log.i("onPause(): ","l'Activitee est en pause")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this,"l'Activitee est stopee",Toast.LENGTH_SHORT).show()
        Log.i("onStop(): ","l'Activitee est stopee")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"l'Activitee est detruite",Toast.LENGTH_SHORT).show()
        Log.i("onDestroy(): ","l'Activitee est detruite")
    }
}