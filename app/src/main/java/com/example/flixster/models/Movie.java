package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    // package private: default access modifier -> visible to classes in same package
    String posterPath;
    String title;
    String overview;

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) {
        List<Movie> movies = new ArrayList<>(); // reload list
        for (int i = 0; i < movieJsonArray.length(); i++) { // retrieve movie data
            try {
                movies.add(new Movie(movieJsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath); // should fetch sizes
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
