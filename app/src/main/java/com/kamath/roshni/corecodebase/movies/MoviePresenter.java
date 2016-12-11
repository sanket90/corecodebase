package com.kamath.roshni.corecodebase.movies;

import android.support.annotation.NonNull;

import com.kamath.roshni.corecodebase.api.models.Movie;

/**
 * Created by mac on 12/11/16.
 */

public class MoviePresenter implements MovieContract.Presenter {

    @NonNull
    MovieContract.View mView ;

    public MoviePresenter(@NonNull MovieContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadMovies(boolean forceUpdate) {

    }

    @Override
    public void openMovieDetails(@NonNull Movie requestedMovie) {

    }
}
