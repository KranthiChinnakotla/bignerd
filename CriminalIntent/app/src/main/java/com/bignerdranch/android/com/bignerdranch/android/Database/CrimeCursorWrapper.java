package com.bignerdranch.android.com.bignerdranch.android.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.model.Crime;

import java.util.UUID;

/**
 * Created by Prathyusha on 9/23/17.
 */

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String title = getString(getColumnIndex(CrimeDbSchema.Cols.TITLE));
        String uuidString = getString(getColumnIndex(CrimeDbSchema.Cols.UUID));
        String date = getString(getColumnIndex(CrimeDbSchema.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeDbSchema.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setDate(date);
        crime.setTitle(title);
        crime.setSolved(isSolved == 1 ? true : false);
        crime.setSuspect(suspect);
        return crime;
    }
}
