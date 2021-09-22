package com.example.flixster.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ViewGroup container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) { // Bind movie data to view
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);

            // 1. Register clickListener on whole row
            container.setOnClickListener(view -> {
//                Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("movie", Parcels.wrap(movie));
                context.startActivity(i);
            });
        }
    }

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        // Inflate: Render XML code into actual UI by Android OS
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Populates movie data into view through viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder");
        Movie movie = movies.get(position); // Get the movie at the passed in position
        holder.bind(movie); // Bind movie data to view in view holder
    }

    // Return number of movies
    @Override
    public int getItemCount() {
        return movies.size();
    }


}
