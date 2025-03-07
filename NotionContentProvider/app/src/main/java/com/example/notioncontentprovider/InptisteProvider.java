package com.example.notioncontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;

public class InptisteProvider extends ContentProvider {

    // Define constants
    static final String PROVIDER_NAME = "com.example.notioncontentprovider.provider";
    static final String URL = "content://" + PROVIDER_NAME + "/inptistes";
    static final Uri CONTENT_URI = Uri.parse(URL);

    // Define column names
    static final String _ID = "id";
    static final String NOM = "nom";
    static final String NOTE = "note";

    private static HashMap<String, String> INPTISTES_PROJECTION_MAP;
    static final int INPTISTES = 1;
    static final int INPTISTE_ID = 2;

    // UriMatcher to match URIs
    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "inptistes", INPTISTES);
        uriMatcher.addURI(PROVIDER_NAME, "inptistes/#", INPTISTE_ID);
    }

    // Database related constants
    private SQLiteDatabase db;
    static final String DATABASE_NAME = "INPT";
    static final String INPTISTES_TABLE_NAME = "inptistes";
    static final int DATABASE_VERSION = 1;

    // SQL statement to create the database table
    static final String CREATE_DB_TABLE =
            "CREATE TABLE " + INPTISTES_TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOM + " TEXT NOT NULL, " +
                    NOTE + " TEXT NOT NULL);";

    // SQLiteOpenHelper to manage database creation and version management
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + INPTISTES_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Validate URI
        if (uriMatcher.match(uri) != INPTISTES) {
            throw new IllegalArgumentException("URI invalide pour insertion: " + uri);
        }

        // Insert into database
        long rowID = db.insert(INPTISTES_TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        } else {
            throw new SQLException("Échec d'ajout d'enregistrement dans " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Match URI to determine query
        switch (uriMatcher.match(uri)) {
            case INPTISTES:
                qb.setProjectionMap(INPTISTES_PROJECTION_MAP);
                qb.setTables(INPTISTES_TABLE_NAME);
                break;
            case INPTISTE_ID:
                qb.appendWhere(_ID + " = " + uri.getPathSegments().get(1));
                qb.setTables(INPTISTES_TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("URI inconnu: " + uri);
        }

        if (TextUtils.isEmpty(sortOrder)) {
            sortOrder = NOM;
        }

        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        // Match URI to determine delete action
        switch (uriMatcher.match(uri)) {
            case INPTISTES:
                count = db.delete(INPTISTES_TABLE_NAME, selection, selectionArgs);
                break;
            case INPTISTE_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(INPTISTES_TABLE_NAME, _ID + " = " + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI inconnu: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        // Match URI to determine update action
        switch (uriMatcher.match(uri)) {
            case INPTISTES:
                count = db.update(INPTISTES_TABLE_NAME, values, selection, selectionArgs);
                break;
            case INPTISTE_ID:
                count = db.update(INPTISTES_TABLE_NAME, values, _ID + " = " + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI inconnu: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case INPTISTES:
                return "vnd.android.cursor.dir/vnd.example.inptistes";
            case INPTISTE_ID:
                return "vnd.android.cursor.item/vnd.example.inptistes";
            default:
                throw new IllegalArgumentException("Type MIME non supporté pour URI: " + uri);
        }
    }
}
