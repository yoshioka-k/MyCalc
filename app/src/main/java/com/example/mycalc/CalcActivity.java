package com.example.mycalc;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalcActivity extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Activity parentctivity = getActivity();
        View view = inflater.inflate(R.layout.fragment_calc_activity, container, false);
        return view;
    }
}