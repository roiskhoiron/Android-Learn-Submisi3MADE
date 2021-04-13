package com.example.submisi3made.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submisi3made.R;
import com.example.submisi3made.adapter.MoviesAdapter;
import com.example.submisi3made.model.Movies;
import com.example.submisi3made.viewModel.MoviesViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private static final String TAG = "MoviesFragment";

    private RecyclerView rvMovies;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: running");
        super.onViewCreated(view, savedInstanceState);


        rvMovies = view.findViewById(R.id.rv_category);
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));

        moviesAdapter = new MoviesAdapter(this);
        moviesAdapter.notifyDataSetChanged();

        rvMovies.setAdapter(moviesAdapter);

        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);

        moviesViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movies>>() {
            @Override
            public void onChanged(ArrayList<Movies> movies) {
                Log.d(TAG, "onChanged: running");
                if (movies != null) {
                    moviesAdapter.setData(movies);
                    showLoading(false);
                }
            }
        });

        moviesViewModel.setMovies("1642911");
    }

    private void showLoading(Boolean state) {
        if (state) {
            Log.d(TAG, "showLoading: on");
            progressBar.setVisibility(View.VISIBLE);
        } else {
            Log.d(TAG, "showLoading: off");
            progressBar.setVisibility(View.GONE);
        }
    }
}
