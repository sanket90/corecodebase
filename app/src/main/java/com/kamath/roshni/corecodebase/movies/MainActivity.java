package com.kamath.roshni.corecodebase.movies;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.kamath.roshni.corecodebase.R;
import com.kamath.roshni.corecodebase.api.models.Movie;
import com.kamath.roshni.corecodebase.ui.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements MovieContract.View<MovieContract.Presenter> {

    private RecyclerView mRecyclerViewMovies ;

    private MovieContract.Presenter mPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MoviePresenter(this) ;

        mRecyclerViewMovies = (RecyclerView) findViewById(R.id.recycler_movies);
    }

    @Override
    public void setPresenter(MovieContract.Presenter presenter) {
        mPresenter = presenter ;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMovies(List<Movie> movies) {

    }

    @Override
    public void showMovieDetailsUi(String taskId) {

    }

    @Override
    public Handler getHandler() {
        return mHandler;
    }
}
