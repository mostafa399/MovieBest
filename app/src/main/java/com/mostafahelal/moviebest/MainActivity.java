package com.mostafahelal.moviebest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.SearchView;

import android.view.View;
import android.widget.Toolbar;

import com.mostafahelal.moviebest.adapter.MovieRecyclerView;
import com.mostafahelal.moviebest.adapter.OnMovieListenner;
import com.mostafahelal.moviebest.models.MovieModel;
import com.mostafahelal.moviebest.viewmodel.MovieListViewmodel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMovieListenner {
   private RecyclerView recyclerView;
   private MovieRecyclerView adapter;


private MovieListViewmodel movieListViewmodel;
boolean isPopular=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setUpSearchView();



        //ViewModel
        movieListViewmodel=new ViewModelProvider(this).get(MovieListViewmodel.class);
        recyclerView=findViewById(R.id.recyclerView);

        setRecyclerView();
        observeAnyDataChange();
        observePopularMovie();
        //getting popular movies
        movieListViewmodel.searchPopularMovie(1);

    }

    private void observePopularMovie() {
        movieListViewmodel.getPopularMovies().observe(this, movieModels -> {
            if (movieModels!=null){
                for (MovieModel model:movieModels){
                    Log.v("Tagg","ON CHANGE "+model.getTitle());
                    adapter.setMovieModels(movieModels);
                }
            }

        });
    }


    //Creating An Observer
    private void observeAnyDataChange(){
        movieListViewmodel.getMovies().observe(this, movieModels -> {
            if (movieModels!=null){
                for (MovieModel model:movieModels){
                    Log.v("Tagg","ON CHANGE "+model.getTitle());
                    adapter.setMovieModels(movieModels);
                }
            }

        });
    }

    private void setRecyclerView(){
        adapter=new MovieRecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);


        //RecyclerView Paging
        //Loading next page of api response
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)){
                    //need to Show next search result on the next page of api
                    movieListViewmodel.searchNextPAge();

                }
            }
        });
    }
    private void setUpSearchView() {
        final SearchView searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewmodel.searchMovieApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchClickListener(v -> isPopular=false);
        searchView.setOnCloseListener(() -> {
            //do what you want  searchview is not expanded
            return false;
        });
    }
    private void setSupportActionBar(Toolbar myToolbar) {

    }
    @Override
    public void onMovieClick(int position) {
       // Toast.makeText(this, "the position "+position, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,MovieDetailActivity.class);
        intent.putExtra("movie",adapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }



}