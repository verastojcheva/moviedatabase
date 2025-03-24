package com.example.moviedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movie_recycler_view);
        errorTextView = findViewById(R.id.error_text_view);

        loadMovies();
    }

    private void loadMovies() {
        // Load movies from Raw resources
        List<Movie> movies = JSONUtility.loadMoviesFromRaw(this);

        if (movies.isEmpty()) {
            // Show error if no movies loaded
            recyclerView.setVisibility(View.GONE);
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText("No movies could be loaded. Please check the JSON file.");
        } else {
            // Setup RecyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            MovieAdapter adapter = new MovieAdapter(movies);
            recyclerView.setAdapter(adapter);

            recyclerView.setVisibility(View.VISIBLE);
            errorTextView.setVisibility(View.GONE);
        }
    }
}
