package br.com.packapps.librarypackappsombr.controllers;

import android.app.Activity;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paulolinhares on 24/05/17.
 */

public class ControllerRetrofit {
    private static final String URL_BASE = "http://google.com/";

    public static Retrofit initRetrofit(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
