package com.alexis_dubus.nibral2;

/**
 * Created by Alexis on 15/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Core extends SQLiteOpenHelper {
    public static final String DATABASE_NAME_OLD = "Animation.db";
    public static final String DATABASE_NAME = "Nibral.db";
    public static final String TABLE_NAME = "Utilisateur";
    public static final String idUser = "id";
    public static final String nomUser = "nom";
    public static final String telUser = "tel";
    public static final String mailUser = "mail";
    public static final String listeReservationUser = "listeReservation";
    public static final String typeUtilisateur = "typeUtilisateur";
    //public static final String COL_4 = "mail";

    public Core(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getReadableDatabase(); //pour obtenir une bdd lisible sur pc
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, tel TEXT, mail TEXT, listeReservation TEXT, typeUtilisateur TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME); //remove ?????
        onCreate(db);
    }
    public boolean insertFirstData(String name, String mail)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nomUser, name);
        contentValues.put(mailUser, mail);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertData(String name, String type, String classes)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nomUser, name);
        contentValues.put(telUser, type);
        contentValues.put(mailUser, classes);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public Cursor getAllDataSup() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME +" WHERE CLASSES == 9 OR CLASSES == 8 OR CLASSES == 7", null);
        return res;
        //en test
    }
    public Cursor getAllDataInf() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME +" WHERE CLASSES == 0 OR CLASSES == 1 OR CLASSES == 2 OR CLASSES == 3", null);
        return res;
        //en test
    }

    public boolean updateData(String id, String name, String type, String classes){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(idUser, id);
        contentValues.put(nomUser, name);
        contentValues.put(telUser, type);
        contentValues.put(mailUser, classes);
        db.update(TABLE_NAME, contentValues, "ID = ?", new  String[] { id } );
        return true;
    }

    public Cursor getNbrand()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME +" ", null);

        int Max = 10;
        int Min = 1;
        int nombreAleatoire = Min + (int)(Math.random() * ((Max - Min) + 1));
        return res;
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
