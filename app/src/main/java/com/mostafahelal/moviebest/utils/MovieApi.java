package com.mostafahelal.moviebest.utils;

import com.mostafahelal.moviebest.models.MovieModel;
import com.mostafahelal.moviebest.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //Search for Movie
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("/3/search/movie")
     Call<MovieSearchResponse>searchMovie(
            @Query("api_key")String key
            ,@Query("query") String query
            ,@Query("page") int page
    );
    //GetPopularMovie
    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse>getPopular(
            @Query("api_key")String api_key,
            @Query("page")int page
    );


    //Search for Movie id
    //https://api.themoviedb.org/3/movie/550?api_key=9d6b83cdeefe320b2324b61f8adbf75b
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel>getMovie(@Path("movie_id")int movie_id,
                             @Query("api_key")String api_key
    );
}
