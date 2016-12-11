package com.kamath.roshni.corecodebase.movies;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.kamath.roshni.corecodebase.api.models.Movie;
import com.kamath.roshni.corecodebase.framework.BasePresenter;
import com.kamath.roshni.corecodebase.framework.BaseView;

import java.util.List;

/**
 * Created by mac on 12/11/16.
 */

public class MovieContract {

    interface View<T> extends BaseView<T> {

        void setLoadingIndicator(boolean active);

        void showMovies(List<Movie> movies);

        void showMovieDetailsUi(String taskId);

        Handler getHandler();
    }

    interface Presenter extends BasePresenter {
        void result(int requestCode, int resultCode);

        void loadMovies(boolean forceUpdate);

        void openMovieDetails(@NonNull Movie requestedMovie);
    }
}
