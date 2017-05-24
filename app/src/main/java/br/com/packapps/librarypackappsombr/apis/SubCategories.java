package br.com.packapps.librarypackappsombr.apis;

import br.com.packapps.librarypackappsombr.utils.Const;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by paulolinhares on 24/05/17.
 */

public interface SubCategories {

    @GET(""+ Const.SUB_CATEGORIES)
    Call<Object> getSubCategories();

}
