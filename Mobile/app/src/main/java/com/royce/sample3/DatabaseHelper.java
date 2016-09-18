package com.royce.sample3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by johnroycepunay on 9/18/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase db;


    public static final String DATABASE_NAME = "Treelife.db";
    public static final String TABLE_NAME = "User_Account";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FIRST_NAME";
    public static final String COL_3 = "MIDDLE_NAME";
    public static final String COL_4 = "LAST_NAME";
    public static final String COL_5 = "EMAIL";
    public static final String COL_6 = "USERNAME";
    public static final String COL_7 = "PASSWORD";

    public static final String TABLE_TREE = "Tree_Specie";
    public static final String Tree_ID = "TREE_ID";
    public static final String Tree_Name = "TREE_NAME";
    public static final String Tree_age = "TREE_AGE";
    public static final String Tree_diameter = "TREE_DIAMETER";
    public static final String Status = "STATUS";
    public static final String created_at = "DATE";
    public static final String Tree_Circumference = "TREE_CIRCUMFERENCE";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();

    }

    public DatabaseHelper open() throws SQLException {
        db = this.getWritableDatabase();
        return this;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_2 + " TEXT," +
                COL_3 + " TEXT," +
                COL_4 + " TEXT," +
                COL_5 + " TEXT," +
                COL_6 + " TEXT," +
                COL_7 + " TEXT" + ")";
        db.execSQL(SQL_String);

        String SQL_STRING_TREE_TABLE = "CREATE TABLE " + TABLE_TREE + "(" +
                Tree_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Tree_Name + " TEXT," +
                Tree_age + " TEXT," +
                Tree_diameter + " TEXT, " +
                Status + " TEXT," +
                created_at + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                Tree_Circumference + " TEXT" + ")";
        db.execSQL(SQL_STRING_TREE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_TREE);
        onCreate(db);


    }

    public void insertData(String Fname, String Mname, String Lname, String Email, String Username, String Password) {

        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Fname);
        contentValues.put(COL_3, Mname);
        contentValues.put(COL_4, Lname);
        contentValues.put(COL_5, Email);
        contentValues.put(COL_6, Username);
        contentValues.put(COL_7, Password);
        db.insert(TABLE_NAME, null, contentValues);

    }

    public void insertTree(String treeName, double treeAge, double diameter, String status, double circumference) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date date = new Date();
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tree_Name, treeName);
        contentValues.put(Tree_age, treeAge);
        contentValues.put(Tree_diameter, diameter);
        contentValues.put(Status, status);
        contentValues.put(created_at,  dateFormat.format(date));
        contentValues.put(Tree_Circumference,circumference);
        db.insert(TABLE_TREE, null, contentValues);

    }

    public Cursor getTreeData(){
        Cursor res= getReadableDatabase().rawQuery("SELECT * from " + TABLE_TREE, null);
        return res;
    }

    public String getSingleEntry(String USERNAME) {
        db = this.getWritableDatabase();
        String query = "Select USERNAME, PASSWORD from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String editTextUsername, editTextPassword;
        editTextPassword = "not found";
        if (cursor.moveToFirst()) {
        }
        do {
            editTextUsername = cursor.getString(0);


            if (editTextUsername.equals(USERNAME)) {
                editTextPassword = cursor.getString(1);
                break;
            }
        }
        while (cursor.moveToNext());
        return editTextPassword;


    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;

    }
    public String Exist(String user) {
        String username="";
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor c = db.query(TABLE_NAME, null, COL_6 + "=?", new String[]{String.valueOf(user)},null, null, null);

            if (c == null) {
                return username;
            }
            else {
                c.moveToFirst();
                username = c.getString(c.getColumnIndex(COL_6));
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return username;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TREE, "TREE_ID = ?",new String[] {id});

    }
    public void deleteAllData(){

        db.execSQL("delete from "+ TABLE_TREE);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name = '" + TABLE_TREE + "'");
    }


}


