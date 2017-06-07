package br.com.packapps.applicationfortest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.com.packapps.librarypackappsombr.apis.models.TaxonomiesResponse;
import br.com.packapps.librarypackappsombr.cache.Cache;
import br.com.packapps.librarypackappsombr.config.ApiClient;
import br.com.packapps.librarypackappsombr.models.Config;
import br.com.packapps.librarypackappsombr.models.Taxonomy;
import br.com.packapps.librarypackappsombr.utils.SharedPreferencesHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Config config;
    private final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadAndParseConfig();

    }

    private void loadAndParseConfig() {
        //Gson gson = new GsonBuilder().registerTypeAdapter(Config.class, new ConfigDeserilializer()).create();
        Call<Config> call = ApiClient.getInstance().getApiService().getConfig();
        call.enqueue(new Callback<Config>() {
            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "config received");
                    config = response.body();
                    Log.i(TAG, "config home checksum: "+ config.getHomeChecksum());

                    //getTaxonomyData();
                } else {
                    int statusCode = response.code();
                    Log.d(TAG, "Config data not received. Error code : " + statusCode);
                    ResponseBody errorBody = response.errorBody();
                    //handleError();
                }
            }

            @Override
            public void onFailure(Call<Config> call, Throwable t) {
                Log.d(TAG, "config data request failed");
                //handleError();
            }
        });
    }

    /*
    private void loadAndParseConfig() {
        Call<Config> call = ApiClient.getInstance().getApiService().getConfig();
        call.enqueue(new Callback<Config>() {

            @Override
            public void onResponse(Response<Config> response, Retrofit retrofit) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "config received");
                    config = response.body();

                    //getTaxonomyData();
                } else {
                    int statusCode = response.code();
                    Log.d(TAG, "Config data not received. Error code : " + statusCode);
                    ResponseBody errorBody = response.errorBody();
                    //handleError();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "config data request failed");
                //handleError();
            }
        });
    }*/




}
