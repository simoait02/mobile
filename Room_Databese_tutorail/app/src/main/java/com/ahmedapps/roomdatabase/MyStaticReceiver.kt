package com.ahmedapps.roomdatabase

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyStaticReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Intent détecté (statique)", Toast.LENGTH_LONG).show()
        Log.d("StaticReceiver", "Intent reçu (statique)")
    }
}