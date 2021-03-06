package com.example.kevin.labo7pdm.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevin.labo7pdm.Adapters.RecyclerViewAdapter;
import com.example.kevin.labo7pdm.DBUtils.DBHelper;
import com.example.kevin.labo7pdm.DBUtils.Registro;
import com.example.kevin.labo7pdm.MainActivity;
import com.example.kevin.labo7pdm.R;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Añadir un registro");

        // Aquí se infla el layout para el fragmento
        final View view = inflater.inflate(R.layout.add_layout, container, false);

        // Ahora se obtienen referencias del heap
        final EditText editTextCarnet = view.findViewById(R.id.editTextCarnet);
        final EditText editTextNota = view.findViewById(R.id.editTextNota);
        final EditText editTextMateria = view.findViewById(R.id.editTextMateria);
        final EditText editTextCat = view.findViewById(R.id.editTextCat);
        final Button addButton = view.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carnet = editTextCarnet.getText().toString();
                String nota = editTextNota.getText().toString();
                String materia = editTextMateria.getText().toString();
                String docente = editTextCat.getText().toString();

                Registro r = new Registro(carnet, nota, materia, docente);

                DBHelper.ourInstance.add(r);
                Toast.makeText(getContext(), "Insertado con éxito", Toast.LENGTH_SHORT).show();
                DBHelper.cargarRegistros();
                Log.d("Test", " "+DBHelper.ourInstance.getCurrentList().size());
            }
        });

        return view;
    }



}
