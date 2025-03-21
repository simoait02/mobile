package com.ahmedapps.roomdatabase.presentation

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun BroadcastReceiverApp() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val intent = Intent("com.data.CUSTOM_INTENT")
                intent.setPackage(context.packageName)
                context.sendBroadcast(intent)
                Toast.makeText(context, "Intent Sent!", Toast.LENGTH_SHORT).show()
                Log.d("StaticReceiver", "sent")
            }
        ) {
            Text("Broadcast Intent")
        }
    }
}