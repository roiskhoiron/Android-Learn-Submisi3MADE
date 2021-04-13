package com.example.submisi3made.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submisi3made.model.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MoviesViewModel extends ViewModel {
    private static final String TAG = "MoviesViewModel";
    
    private static final String API_KEY = "3330a65b63a628fcd0eae6876455f8cf";
    private MutableLiveData<ArrayList<Movies>> listMovies = new MutableLiveData<>();

    public void setMovies(final String run) {
        Log.d(TAG, "setMovies: running");
        AsyncHttpClient client = new AsyncHttpClient();

        final ArrayList<Movies> listItems = new ArrayList<>();

        String url = "http://api.themoviedb.org/3/discover/movie?api_key=" +API_KEY+ "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "onSuccess: running");
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movies = list.getJSONObject(i);
                        Movies movieItems = new Movies();
                        movieItems.setTitle(movies.getString("original_title"));
                        movieItems.setOverview(movies.getString("overview"));
                        movieItems.setYear(movies.getString("release_date"));
                        movieItems.setPhoto(movies.getString("poster_path"));
                        movieItems.setAverage(movies.getInt("vote_average"));
                        listItems.add(movieItems);
                    }
                    listMovies.postValue(listItems);
                    Log.d("sukses", "onSuccess: ");
                } catch (Exception e) {
                    Log.d("Exception", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "onFailure: running");
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
    }
    public LiveData<ArrayList<Movies>> getMovies() {
        Log.d(TAG, "getMovies: running");
        return listMovies;
    }
}
