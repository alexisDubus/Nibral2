package com.alexis_dubus.nibral2;

/**
 * Created by Adrian Gandon on 16/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Etablissement extends SQLiteOpenHelper {
    public static final String DATABASE_NAME_OLD = "Animation.db";
    public static final String DATABASE_NAME = "Nibral2.db";
    public static final String TABLE_NAME = "Note";
    public static final String id = "id";
    public static final String lieu = "lieu";
    public static final String nbPlace = "nbPlace";
    public static final String prestation = "prestation";
    public static final String listeNotes = "listeNotes";
    public static final String listeReservations = "listeReservations";

    public Etablissement(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getReadableDatabase(); //pour obtenir une bdd lisible sur pc
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, lieu TEXT NULL, nbPlace TEXT NULL, prestation TEXT NULL, listeNotes TEXT NULL, listeReservations TEXT NULL)");
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

    public boolean updateData(String unId, String unLieu, String unNbPlace, String unePrestation, String uneListeNotes, String uneListeReservations){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id, unId);
        contentValues.put(lieu, unLieu);
        contentValues.put(nbPlace, unNbPlace);
        contentValues.put(prestation, unePrestation);
        contentValues.put(listeNotes, uneListeNotes);
        contentValues.put(listeReservations, uneListeReservations);
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
