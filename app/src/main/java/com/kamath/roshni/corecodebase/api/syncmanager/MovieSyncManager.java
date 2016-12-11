package com.kamath.roshni.corecodebase.api.syncmanager;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.kamath.roshni.corecodebase.R;
import com.kamath.roshni.corecodebase.api.DataSyncListener;
import com.kamath.roshni.corecodebase.api.DataSyncManager;
import com.kamath.roshni.corecodebase.api.syncmodels.MovieResponse;

import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 12/11/16.
 */

public class MovieSyncManager extends DataSyncManager {

    public static void getMovies(@NonNull Context context, final Handler handler, final DataSyncListener listener, final int requestCode) {
        getApplicationAPI(context)
                .getTopRatedMovies(context.getResources().getString(R.string.movie_api_key))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<MovieResponse>() {
                    @Override
                    public void call(MovieResponse response) {
                        sendOnSuccessEvent(handler, listener, response, requestCode);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        sendOnErrorEvent(handler, listener, throwable, requestCode);
                    }
                });
    }
}
