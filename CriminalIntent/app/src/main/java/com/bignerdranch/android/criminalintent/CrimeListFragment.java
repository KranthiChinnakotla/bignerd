package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.model.Crime;
import com.bignerdranch.android.model.CrimeLab;

import java.util.List;

/**
 * Created by kchinnak on 8/25/2017.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CrimeAdapter mCrimeAdapter;
    private TextView mTextViewTitle, mTextViewDate;
    private ImageView mImageView_Solved;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUi();
        return v;
    }


    private class CrimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;

        public void bind(Crime crime) {
            mCrime = crime;
            mTextViewTitle.setText(mCrime.getTitle());
            mTextViewDate.setText(mCrime.getDate().toString());
            mImageView_Solved.setVisibility(mCrime.isSolved() ? View.VISIBLE:View.GONE);

        }


        public CrimeViewHolder(LayoutInflater inflater, ViewGroup parent) {


            super(inflater.inflate(R.layout.row_list_item, parent, false));
            mTextViewTitle = (TextView) itemView.findViewById(R.id.row_CrimeTitle);
            mTextViewDate = (TextView) itemView.findViewById(R.id.row_CrimeDate);
            mImageView_Solved = (ImageView) itemView.findViewById(R.id.ic_solved_imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
    }
/*
    private class SeriousCrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;

        public void bind(Crime crime) {
            mCrime = crime;
            mTextViewTitle_Ser.setText(mCrime.getTitle());
            mTextViewDate_Ser.setText(mCrime.getDate().toString());
        }

        public SeriousCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.row_list_item_ser, parent, false));
            mTextViewDate_Ser = (TextView) itemView.findViewById(R.id.ser_row_CrimeDate);
            mTextViewTitle_Ser = (TextView) itemView.findViewById(R.id.ser_row_CrimeTitle);
            mButtonPolice = (Button) itemView.findViewById(R.id.ser_row_PoliceButton);
            mButtonPolice.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(view.getId() == mButtonPolice.getId()){
                Toast.makeText(getActivity(),"Call police immediately, this is a serious "+mCrime.getTitle()+" commite on "+mCrime.getDate().toString()+" .",Toast.LENGTH_LONG).show();
            }else if(view.getId() == itemView.getId()){
                Toast.makeText(getActivity(), "The " + mCrime.getTitle() + " is clicked on " + mCrime.getDate() + ".", Toast.LENGTH_LONG).show();
            }

        }
    }*/

    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new CrimeViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Crime crime = mCrimes.get(position);
                    CrimeViewHolder crimeViewHolder = (CrimeViewHolder) holder;
                    crimeViewHolder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    private void updateUi() {
        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimesList();
        if(mCrimeAdapter == null){
            mCrimeAdapter = new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(mCrimeAdapter);
        }else{
            mCrimeAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mCrimeAdapter);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();

    }
}
