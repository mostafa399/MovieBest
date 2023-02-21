package com.mostafahelal.moviebest.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mostafahelal.moviebest.models.MovieModel;
import com.mostafahelal.moviebest.request.MovieClientApi;

import java.util.List;

public class MovieRepository {

private MovieClientApi movieClientApi;
    public static MovieRepository instance;
    private String mQuery;
    private int mPageNumber;

    public MovieRepository() {
       movieClientApi=MovieClientApi.getInstance();
    }

    public static MovieRepository getInstance() {
        if (instance==null){
            instance=new MovieRepository();
        }
        return instance;
    }
    public LiveData<List<MovieModel>>getMovies(){
        return movieClientApi.getMovies() ;
    }
    public LiveData<List<MovieModel>>getPopularMovies(){
        return movieClientApi.getPopularMovies() ;
    }
    public void searchMovieApi(String query,int pageNumber){
        mQuery=query;
        mPageNumber=pageNumber;
        movieClientApi.searchMovieApi(query,pageNumber);

    }
    public void searchMoviePopular(int pageNumber){

        mPageNumber=pageNumber;
        movieClientApi.searchMoviePopular(pageNumber);

    }
    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }
}
