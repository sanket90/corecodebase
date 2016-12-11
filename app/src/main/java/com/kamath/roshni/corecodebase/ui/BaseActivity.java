package com.kamath.roshni.corecodebase.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.kamath.roshni.corecodebase.BuildConfig;
import com.kamath.roshni.corecodebase.R;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by mac on 12/9/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Handler mHandler;
    private ProgressDialog progressDialog;
    private Toolbar mToolbar ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHandler = new Handler(Looper.getMainLooper());

    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public Toolbar getToolbar(){
        return  mToolbar ;
    }

    public void handleError(Throwable throwable, int i) {
        if(BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
        if (throwable instanceof HttpException) {
            switch (((HttpException) throwable).code()) {
                case 400:
                    //bad request
                case 404:
                    //not found
                case 502:
                    //bad gateway
                case 500:

                    break;
                case 401:

                    break;
            }
        } else if (throwable instanceof JsonMappingException) {
            //parsing error

        } else if (throwable instanceof IOException) {
            //network error
        } else {
            //just show message
        }
    }

    public void showProgressDialog(boolean isCancelable) {
        this.hideProgressDialog();
        try {
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setCanceledOnTouchOutside(true);
            this.progressDialog.setCancelable(isCancelable);
            this.progressDialog.show();
        } catch (WindowManager.BadTokenException var3) {
            ;
        }
    }

    public void hideProgressDialog() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            try {
                this.progressDialog.dismiss();
            } catch (IllegalArgumentException exception) {
                this.progressDialog = null;
            }
        }

    }
}
