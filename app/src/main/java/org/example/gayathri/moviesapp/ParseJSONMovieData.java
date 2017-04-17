package org.example.gayathri.moviesapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static org.example.gayathri.moviesapp.MainActivity.currentPage;
import static org.example.gayathri.moviesapp.MainActivity.totalPages;

/**
 * Created by gayathri on 4/16/17.
 */

class ParseJSONMovieData {
    private static final String TAG = "ParseJSONMovieData";
    private ArrayList<Movies> moviesList;

    public ParseJSONMovieData() {
       // if(currentPage == 1)
        moviesList = new ArrayList<>();

    }

    public ArrayList<Movies> getMoviesList() {
        Log.d(TAG, "getMoviesList: size"+moviesList.size());
        return moviesList;
    }

    Boolean parseData(String jsonmoviesData){
        try {
            JSONObject jsonData = new JSONObject(jsonmoviesData);
            totalPages = jsonData.getInt("total_pages");
            currentPage = jsonData.getInt("page");

            JSONArray itemsArray = jsonData.getJSONArray("results");

            for (int i = 0; i < itemsArray.length(); i++) {

                JSONObject jsonMovie = itemsArray.getJSONObject(i);
                String overView = jsonMovie.getString("overview");
                String releaseDate = jsonMovie.getString("release_date");
                String originalTitle = jsonMovie.getString("original_title");
                String originalLanguage = jsonMovie.getString("original_language");
                String title = jsonMovie.getString("title");
                Double popularity = jsonMovie.getDouble("popularity");
                int voteAverage = jsonMovie.getInt("vote_average");
                int voteCount = jsonMovie.getInt("vote_count");



                Movies movieObject = new Movies();
                movieObject.setTitle(title);
                movieObject.setOverView(overView);
                movieObject.setReleaseDate(releaseDate);
                movieObject.setOriginalTitle(originalTitle);
                movieObject.setOriginalLanguage(originalLanguage);
                movieObject.setPopularity(popularity);
                movieObject.setVoteAverage(voteAverage);
                movieObject.setVoteCount(voteCount);

                Log.d(TAG, "parseData: movieObject"+movieObject);
                moviesList.add(movieObject);

            }
            Log.d(TAG, "parseData: moviesList"+moviesList);
        } catch (JSONException json) {
            json.printStackTrace();
            Log.e(TAG, "onDownloadComplete: Error processing Json data " + json.getMessage());
        }
        return true;
    }

}
