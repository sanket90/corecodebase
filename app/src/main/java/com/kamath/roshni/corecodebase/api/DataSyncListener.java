package com.kamath.roshni.corecodebase.api;

/**
 * Created by mac on 12/10/16.
 */

public interface DataSyncListener<T> {

    void onStart(int requestCode);

    void onError(Throwable error, int requestCode);

    void onSuccess(T obj, int requestCode);
}
