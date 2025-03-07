package com.example.notionservicejet

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notionservicejet.ui.theme.NotionServiceJetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotionServiceJetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Text Explicatif")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(paddingValues)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { demarrerService() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Démarrer Service")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(
                                onClick = { arreterService() },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Arrêter Service")
                            }
                        }
                    }

                }
            }
        }
    }

    // Start service
    private fun demarrerService() {
        startService(Intent(this, UnService::class.java))
    }

    // Stop service
    private fun arreterService() {
        stopService(Intent(this, UnService::class.java))
    }
}
