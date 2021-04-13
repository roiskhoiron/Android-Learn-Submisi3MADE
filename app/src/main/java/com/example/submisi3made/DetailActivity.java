package com.example.submisi3made;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submisi3made.model.Movies;
import com.example.submisi3made.model.TvShow;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    public static final String MOVIE_INDEX = "parcel movie";
    public static final String TVSHOW_INDEX = "parcel tvShow";

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: running");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvRelease = findViewById(R.id.tv_release);
        TextView tvDirector = findViewById(R.id.tv_director);
        TextView tvOverview = findViewById(R.id.tv_overview);
        ImageView imgvPhoto = findViewById(R.id.imgv_photo);
        progressBar = findViewById(R.id.progressBar);


        Movies movieParcel = getIntent().getParcelableExtra(MOVIE_INDEX);
        TvShow tvShowParcel = getIntent().getParcelableExtra(TVSHOW_INDEX);

        if (movieParcel != null) {
            Log.d(TAG, "onCreate: movie");
            String vote_average = Integer.toString(movieParcel.getAverage());
            String url_image = "https://image.tmdb.org/t/p/w185" + movieParcel.getPhoto();

            Objects.requireNonNull(getSupportActionBar()).setTitle("Movie");
            tvTitle.setText(movieParcel.getTitle());
            tvRelease.setText(movieParcel.getYear());
            tvDirector.setText(vote_average);
            tvOverview.setText(movieParcel.getOverview());
            Glide.with(this)
                    .load(url_image)
                    .apply(new RequestOptions().override(100, 140))
                    .into(imgvPhoto);
            showLoading(false);
        } else if (tvShowParcel != null) {
            Log.d(TAG, "onCreate: tvshow");
            String vote_average = Integer.toString(tvShowParcel.getAverage());
            String url_image = "https://image.tmdb.org/t/p/w185" + tvShowParcel.getPhoto();

            Objects.requireNonNull(getSupportActionBar()).setTitle("TvShow");
            tvTitle.setText(tvShowParcel.getTitle());
            tvRelease.setText(tvShowParcel.getYear());
            tvDirector.setText(vote_average);
            tvOverview.setText(tvShowParcel.getOverview());
            Glide.with(this)
                    .load(url_image)
                    .apply(new RequestOptions().override(100, 140))
                    .into(imgvPhoto);
            showLoading(false);
        } else {
            Log.e(TAG, "onCreate: Error");
            showLoading(true);
        }

    }

    private void showLoading(Boolean state) {
        progressBar = findViewById(R.id.progressBar);
        if (state) {
            Log.d(TAG, "showLoading: on");
            progressBar.setVisibility(View.VISIBLE);
        } else {
            Log.d(TAG, "showLoading: off");
            progressBar.setVisibility(View.GONE);
        }
    }
}
