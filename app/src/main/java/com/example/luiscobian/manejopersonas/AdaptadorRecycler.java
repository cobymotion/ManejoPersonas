package com.example.luiscobian.manejopersonas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luiscobian.manejopersonas.db.BaseDatosPersonal;
import com.example.luiscobian.manejopersonas.db.Personal;

import java.util.List;

/**
 * Created by luiscobian on 5/30/17.
 */

public class AdaptadorRecycler extends
        RecyclerView.Adapter<AdaptadorRecycler.ViewPersonal> {

    private List<Personal> lista;


    public AdaptadorRecycler() {
        this.lista = BaseDatosPersonal.getLista();
    }

    @Override
    public ViewPersonal onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.personal_view,parent,false);
        ViewPersonal viewPersonal = new ViewPersonal(v);
        return viewPersonal;
    }

    @Override
    public void onBindViewHolder(ViewPersonal holder, int position) {

        Personal personal = lista.get(position);
        holder.txtNombre.setText(personal.getNombre());
        holder.txtTelefono.setText(personal.getTelefono());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }



    public static class ViewPersonal extends RecyclerView.ViewHolder{
        TextView txtNombre;
        TextView txtTelefono;

        public ViewPersonal(View itemView)
        {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.vistaTxtNombre);
            txtTelefono = (TextView) itemView.findViewById(R.id.vistaTxtTelefono);
        }

    }
}
