package com.alexis_dubus.nibral2;

/**
 * Created by Adrian Gandon on 16/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Reservation extends SQLiteOpenHelper {
    public static final String DATABASE_NAME_OLD = "Animation.db";
    public static final String DATABASE_NAME = "Nibral2.db";
    public static final String TABLE_NAME = "Note";
    public static final String id = "id";
    public static final String date = "date";
    public static final String heure = "heure";
    public static final String etablissement = "etablissement";
    public static final String nbPersonne = "nbPersonne";

    public Reservation(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getReadableDatabase(); //pour obtenir une bdd lisible sur pc
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT NULL, heure TEXT NULL, etablissement TEXT NULL, nbPersonne TEXT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME); //remove ?????
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String unId, String uneDate, String uneHeure, String unEtablissement, String unNbPersonne){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id, unId);
        contentValues.put(date, uneDate);
        contentValues.put(heure, uneHeure);
        contentValues.put(etablissement, unEtablissement);
        contentValues.put(nbPersonne, unNbPersonne);
        db.update(TABLE_NAME, contentValues, "ID = ?", new  String[] { id } );
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public void onDeleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[]{"1"});
    }
}
