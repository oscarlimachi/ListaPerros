package com.example.listaperros

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    //como llamamos a la api para utilizarlo
    @GET
    fun getDogsByBreeds(@Url url: String) : Response<DogsResponse>

}