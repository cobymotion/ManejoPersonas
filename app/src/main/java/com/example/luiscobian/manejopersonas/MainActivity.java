package com.example.luiscobian.manejopersonas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luiscobian.manejopersonas.db.BaseDatosPersonal;
import com.example.luiscobian.manejopersonas.db.Personal;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static String pass = "1234";
    private TextView nombreHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefe = getSharedPreferences("acceso",
                Context.MODE_PRIVATE);

        boolean acceso = prefe.getBoolean("estado",false);
        String nombre = prefe.getString("nombre","NE");

        if(!acceso) {
            Intent i = new Intent(this, AutentificacionActividad.class);
            startActivity(i);
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nombreHeader = (TextView) headerView.findViewById(R.id.header_nombre);
        nombreHeader.setText(nombre);

        navigationView.setNavigationItemSelectedListener(this);

        /// Cargar fragmento principal

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new PrincipalFragment();
        trans.add(R.id.contenedor, fragment);
        trans.commit();

        BaseDatosPersonal.addPersonal(new Personal("JOSE LUIS","34343423"));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        int id = item.getItemId();
        if (id == R.id.item_agregar) {
            fragment = new AgregarFragment();
        } else if (id == R.id.item_mostrar) {
            fragment = new MostrarFragment();
        } else if (id == R.id.item_eliminar) {
            Toast.makeText(this, "Eliminar",Toast.LENGTH_LONG).show();
        } else if (id == R.id.item_cerrar_sesion)
        {
            SharedPreferences sp = getSharedPreferences("acceso", Context.MODE_PRIVATE);
            SharedPreferences.Editor esp = sp.edit();
            esp.remove("estado");
            esp.remove("nombre");
            esp.commit();
            finish();
        }

        if(fragment != null ) {
            trans.replace(R.id.contenedor, fragment);
            trans.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
