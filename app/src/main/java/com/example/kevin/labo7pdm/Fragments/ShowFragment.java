package com.example.kevin.labo7pdm.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kevin.labo7pdm.Adapters.RecyclerViewAdapter;
import com.example.kevin.labo7pdm.DBUtils.DBHelper;
import com.example.kevin.labo7pdm.DBUtils.Registro;
import com.example.kevin.labo7pdm.MainActivity;
import com.example.kevin.labo7pdm.R;

import java.util.ArrayList;

public class ShowFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Registro> mRegistros;
    private RecyclerViewAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Mostrar registros");

        // Aquí se infla el layout para el fragmento
        final View view = inflater.inflate(R.layout.show_layout, container, false);

        mRecyclerView =  (RecyclerView) view.findViewById(R.id.registroRecycleView);
        recyclerAdapter = new RecyclerViewAdapter(getContext(), mRegistros);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recyclerAdapter);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegistros = DBHelper.ourInstance.getCurrentList();
        recyclerAdapter = new RecyclerViewAdapter(getContext(), mRegistros);
    }

}
