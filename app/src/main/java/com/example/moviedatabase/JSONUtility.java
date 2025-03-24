package com.example.moviedatabase;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONUtility {
    private static final String TAG = "JSONUtility";

    public static List<Movie> loadMoviesFromRaw(Context context) {
        List<Movie> movies = new ArrayList<>();

        try {
            // Read JSON file from raw resources
            InputStream is = context.getResources().openRawResource(R.raw.movies);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonString = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            is.close();

            JSONArray jsonArray = new JSONArray(jsonString.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject movieJson = jsonArray.getJSONObject(i);

                    // Extract and validate each field
                    String title = getStringOrDefault(movieJson, "title", null);
                    int year = getYearFromJson(movieJson);
                    String genre = getStringOrDefault(movieJson, "genre", null);
                    String poster = getStringOrDefault(movieJson, "poster", null);

                    // Create Movie object with built-in error handling
                    Movie movie = new Movie(title, year, genre, poster);
                    movies.add(movie);

                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing individual movie: " + e.getMessage());
                }
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error loading movies from JSON", e);
        }

        return movies;
    }

    // Previous helper methods remain the same
    private static String getStringOrDefault(JSONObject json, String key, String defaultValue) {
        try {
            return json.has(key) && !json.isNull(key)
                    ? json.getString(key)
                    : defaultValue;
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    private static int getYearFromJson(JSONObject json) {
        try {
            Object yearObj = json.get("year");

            if (yearObj instanceof Integer) {
                return (Integer) yearObj;
            } else if (yearObj instanceof String) {
                try {
                    return Integer.parseInt((String) yearObj);
                } catch (NumberFormatException e) {
                    return 0;
                }
            } else if (yearObj instanceof Double) {
                return ((Double) yearObj).intValue();
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing year", e);
        }
        return 0;
    }
}
