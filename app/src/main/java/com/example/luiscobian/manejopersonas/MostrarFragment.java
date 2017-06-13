package com.example.luiscobian.manejopersonas;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarFragment extends Fragment {


    RecyclerView rv;


    public MostrarFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_mostrar, container, false);

        rv = (RecyclerView) view.findViewById(R.id.listaPersonal);

        AdaptadorRecycler arc = new AdaptadorRecycler(getContext());

        arc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Posicion click", "P:" + rv.getChildAdapterPosition(v));
                View v1 = rv.getChildAt(rv.getChildAdapterPosition(v));
                TextView txtTelefono = (TextView)v1.findViewById(R.id.vistaTxtTelefono);
                String telefono = txtTelefono.getText().toString();
                Log.i("Telefono", telefono);

                Intent i = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + telefono));

                if(ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},225);
                    return;
                }

                startActivity(i);
            }
        });

        rv.setAdapter(arc);


        rv.hasFixedSize();
        LinearLayoutManager llm  = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        return view;
    }

}
