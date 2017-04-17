package org.example.gayathri.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,GetMovieData.OnDataAvailable {

    private static final String TAG = "MainActivity";
    static final String API_KEY = "226cfa46eed6d9e27d57aff283d909f9";
    public static int currentPage = 1;
    public static int totalPages = 0;
    public static ArrayList<Movies> gArrayData = new ArrayList<Movies>();
    public static String searchCriteria = null;

    private String feedUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY
            + "&language=en-US&query=?&page=?&include_adult=false";
    EditText movieNameText;
    ListView lstMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieNameText = (EditText) findViewById(R.id.edtTxtMovieName);
        lstMovies = (ListView) findViewById(R.id.lstMovies);
        Button searchBtn = (Button) findViewById(R.id.btnSearch);
        searchBtn.setOnClickListener(this);
        lstMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: i"+i);

                TextView txtData=(TextView)view.findViewById(R.id.txtHidden);
                Log.d(TAG, "onItemClick: t.getText"+txtData.getText().toString());
                Intent intent = new Intent(MainActivity.this,MoviesDetail.class);
                intent.putExtra("DetailsData",txtData.getText().toString());
                startActivity(intent);
            }
        });

        ScrollListener scrollListener = new ScrollListener();
        lstMovies.setOnScrollListener(scrollListener);
    }

    @Override
    public void onDataAvailable(ArrayList<Movies> data){
        gArrayData.addAll(data);
        MoviesAdapter<Movies> ma =
                new MoviesAdapter<Movies>(MainActivity.this,R.layout.moviesname,gArrayData);
        lstMovies.setAdapter(ma);
    }

    @Override
    public void onClick(View view) {
        if(movieNameText.getText() != null && movieNameText.getText().toString() != ""){
            searchCriteria = movieNameText.getText().toString();
            currentPage = 1;
            gArrayData = new ArrayList<Movies>();
            callMoviesDB();
        } else{
            Toast.makeText(this,"Please Enter a search Criteria" , Toast.LENGTH_SHORT).show();
        }

    }

    void callMoviesDB(){
        String url = createUri(searchCriteria);
        Log.d(TAG, "onClick: url"+url);
        GetMovieData getMovieData = new GetMovieData(this);
        getMovieData.execute(url);
        Log.d(TAG, "onCreate: after downloaded");
    }



    private String createUri(String searchCriteria){
        return Uri.parse(feedUrl).buildUpon().appendQueryParameter("query",searchCriteria)
                .appendQueryParameter("page",Integer.toString(currentPage)).toString();
    }


    //ScrollListener class To
    class ScrollListener implements AbsListView.OnScrollListener
    {

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                             int totalItemCount)
        {
//            if(currentPage < totalPages)
//            {
//                currentPage++;
//                callMoviesDB();
//            }
//            else {
//                Toast.makeText(getBaseContext(),"No data beyond this",Toast.LENGTH_SHORT).show();
//            }
        }
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState)
        {
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                    && (lstMovies.getLastVisiblePosition() - lstMovies.getHeaderViewsCount() -
                    lstMovies.getFooterViewsCount()) >= (lstMovies.getCount() - 1)) {
                if(currentPage < totalPages)
                {
                    currentPage++;
                    callMoviesDB();
                }
                else {
                    Toast.makeText(getBaseContext(),"No data beyond this",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }


}
