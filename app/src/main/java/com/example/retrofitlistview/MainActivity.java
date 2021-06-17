package com.example.retrofitlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.retrofitlistview.ApiService.urlbase;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Adaptador adaptador;
    ArrayList<Producto> lista;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        lista = new ArrayList<Producto>();
        adaptador = new Adaptador(getApplicationContext(), lista);
        lv.setAdapter(adaptador);


        //RETROFIT

        obtenerDatos();


    }

    private void obtenerDatos() {
        retrofit = new Retrofit.Builder()
                .baseUrl(urlbase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService servicio = retrofit.create(ApiService.class);
        Call<List<Producto>> listado = servicio.listarCatalogo();
        listado.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> catalogo = response.body();
                    for (int i = 0; i < catalogo.size(); i++) {
                        Producto pr = new Producto(catalogo.get(i).getId(), catalogo.get(i).getNombre(), catalogo.get(i).getPrecio(), catalogo.get(i).getDescripcion());
                        lista.add(pr);
                    }
                    adaptador.notifyDataSetChanged();

                } else {
                    Log.e("ERROR", "No ha funcionado");
                }


            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("Error", t.getMessage());

            }
        });

    }

    /*private void obtenerDatos() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Service.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service servicio = retrofit.create(Service.class);
        Call<List<Producto>> requescatalog = servicio.listarCatalogo();
        requescatalog.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (!response.isSuccessful()) {
                    Log.e("ERROR", "Ha fallado el servicio");
                } else {

                    List<Producto> listado = response.body();
                    //Log.e("error", response.body().toString());
                    for(int i=0; i<listado.size(); i++){
                        //Log.e("error", listado.get(i).getNombre() );

                        Producto pr = new Producto(listado.get(i).getId(), listado.get(i).getNombre(), listado.get(i).getPrecio(), listado.get(i).getDescripcion());

                        lista.add(pr);
                    }
                    adaptador.notifyDataSetChanged();



                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());

            }
        });
    }*/
}