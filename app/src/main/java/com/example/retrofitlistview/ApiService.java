package com.example.retrofitlistview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    public String urlbase = "http://10.0.2.2:80/JSONARRAYRETROFIT/";

    @GET ("Productos.json")
    Call<List<Producto>> listarCatalogo();
}
