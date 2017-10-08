package com.bignerdranch.android.com.bignerdranch.android.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Prathyusha on 9/23/17.
 */

public class CrimeDbhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CrimesDb";
    private static final int VERSION = 1;

    public CrimeDbhelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("create table " + CrimeDbSchema.NAME + "( _id integer primary key autoincrement, " +
        CrimeDbSchema.Cols.UUID+" , "+CrimeDbSchema.Cols.DATE+" , "+CrimeDbSchema.Cols.TITLE+" , "+CrimeDbSchema.Cols.SOLVED+", "+ CrimeDbSchema.Cols.SUSPECT+ " )");

        Log.d(DATABASE_NAME,"create table " + CrimeDbSchema.NAME + "( _id integer primary key autoincrement, " +
                CrimeDbSchema.Cols.UUID+" , "+CrimeDbSchema.Cols.DATE+" , "+CrimeDbSchema.Cols.TITLE+" , "+CrimeDbSchema.Cols.SOLVED+", "+ CrimeDbSchema.Cols.SUSPECT+ " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
