package com.example.kevin.labo7pdm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kevin.labo7pdm.MainActivity;
import com.example.kevin.labo7pdm.R;

public class AddFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.add_layout, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Fragment Inbox");

        return view;
    }


}
