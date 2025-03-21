package com.example.broadcastrec;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activité principale pour démontrer l'utilisation d'un BroadcastReceiver.
 */
public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver monReceiver;

    /**
     * Appelée lorsque l'activité est créée.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Définir un filtre pour le BroadcastReceiver
        IntentFilter filtre = new IntentFilter();
        filtre.addAction("com.data.CUSTOM_INTENT");

        // Initialisation du BroadcastReceiver
        monReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Intent détecté", Toast.LENGTH_LONG).show();
                Log.d("tagReceived", "Intent reçu");
            }
        };

        // Enregistrer le BroadcastReceiver
        registerReceiver(monReceiver, filtre, Context.RECEIVER_NOT_EXPORTED);
    }

    /**
     * Méthode pour diffuser un intent particulier.
     */
    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.data.CUSTOM_INTENT");
        intent.setPackage(getPackageName()); // Cibler uniquement cette application
        sendBroadcast(intent);
        Log.d("tagBroadcast", "Broadcast envoyé");
    }

    /**
     * Nettoyer les ressources lorsque l'activité est détruite.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (monReceiver != null) {
            unregisterReceiver(monReceiver);
            monReceiver = null;
        }
    }
}