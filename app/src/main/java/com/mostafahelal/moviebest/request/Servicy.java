package com.mostafahelal.moviebest.request;

import com.mostafahelal.moviebest.utils.Credintals;
import com.mostafahelal.moviebest.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicy {
    private static Retrofit.Builder retrofitBuilder=new Retrofit.Builder().baseUrl(Credintals.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit=retrofitBuilder.build();
    private static MovieApi movieApi=retrofit.create(MovieApi.class);
    public static MovieApi getMovieApi(){
        return movieApi;
    }
}
