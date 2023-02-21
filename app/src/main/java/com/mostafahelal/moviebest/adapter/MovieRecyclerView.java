package com.mostafahelal.moviebest.adapter;

import android.net.Credentials;
import android.net.wifi.hotspot2.pps.Credential;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mostafahelal.moviebest.R;
import com.mostafahelal.moviebest.models.MovieModel;
import com.mostafahelal.moviebest.utils.Credintals;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel>movieModels;
    private final OnMovieListenner onMovieListenner;
    private static final int DISPLAY_POPULAR=1;
    private static final int DISPLAY_SEARCH=2;

    public MovieRecyclerView(OnMovieListenner onMovieListenner) {
        this.onMovieListenner = onMovieListenner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType==DISPLAY_SEARCH){
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
            return new MovieViewHolder(view,onMovieListenner);
        }else {
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_list_item,parent,false);
            return new PopularViewHolder(view,onMovieListenner);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        MovieModel model=movieModels.get(position);
        if (viewType == DISPLAY_SEARCH){

            // vote average is over 10, and our rating bar is over 5 stars: dividing by 2
            ((MovieViewHolder)holder).ratingBar.setRating((float) ((model.getVoteAverage())/2));

            // ImageView: Using Glide Library
            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/" +model.getPosterPath())
                    .into(((MovieViewHolder)holder).imageView);

        }else{
           ((PopularViewHolder)holder).ratingBar_pop.setRating((float) ((model.getVoteAverage())/2));

            // ImageView: Using Glide Library
            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/"
                            +model.getPosterPath())
                    .into(((PopularViewHolder)holder).imageView_pop);

        }
    }



    @Override
    public int getItemCount() {
        if (movieModels!=null) {
            return movieModels.size();
        }else return 0;
    }

    public void setMovieModels(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }
    public MovieModel getSelectedMovie(int position){
        if (movieModels!=null){
            if (movieModels.size()>0){
                return movieModels.get(position);
            }
        }
        return null;
    }
    @Override
    public int getItemViewType(int position) {
        if (Credintals.POPULAR){
            return DISPLAY_POPULAR;
        }
        else
            return DISPLAY_SEARCH;
    }

}
