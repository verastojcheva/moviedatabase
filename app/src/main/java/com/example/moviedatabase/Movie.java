package com.example.moviedatabase;

public class Movie {
    private String title;
    private int year;
    private String genre;
    private String posterResourceId;

    // Constructor with error checking
    public Movie(String title, int year, String genre, String posterResourceId) {
        // Validate and set title
        this.title = (title == null || title.trim().isEmpty())
                ? "Unknown Title"
                : title;

        // Validate year
        this.year = (year > 0 && year < 2025)
                ? year
                : 0;

        // Set genre with default
        this.genre = (genre == null || genre.trim().isEmpty())
                ? "Unspecified"
                : genre;

        // Set poster with default
        this.posterResourceId = (posterResourceId == null || posterResourceId.trim().isEmpty())
                ? "default_poster"
                : posterResourceId;
    }

    // Getters
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    public String getPosterResourceId() { return posterResourceId; }

    // toString for debugging
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", poster='" + posterResourceId + '\'' +
                '}';
    }
}