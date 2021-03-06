package com.example.kevin.labo7pdm.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setHasOptionsMenu(true);
        mRegistros = DBHelper.ourInstance.getCurrentList();
        recyclerAdapter = new RecyclerViewAdapter(getContext(), mRegistros);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search,menu);

        final MenuItem searchItem = menu.findItem(R.id.item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint("Ingrese un carnet");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerAdapter.filter(query);
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mRegistros.clear();
        mRegistros.addAll(recyclerAdapter.getOriginal());

    }

}
