package br.com.geoambiente.engine;

import br.com.geoambiente.domain.impl.Screenshot;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface DribbbleAPI {
    @GET("shots?sort=views")
    Call<List<Screenshot>> getPopular(
            @Query("page") Integer page,
            @Query("per_page") Integer quantityPerPage,
            @Query("access_token") String accessToken
    );
}
