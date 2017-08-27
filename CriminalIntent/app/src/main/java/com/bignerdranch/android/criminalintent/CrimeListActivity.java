package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

import com.bignerdranch.android.com.bignerdranch.android.Utils.SingleFragmentActivity;

/**
 * Created by kchinnak on 8/25/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
