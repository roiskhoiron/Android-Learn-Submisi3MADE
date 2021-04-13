package com.example.submisi3made.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submisi3made.model.Movies;
import com.example.submisi3made.model.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class TvShowsViewModel extends ViewModel {
    private static final String TAG = "TvShowsViewModel";

    private static final String API_KEY = "3330a65b63a628fcd0eae6876455f8cf";
    private MutableLiveData<ArrayList<TvShow>> listTvShow = new MutableLiveData<>();

    public void setTvShows(final String run) {
        Log.d(TAG, "setTvShows: running");
        AsyncHttpClient client = new AsyncHttpClient();

        final ArrayList<TvShow> listItems = new ArrayList<>();

        String url = "http://api.themoviedb.org/3/discover/tv?api_key=" +API_KEY+ "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "onSuccess: running");
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tvShow = list.getJSONObject(i);
                        TvShow tvShowItems = new TvShow();
                        tvShowItems.setTitle(tvShow.getString("original_name"));
                        tvShowItems.setOverview(tvShow.getString("overview"));
                        tvShowItems.setYear(tvShow.getString("first_air_date"));
                        tvShowItems.setPhoto(tvShow.getString("poster_path"));
                        tvShowItems.setAverage(tvShow.getInt("vote_average"));
                        listItems.add(tvShowItems);
                    }
                    listTvShow.postValue(listItems);
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
    public LiveData<ArrayList<TvShow>> getTvShows() {
        Log.d(TAG, "getMovies: running");
        return listTvShow;
    }
}
