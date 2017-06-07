package br.com.packapps.librarypackappsombr.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import br.com.packapps.librarypackappsombr.apis.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by paulolinhares on 07/06/17.
 */

public class ApiClient {
    public static final String HEADER_API_TOKEN = "X-Spree-Token";
    private static ApiClient apiClient;

    private ApiService apiService;
    private ApiService sessionApiService;

    public static ApiClient getInstance() {
        if(apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                //.client(client)
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(ApiService.class);

        Retrofit sessionRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.client(createRequestInterceptorClient())
                .build();

        sessionApiService = sessionRetrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

}
