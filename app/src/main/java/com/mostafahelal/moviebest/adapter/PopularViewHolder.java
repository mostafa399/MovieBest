package com.mostafahelal.moviebest.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mostafahelal.moviebest.R;

public class PopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    OnMovieListenner onMovieListenner;
    ImageView imageView_pop;
    RatingBar ratingBar_pop;

    public PopularViewHolder(@NonNull View itemView,OnMovieListenner onMovieListenner) {
        super(itemView);
        this.onMovieListenner=onMovieListenner;
        imageView_pop=itemView.findViewById(R.id.movie_img_popualar);
        ratingBar_pop=itemView.findViewById(R.id.rating_bar_pop);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
