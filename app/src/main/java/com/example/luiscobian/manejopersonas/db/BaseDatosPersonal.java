package com.example.luiscobian.manejopersonas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.luiscobian.manejopersonas.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luiscobian on 5/30/17.
 */

public class BaseDatosPersonal {


    private static final List<Personal> personal = new ArrayList<>();

    public static void addPersonal(Personal p, Context context)
    {
        EstructuraHelper dbHelper = new EstructuraHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put("nombre",p.getNombre());
        args.put("telefono",p.getTelefono());
        db.insert("persona",null, args);
        Log.i("Estado BD", "Se guardo correctamente");
        personal.add(p);
    }

    public static  List<Personal> getLista(Context context) {
        EstructuraHelper dbHelper = new EstructuraHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Personal> datos = new ArrayList<>();

        // columnas que me quiero traer
        String columns[] = {"nombre","telefono"};
        Cursor c = db.query(
                "persona",     // nombre de la tabla
                columns,     // columnas que me quiero traer
                null,       // sentencia del where
                null,       // valores para el where en caso de parametros
                null,       // sentecia de Group By
                null,       // filtros que se aplican
                "nombre ASC"  // ordenamiento
        );

        // Cursor c = db.rawQuery("SELECT * FROM persona",null);

        // para recorrer
        if(c.moveToFirst())
        {
            do {
                String nombre  = c.getString(0);
                String telefono = c.getString(1);
                datos.add(new Personal(nombre, telefono));
            } while(c.moveToNext());
        }

        return datos;
    }



}
