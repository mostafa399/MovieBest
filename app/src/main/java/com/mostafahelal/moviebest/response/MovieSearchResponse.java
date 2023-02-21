package com.mostafahelal.moviebest.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mostafahelal.moviebest.models.MovieModel;

import java.util.List;

public class MovieSearchResponse {
    @SerializedName("results")
    @Expose
    private List<MovieModel>movieModels;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public MovieSearchResponse(List<MovieModel> movieModels, Integer totalResults) {
        this.movieModels = movieModels;
        this.totalResults = totalResults;
    }

    public List<MovieModel> getMovies() {
        return movieModels;
    }

    public void setMovieModels(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @NonNull
    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "movieModels=" + movieModels +
                ", totalResults=" + totalResults +
                '}';
    }
}
