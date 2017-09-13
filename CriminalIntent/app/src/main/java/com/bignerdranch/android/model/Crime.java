package com.bignerdranch.android.model;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by kchinnak on 8/21/2017.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private String mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;


    public Crime(){
        mId = UUID.randomUUID();
        Date date = new Date();
        SimpleDateFormat mDateFormat = new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss");
        mDate = mDateFormat.format(date);

    }

    public UUID getId() {
        return mId;
    }

    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        mRequiresPolice = requiresPolice;
    }

    public String getTitle() {

        return mTitle;

    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
