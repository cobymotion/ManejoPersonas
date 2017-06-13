package com.example.luiscobian.manejopersonas;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.luiscobian.manejopersonas.db.BaseDatosPersonal;
import com.example.luiscobian.manejopersonas.db.Personal;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarFragment extends Fragment {


    View vista;
    EditText nombre;
    EditText telefono ;

    public AgregarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_agregar, container, false);

        nombre = (EditText) vista.findViewById(R.id.txtNombre);
        telefono = (EditText) vista.findViewById(R.id.txtTelefono);

        FloatingActionButton boton = (FloatingActionButton)
                                 vista.findViewById(R.id.boton_add);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personal per = new Personal(nombre.getText().toString(),
                                            telefono.getText().toString());
                BaseDatosPersonal.addPersonal(per,getContext());
                Snackbar.make(vista,"Se agrego el personal",Snackbar.LENGTH_LONG)
                        .show();
            }
        });

        return vista;
    }

}
