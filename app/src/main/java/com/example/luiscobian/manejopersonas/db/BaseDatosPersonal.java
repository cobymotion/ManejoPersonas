package com.example.luiscobian.manejopersonas.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luiscobian on 5/30/17.
 */

public class BaseDatosPersonal {


    private static final List<Personal> personal = new ArrayList<>();

    public static void addPersonal(Personal p)
    {
        personal.add(p);
    }

    public static  List<Personal> getLista() {
        return personal;
    }



}
