package com.example.pavilion.androidschool.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.pavilion.androidschool.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavilion on 22.04.2015.
 */
public class SplashActivity extends Activity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        setNextActivity(LoginActivity.class);
        //initAuthenticatedCB();
        //authenticatedRequest();
        showNewActivity();
    }

    private void showNewActivity(){
        ScheduledExecutorService worker =
                Executors.newSingleThreadScheduledExecutor();
        Runnable task = new Runnable() {
            public void run() {
                openActivity();
            }
        };
        worker.schedule(task, 5, TimeUnit.SECONDS);
    }

    private void openActivity() {
        startActivity(mIntent);
        finish();
    }

    private void setNextActivity(Class _class){
        mIntent = new Intent(this, _class);
    }
}
