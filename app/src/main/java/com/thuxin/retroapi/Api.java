package com.thuxin.retroapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    String BASE_URL = "https://myheroapi.herokuapp.com/myapi/apiheroes/";

    @GET("?format=json")
    Call<List<Hero>> getHeroes();
}
