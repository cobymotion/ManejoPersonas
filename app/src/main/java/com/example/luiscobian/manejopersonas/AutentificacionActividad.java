package com.example.luiscobian.manejopersonas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AutentificacionActividad extends AppCompatActivity {

    EditText txtNombre;
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentificacion_actividad);

        txtNombre = (EditText) findViewById(R.id.editText);
        txtPass = (EditText) findViewById(R.id.editText2);

        FloatingActionButton boton = (FloatingActionButton)
                findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String pass = txtPass.getText().toString();
                if(pass.equals(MainActivity.pass))
                {
                    // Si es verdadero
                    SharedPreferences preferences = getSharedPreferences("acceso",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("estado",true);
                    editor.putString("nombre", nombre);
                    editor.commit();
                    Intent i = new Intent(AutentificacionActividad.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();


                } else {
                    // Si es falso
                    Snackbar.make(v ,"El password es incorrecto",
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
}
