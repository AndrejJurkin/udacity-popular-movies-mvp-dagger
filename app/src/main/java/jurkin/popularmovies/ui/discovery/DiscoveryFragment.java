/*
 * Copyright 2017 Andrej Jurkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jurkin.popularmovies.ui.discovery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import jurkin.popularmovies.App;
import jurkin.popularmovies.R;
import jurkin.popularmovies.api.MovieService;
import jurkin.popularmovies.base.BaseFragment;
import jurkin.popularmovies.data.model.Movie;
import jurkin.popularmovies.ui.moviedetail.MovieDetailActivity;

/**
 * Created by ajurkin on 5/13/17.
 */
public class DiscoveryFragment extends BaseFragment
        implements DiscoveryContract.View, DiscoveryAdapter.OnMovieClickListener {

    private static final String TAG = "DiscoveryFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    DiscoveryContract.Presenter presenter;

    @Inject
    RecyclerView.LayoutManager layoutManager;

    @Inject
    DiscoveryAdapter adapter;

    public static DiscoveryFragment newInstance() {
        return new DiscoveryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnMovieClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void setPresenter(DiscoveryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMovies(List<Movie> movies) {
        Log.d(TAG, "Show movies." + isVisible());
        this.adapter.setData(movies);
    }

    @Override
    public void showDetail(Movie movie) {
        Intent i = new Intent(getActivity(), MovieDetailActivity.class);
        i.putExtra(MovieDetailActivity.ARG_MOVIE, movie);
        getActivity().startActivity(i);
    }

    @Override
    public void onMovieClicked(Movie movie) {
        presenter.onMovieClicked(movie);
    }
}