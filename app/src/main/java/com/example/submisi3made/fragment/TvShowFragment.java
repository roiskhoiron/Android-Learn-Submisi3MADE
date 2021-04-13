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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submisi3made.R;
import com.example.submisi3made.adapter.TvShowsAdapter;
import com.example.submisi3made.model.TvShow;
import com.example.submisi3made.viewModel.TvShowsViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private static final String TAG = "TvShowFragment";

    private RecyclerView rvTvShows;
    private TvShowsAdapter tvShowsAdapter;
    private ProgressBar progressBar;
    private TvShowsViewModel tvShowsViewModel;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: running");

        rvTvShows = view.findViewById(R.id.rv_category);
        rvTvShows.setLayoutManager(new GridLayoutManager(getContext(), 2));

        tvShowsAdapter = new TvShowsAdapter(this);
        tvShowsAdapter.notifyDataSetChanged();

        rvTvShows.setAdapter(tvShowsAdapter);

        progressBar = view.findViewById(R.id.progressBar);

        tvShowsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowsViewModel.class);

        tvShowsViewModel.getTvShows().observe(getViewLifecycleOwner(), new Observer<ArrayList<TvShow>>() {
            @Override
            public void onChanged(ArrayList<TvShow> tvShows) {
                Log.d(TAG, "onChanged: running");
                if (tvShows != null) {
                    tvShowsAdapter.setData(tvShows);
                    showLoading(false);
                }
            }
        });

        tvShowsViewModel.setTvShows("1642911");
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
