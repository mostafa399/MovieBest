package com.mostafahelal.moviebest.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mostafahelal.moviebest.AppExecutor;
import com.mostafahelal.moviebest.models.MovieModel;
import com.mostafahelal.moviebest.repository.MovieRepository;
import com.mostafahelal.moviebest.response.MovieSearchResponse;
import com.mostafahelal.moviebest.utils.Credintals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieClientApi {
    //
    //LiveData For Search
    private MutableLiveData<List<MovieModel>> mMovie;
    //Making Global Runnable
    private RetriveMovieRunnable retriveMovieRunnable;


    //
    //LiveData For Popular Movie
    private MutableLiveData <List<MovieModel>>mMoviesPopular;
    //Making Global PopularMovie Runnable
    private RetriveMoviePopularRunnable retriveMovieRunnablePopular;

    //
    //instance Singleton
    public static MovieClientApi instance;
    public static MovieClientApi getInstance() {
        if (instance==null){
            instance=new MovieClientApi();
        }
        return instance;
    }

    //
    //Constructor
    public MovieClientApi() {
        mMovie=new MutableLiveData<>();
        mMoviesPopular=new MutableLiveData<>();
    }



    public LiveData<List<MovieModel>> getMovies() {
        return mMovie;
    }
    public LiveData<List<MovieModel>> getPopularMovies() {
        return mMoviesPopular;
    }

    //Call through classes
    public void searchMovieApi(String query,int pageNumber){

        if (retriveMovieRunnable!=null){
            retriveMovieRunnable=null;
        }
        retriveMovieRunnable=new RetriveMovieRunnable(query,pageNumber);

        //Retrieve Data From Api
        final Future myHandler= AppExecutor.getInstance().networkIo().submit(retriveMovieRunnable);

       //Set TimeOut or cancelling the retrofit call
        AppExecutor.getInstance().networkIo().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the Retrofit
                myHandler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);
    }
    public void searchMoviePopular(int pageNumber){

        if (retriveMovieRunnablePopular!=null){
            retriveMovieRunnablePopular=null;
        }
        retriveMovieRunnablePopular=new RetriveMoviePopularRunnable(pageNumber);

        //Retrieve Data From Api
        final Future myHandler2= AppExecutor.getInstance().networkIo().submit(retriveMovieRunnablePopular);

        //Set TimeOut or cancelling the retrofit call
        AppExecutor.getInstance().networkIo().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancelling the Retrofit
                myHandler2.cancel(true);
            }
        },1000, TimeUnit.MILLISECONDS);
    }


    //Retrieve data from RestApi by Runnable class
    //2query Response Types id & search
    private class RetriveMovieRunnable implements Runnable{
        private final String query;
        private final int pageNumber;
        boolean cancelRequest;

        public RetriveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest=false;
        }

        @Override
        public void run() {
            //get Response objects
            try {
                Response response=getMovies(query,pageNumber).execute();
                if (cancelRequest){
                    return;
                }
                if (response.code()==200){
                    List<MovieModel>list=new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if (pageNumber==1){
                        //Sending Data To Live Data
                        //PostValue used For background thread
                        //setValue not used for background thread
                        mMovie.postValue(list);
                    }else {
                        List<MovieModel>currentList=mMovie.getValue();
                        currentList.addAll(list);
                        mMovie.postValue(currentList);
                    }
                }else {
                    String error=response.errorBody().string();
                    Log.v("Tag","Error Code "+error);
                    mMovie.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovie.postValue(null);
            }



        }
        //Search Method/Query
        private Call<MovieSearchResponse>getMovies(String query,int pageNumber){
                return Servicy.getMovieApi().searchMovie(Credintals.API_KEY
                        ,query
                        ,pageNumber);

            }
        private void cancelRequest(){
            Log.v("Tag","Calling Search Request");
            cancelRequest=true;
        }

        }
    private class RetriveMoviePopularRunnable implements Runnable{

        private final int pageNumber;
        boolean cancelRequest;
        public RetriveMoviePopularRunnable( int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest=false;
        }

        @Override
        public void run() {
            //get Response objects
            try {
                Response response2=getPopularMovies(pageNumber).execute();
                if (cancelRequest){
                    return;
                }
                if (response2.code()==200){
                    List<MovieModel>list=new ArrayList<>(((MovieSearchResponse)response2.body()).getMovies());
                    if (pageNumber==1){
                        //Sending Data To Live Data
                        //PostValue used For background thread
                        //setValue not used for background thread
                        mMoviesPopular.postValue(list);
                    }else {
                        List<MovieModel>currentList=mMoviesPopular.getValue();
                        currentList.addAll(list);
                        mMoviesPopular.postValue(currentList);
                    }
                }else {
                    String error=response2.errorBody().string();
                    Log.v("Tag","Error Code "+error);
                    mMoviesPopular.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMoviesPopular.postValue(null);
            }



        }
        //Search Method/Query
        private Call<MovieSearchResponse>getPopularMovies(int pageNumber){
            return Servicy.getMovieApi().getPopular(
                    Credintals.API_KEY
                    ,pageNumber);

        }
        private void cancelRequest(){
            Log.v("Tag","Calling Search Request");
            cancelRequest=true;
        }

    }
    }
