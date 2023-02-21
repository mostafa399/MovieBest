package com.mostafahelal.moviebest.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafahelal.moviebest.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imageView;
    RatingBar ratingBar;

    OnMovieListenner onMovieListenner;

    public MovieViewHolder(@NonNull View itemView,OnMovieListenner onMovieListenner) {
        super(itemView);
        this.onMovieListenner=onMovieListenner;
        imageView=itemView.findViewById(R.id.movie_img);
        ratingBar=itemView.findViewById(R.id.rating_bar);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieListenner.onMovieClick(getAdapterPosition());


    }
}
