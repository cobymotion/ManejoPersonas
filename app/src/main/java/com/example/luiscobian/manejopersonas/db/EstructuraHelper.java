package com.example.luiscobian.manejopersonas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luiscobian on 6/13/17.
 */

public class EstructuraHelper extends SQLiteOpenHelper {

    public final static String NOMBRE_BASE_DATOS = "persona.db";
    public final static int VERSION_BASE_DATOS = 1;
    public final static String SQL_CREAR_BASE_DATOS =
            "CREATE TABLE persona (nombre text, telefono text)";
    public final static String SQL_LIMPIAR_BASE_DATOS =
            "DROP TABLE IF EXIST persona ";

    public EstructuraHelper(Context context){
        super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR_BASE_DATOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_LIMPIAR_BASE_DATOS);
        onCreate(db);
    }
}
