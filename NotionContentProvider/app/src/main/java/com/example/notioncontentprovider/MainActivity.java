package com.example.notioncontentprovider;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Ajouter une note d'étudiant.
    // @param view Vue déclencheuse de l'événement.
    public void onClickAjouterNoteInptiste(View view) {
        // Préparer les valeurs pour l'insertion
        ContentValues values = new ContentValues();
        values.put(InptisteProvider.NOM, ((EditText) findViewById(R.id.nom)).getText().toString());
        values.put(InptisteProvider.NOTE, ((EditText) findViewById(R.id.note)).getText().toString());

        // Insérer les données dans le ContentProvider
        Uri uri = getContentResolver().insert(InptisteProvider.CONTENT_URI, values);

        if (uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
    }

    // Récupérer et afficher les notes des étudiants
    // @param view Vue déclencheuse de l'événement.
    public void onClickRetrouverNotesInptistes(View view) {
        // Définir l'URI du ContentProvider
        String URL = "content://com.example.notioncontentprovider.provider/inptistes";
        Uri inptistes = Uri.parse(URL);

        // Récupérer les données depuis le ContentProvider
        Cursor cursor = getContentResolver().query(inptistes, null, null, null, "nom");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(InptisteProvider._ID));
                String nom = cursor.getString(cursor.getColumnIndexOrThrow(InptisteProvider.NOM));
                String note = cursor.getString(cursor.getColumnIndexOrThrow(InptisteProvider.NOTE));

                Toast.makeText(this, "ID: " + id + "\nNom: " + nom + "\nNote: " + note, Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "Aucune donnée trouvée.", Toast.LENGTH_LONG).show();
        }
    }
}
