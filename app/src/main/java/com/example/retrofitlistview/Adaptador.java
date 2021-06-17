package com.example.retrofitlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    Context miContexto;
    ArrayList<Producto> miLista;

    public Adaptador(Context miContexto, ArrayList<Producto> miLista) {
        this.miContexto = miContexto;
        this.miLista = miLista;
    }

    @Override
    public int getCount() {
        return miLista.size();
    }

    @Override
    public Object getItem(int i) {
        return miLista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return miLista.get(i).getId();
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        LayoutInflater inflador = LayoutInflater.from(miContexto);
        view = inflador.inflate(R.layout.itemlistview, null);

        //TextView idproducto = (TextView) view.findViewById(R.id.idProducto);
        TextView nombre = (TextView) view.findViewById(R.id.tvNombre);
        //TextView precio = (TextView) view.findViewById(R.id.tvPrecio);
        //TextView descripcion = (TextView) view.findViewById(R.id.tvDescripcion);

        //idproducto.setText(miLista.get(pos).getId() + "");
        nombre.setText(miLista.get(pos).getNombre());
        //precio.setText(miLista.get(pos).getPrecio());
        //descripcion.setText(miLista.get(pos).getDescripcion());

        return view;
    }
}
