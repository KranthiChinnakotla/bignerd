package com.bignerdranch.android.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.com.bignerdranch.android.Database.CrimeCursorWrapper;
import com.bignerdranch.android.com.bignerdranch.android.Database.CrimeDbSchema;
import com.bignerdranch.android.com.bignerdranch.android.Database.CrimeDbhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kchinnak on 8/25/2017.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;
    private SQLiteDatabase mSQLiteDatabase;
    private Context mContext;

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mSQLiteDatabase.insert(CrimeDbSchema.NAME, null, values);
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mSQLiteDatabase.update(CrimeDbSchema.NAME, values, CrimeDbSchema.Cols.UUID + " = ? ", new String[]{uuidString});
    }

    private CrimeLab(Context context) {

        mContext = context.getApplicationContext();
        mSQLiteDatabase = new CrimeDbhelper(mContext).getWritableDatabase();

        mCrimes = new ArrayList<>();


    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.Cols.UUID, crime.getId().toString());
        values.put(CrimeDbSchema.Cols.DATE, crime.getDate());
        values.put(CrimeDbSchema.Cols.TITLE, crime.getTitle());
        values.put(CrimeDbSchema.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeDbSchema.Cols.SUSPECT, crime.getSuspect());
        return values;
    }


    public static CrimeLab getCrimeLab(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimesList() {
        mCrimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Crime crime = cursor.getCrime();
                mCrimes.add(crime);
                cursor.moveToNext();
            }

        }finally {
            cursor.close();
        }

        return  mCrimes;
    }

    public CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(CrimeDbSchema.NAME, null, whereClause, whereArgs, null, null, null);
        return new CrimeCursorWrapper(cursor);
    }




    public Crime getCrime(UUID id) {

        CrimeCursorWrapper cursor = queryCrimes(CrimeDbSchema.Cols.UUID+" = ? ",new String[]{id.toString()});

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();

        }finally {
            cursor.close();
        }

    }


}
