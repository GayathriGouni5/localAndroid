package org.example.gayathri.moviesapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gayathri on 4/16/17.
 */

class GetMovieData extends AsyncTask<String,Void,String>{
    private static final String TAG = "GetMovieData";
    private final OnDataAvailable callBack;

    public GetMovieData(OnDataAvailable callBack) {
        this.callBack=callBack;
    }

    interface OnDataAvailable {
        void onDataAvailable(ArrayList<Movies> data);
    }
    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection httpConnection = null;
        BufferedReader bufferedReader =null;
        try{
            URL url = new URL(strings[0]);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();
            int response = httpConnection.getResponseCode();
            Log.d(TAG, "downLoadMoviesData: reponse code is"+response);
            StringBuilder stringBuilder = new StringBuilder();
            Log.d(TAG, "doInBackground: stringBuilder"+stringBuilder);
            bufferedReader = new BufferedReader
                    (new InputStreamReader(httpConnection.getInputStream()));
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine())
            {
            //String line;
            //while(null != (line = bufferedReader.readLine())) {
                stringBuilder.append(line).append("\n");

            }
            return stringBuilder.toString();
        } catch (MalformedURLException e){
            Log.e(TAG, "doInBackground: exception is "+e );
        } catch (IOException e){
            Log.e(TAG, "doInBackground: Exception is "+e );
        } catch (Exception e){
            Log.e(TAG, "doInBackground: exception is "+e );
        } finally {
            if(httpConnection != null){
                httpConnection.disconnect();
            }
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                } catch (IOException e){
                    Log.e(TAG, "doInBackground: Exception in finally block is "+e );
                }
            }


        }
        Log.d(TAG, "doInBackground: before returning null");
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: s is " + s);
        ParseJSONMovieData parseJSONData= new ParseJSONMovieData();
        parseJSONData.parseData(s);
        ArrayList<Movies> movieList = parseJSONData.getMoviesList();
        if(callBack != null) {
            callBack.onDataAvailable(movieList);
        }

    }


}
