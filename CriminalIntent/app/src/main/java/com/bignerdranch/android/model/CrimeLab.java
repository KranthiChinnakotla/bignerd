package com.bignerdranch.android.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kchinnak on 8/25/2017.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();

    /*    for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            if (i % 2 == 0) {
                crime.setSolved(true);
                crime.setRequiresPolice(false);
            } else {
                crime.setSolved(false);
                crime.setRequiresPolice(true);
            }

            mCrimes.add(crime);
        }*/
    }

    public static CrimeLab getCrimeLab(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimesList() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }

        return null;
    }


}
