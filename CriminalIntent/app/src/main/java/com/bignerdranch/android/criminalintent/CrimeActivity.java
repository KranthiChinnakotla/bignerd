package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bignerdranch.android.com.bignerdranch.android.Utils.SingleFragmentActivity;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static String TAG = "com.bignerdranch.android.crimeid";

    @Override
    protected Fragment createFragment() {
        UUID crime_id = (UUID) getIntent().getSerializableExtra(TAG);
        return CrimeFragment.newInstance(crime_id);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(TAG,crimeId);
        return intent;
    }
}
