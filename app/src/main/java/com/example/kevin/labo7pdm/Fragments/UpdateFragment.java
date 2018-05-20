package com.example.kevin.labo7pdm.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kevin.labo7pdm.DBUtils.DBHelper;
import com.example.kevin.labo7pdm.MainActivity;
import com.example.kevin.labo7pdm.R;

public class UpdateFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Actualizar una nota");

        // Aquí se infla el layout para el fragmento
        final View view = inflater.inflate(R.layout.update_layout, container, false);

        // Ahora se obtienen referencias del heap
        final EditText editTextCarnet = view.findViewById(R.id.editTextUpdate);
        final EditText editTextNuevaNota = view.findViewById(R.id.editTextNuevaNota);
        final Button updateButton = view.findViewById(R.id.updateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carnet = editTextCarnet.getText().toString();
                String nota = editTextNuevaNota.getText().toString();

                DBHelper.ourInstance.editarRegistro(carnet, nota);
                DBHelper.ourInstance.cargarRegistros();
            }
        });

        return view;
    }

}
