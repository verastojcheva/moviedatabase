package com.example.moviedatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        // Set title
        holder.titleTextView.setText(movie.getTitle());

        // Set year
        holder.yearTextView.setText(movie.getYear() > 0
                ? String.valueOf(movie.getYear())
                : "Year Unknown");

        // Set genre
        holder.genreTextView.setText(movie.getGenre());

        // Set poster (with placeholder)
        int posterResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(movie.getPosterResourceId(), "drawable",
                        holder.itemView.getContext().getPackageName());

        holder.posterImageView.setImageResource(
                posterResourceId > 0
                        ? posterResourceId
                        : R.drawable.default_movie_poster
        );
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView yearTextView;
        TextView genreTextView;
        ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movie_title);
            yearTextView = itemView.findViewById(R.id.movie_year);
            genreTextView = itemView.findViewById(R.id.movie_genre);
            posterImageView = itemView.findViewById(R.id.movie_poster);
        }
    }
}