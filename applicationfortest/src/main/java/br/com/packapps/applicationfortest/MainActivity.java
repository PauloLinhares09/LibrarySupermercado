package br.com.packapps.applicationfortest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        Call<Config> call = ApiClient.getInstance().getApiService().getConfig();
        call.enqueue(new Callback<Config>() {
            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                if (response.isSuccessful()) {
                    config = response.body();
                    Log.d(TAG, "config received");
                    Log.i(TAG, "config home checksum: "+ config.getHomeChecksum());

                    getTaxonomyData();
                } else {
                    int statusCode = response.code();
                    Log.d(TAG, "Config data not received. Error code : " + statusCode);
                    ResponseBody errorBody = response.errorBody();
                    handleError();
                }
            }

            @Override
            public void onFailure(Call<Config> call, Throwable t) {
                Log.d(TAG, "config data request failed");
                handleError();
            }
        });
    }


    /**
     * get Categories / subcategories, etc
     */
    private void getTaxonomyData() {
        Cache cache = Cache.get();
        if (config.getTaxonomiesChecksum().equals(cache.getTaxonomiesChecksum())) { // if taxonomy data has not changed

            //getDashboardData();
            goToHome();
            return;
        }

        Call<TaxonomiesResponse> call = ApiClient.getInstance().getApiService().getAllTaxonomies();
        call.enqueue(new Callback<TaxonomiesResponse>() {
            @Override
            public void onResponse(Call<TaxonomiesResponse> call, Response<TaxonomiesResponse> response) {
                Log.d(TAG, "Taxonomies data request succeeded");
                if (response.isSuccessful()) {
                    Log.d(TAG, "Taxonomies received");
                    TaxonomiesResponse taxonomiesResponse = response.body();
                    List<Taxonomy> taxonomies = taxonomiesResponse.getTaxonomies();
                    Cache cache = Cache.get();
                    cache.getCachedTaxonomies().clear();
                    cache.getCachedTaxonomies().addAll(taxonomies);
                    cache.setTaxonomiesChecksum(taxonomiesResponse.getChecksum());
                    SharedPreferencesHelper.saveCache(cache);

                    //getDashboardData();
                    goToHome();
                } else {
                    int statusCode = response.code();
                    Log.d(TAG, "Taxonomies not received. Error code : " + statusCode);
                    ResponseBody errorBody = response.errorBody();
                    Log.d(TAG, "Tax Error : " + errorBody.toString());
                    handleError();
                }
            }

            @Override
            public void onFailure(Call<TaxonomiesResponse> call, Throwable t) {
                Log.d(TAG, "Taxonomies data request failed: "+t.getMessage());
                handleError();
            }
        });

    }

    private void handleError() {
        Toast.makeText(this, "Erro connection Api", Toast.LENGTH_SHORT).show();
    }

    private void goToHome() {
        //progressBar.setVisibility(View.GONE);
        Intent openHome = new Intent(MainActivity.this, MainActivity.class);
        startActivity(openHome);
        finish();
    }


}
