package com.mostafahelal.moviebest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mostafahelal.moviebest.models.MovieModel;

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movieModel;

    public MovieModel getMovieModel() {
        return movieModel;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movieModel=" + movieModel +
                '}';
    }
}
