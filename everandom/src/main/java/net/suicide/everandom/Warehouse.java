package net.suicide.everandom;

import android.content.ContentValues;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Warehouse extends SQLiteOpenHelper {  
    private static final int DATABASE_VERSION = 1;  
    private static final String DATABASE_NAME = "bookshelf";  
    private static final String TABLE_NOTES = "notes";  
    private static final String KEY_ID = "id";  
    private static final String KEY_TITLE= "title";  
    private static final String KEY_CONTENT= "content";

    Context ctx;
  
    public Warehouse(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
        //3rd argument to be passed is CursorFactory instance
    }  
  
    // Creating Tables  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("  
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"  
                + KEY_CONTENT + " TEXT" + ")";  
        db.execSQL(CREATE_NOTES_TABLE);  
    }  
  
    // Upgrading database  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        // Drop older table if existed  
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);  
  
        // Create tables again  
        onCreate(db);  
    }  
  
    // code to add the new note  
    void insert(String title, String content) {  
        SQLiteDatabase db = this.getWritableDatabase();  
  
        ContentValues values = new ContentValues();  
        values.put(KEY_TITLE, title); // Contact Name  
        values.put(KEY_CONTENT, content); // Contact Phone  
  
        // Inserting Row  
        db.insert(TABLE_NOTES, null, values);  
        //2nd argument is String containing nullColumnHack  
        db.close(); // Closing database connection  
    }  
  
    // code to get the single note  
    Map<String, Object> selectOne(int id) {  
        SQLiteDatabase db = this.getReadableDatabase();  
  
        Cursor cursor = db.query(TABLE_NOTES, new String[] { KEY_ID,  KEY_TITLE, KEY_CONTENT }, KEY_ID + "=?",  
                new String[] { String.valueOf(id) }, null, null, null, null);  
        if (cursor != null)  
            cursor.moveToFirst();
  
        // return note  
        return cursor2map(cursor);
    }
    Map<String, Object> selectOne(String title) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTES, new String[] { KEY_ID,  KEY_TITLE, KEY_CONTENT }, KEY_TITLE + "=?",
                new String[] { String.valueOf(title) }, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();

        // return note
        return cursor2map(cursor);
    }

    // code to get all notes in a list view  
    public List<Map<String, Object>> selectAll() {  
        List<Map<String, Object>> noteList = new ArrayList<Map<String, Object>>();  
        // Select All Query  
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;  
  
        SQLiteDatabase db = this.getWritableDatabase();  
        Cursor cursor = db.rawQuery(selectQuery, null);  
  
        // looping through all rows and adding to list  
        if (cursor.moveToFirst()) {  
            do {  
                // Adding note to list
                noteList.add(cursor2map(cursor));
            } while (cursor.moveToNext());  
        }  
  
        // return note list  
        return noteList;  
    }  

    public List<Map<String, Object>> random() throws IOException {
        List<Map<String, Object>> noteList = new ArrayList<>();
        // Select All Query  
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES + " WHERE id IN (SELECT id FROM " + TABLE_NOTES + " ORDER BY RANDOM() LIMIT " + Appliance.prop("limit",ctx) + ")";
  
        SQLiteDatabase db = this.getWritableDatabase();  
        Cursor cursor = db.rawQuery(selectQuery, null);  
  
        // looping through all rows and adding to list  
        if (cursor.moveToFirst()) {  
            do {  
                // Adding note to list
                noteList.add(cursor2map(cursor));
            } while (cursor.moveToNext());  
        }  
  
        // return note list  
        return noteList;
    }

    Map<String, Object> cursor2map(Cursor cursor){
         Map<String, Object> note= new HashMap<String, Object>();
         note.put("id", Integer.parseInt(cursor.getString(0)));
         note.put("title", cursor.getString(1));
         note.put("content", cursor.getString(2));
         return note;
    }
  
    // code to update the single note  
    public int updateContact(int id, String title, String content) {  
        SQLiteDatabase db = this.getWritableDatabase();  
  
        ContentValues values = new ContentValues();  
        values.put(KEY_TITLE, title);  
        values.put(KEY_CONTENT, content);  
  
        // updating row  
        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",  new String[] { String.valueOf(id) });  
    }  
  
    // Deleting single note  
    public void delete(int id) {  
        SQLiteDatabase db = this.getWritableDatabase();  
        db.delete(TABLE_NOTES, KEY_ID + " = ?",  new String[] { String.valueOf(id) });  
        db.close();  
    }  
  
    // Getting notes Count  
    public int count() {  
        String countQuery = "SELECT  1 FROM " + TABLE_NOTES;  
        SQLiteDatabase db = this.getReadableDatabase();  
        Cursor cursor = db.rawQuery(countQuery, null);  
        cursor.close();  
  
        // return count  
        return cursor.getCount();  
    }  
  
}  
