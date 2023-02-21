package com.mostafahelal.moviebest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mostafahelal.moviebest.models.MovieModel;
import com.mostafahelal.moviebest.repository.MovieRepository;

import java.util.List;

public class MovieListViewmodel extends ViewModel {
    private final MovieRepository movieRepository;

    public MovieListViewmodel() {
        movieRepository=MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }
    public LiveData<List<MovieModel>> getPopularMovies() {
        return movieRepository.getPopularMovies();
    }
    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query,pageNumber);
    }
    public void searchPopularMovie(int pageNumber){
        movieRepository.searchMoviePopular(pageNumber);
    }
    public void searchNextPAge(){
        movieRepository.searchNextPage();
    }
}
