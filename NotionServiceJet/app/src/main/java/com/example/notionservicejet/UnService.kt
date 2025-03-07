package com.example.notionservicejet

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast

class UnService : Service() {

    companion object {
        private const val TAG = "MonService"
    }

    private var lecteur: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Le service a été créé")
        Toast.makeText(applicationContext, "Service créé", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Le service a été démarré")
        Toast.makeText(applicationContext, "Service démarré", Toast.LENGTH_LONG).show()

        lecteur = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        lecteur?.apply {
            isLooping = true
            start()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Le service a été détruit")
        Toast.makeText(applicationContext, "Service détruit", Toast.LENGTH_LONG).show()

        lecteur?.apply {
            stop()
            release()
        }
        lecteur = null
    }
}
