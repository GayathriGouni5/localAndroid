package org.example.gayathri.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

public class MoviesDetail extends AppCompatActivity {
    private static final String TAG = "MoviesDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviesdetail);
        Intent intent = getIntent();
        String data = intent.getStringExtra("DetailsData");

        TextView tvOverView = (TextView) findViewById(R.id.txtOverView);
        TextView tvTitle = (TextView) findViewById(R.id.txtTitle);
        TextView tvOriginalTitle = (TextView) findViewById(R.id.txtOriginalTitle);
        TextView tvOriginalLanguage = (TextView) findViewById(R.id.txtOriginalLanguage);
        TextView tvPopularity = (TextView) findViewById(R.id.txtPopularity);
        TextView tvReleaseDate = (TextView) findViewById(R.id.txtReleaseDate);
        TextView tvVoteAverage = (TextView) findViewById(R.id.txtVoteAverage);
        TextView tvVoteCount = (TextView) findViewById(R.id.txtVoteCount);


        try {
            JSONObject obj = new JSONObject(data);
            Log.d(TAG, "onCreate: obj.getString(title)"+obj.getString("title"));
            tvTitle.setText("Title : "+obj.getString("title"));
            tvOverView.setText("Overview : "+obj.getString("overView"));
            tvOriginalLanguage.setText("Original Language : "+obj.getString("originalLanguage"));
            tvOriginalTitle.setText("Original Title : "+obj.getString("originalTitle"));
            tvPopularity.setText("Popularity : "+obj.getString("popularity"));
            tvVoteAverage.setText("Vote Average : "+obj.getString("voteAverage"));
            tvVoteCount.setText("Vote Count : "+obj.getString("voteCount"));
            tvReleaseDate.setText("Release Date : "+obj.getString("releaseDate"));

        } catch (Exception e) {
            Log.e(TAG, "onCreate: "+e );
        }
    }
}

