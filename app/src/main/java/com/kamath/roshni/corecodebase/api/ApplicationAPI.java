package com.kamath.roshni.corecodebase.api;

import com.kamath.roshni.corecodebase.api.syncmodels.MovieResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mac on 12/10/16.
 */

public interface ApplicationAPI {

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
