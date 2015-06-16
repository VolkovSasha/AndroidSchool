package com.example.pavilion.androidschool.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import com.example.pavilion.androidschool.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

/**
 * Created by Pavilion on 27.04.2015.
 */
public class LoginActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_ERROR_CODE = 9000;

    private GoogleApiClient mClient;

    private boolean mSignInClick = false;
    private boolean mIntentInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSignInClick = true;
        mClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.w("PLUS", "Connected");
        mSignInClick = false;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("PLUS", "Connection failed");
        if(!mIntentInProgress) {
            if(mSignInClick && connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, REQUEST_ERROR_CODE);
                    mIntentInProgress = true;
                } catch(IntentSender.SendIntentException e) {
                    Log.e(LoginActivity.class.getSimpleName(), e.getMessage(), e);
                    mIntentInProgress = false;
                    mClient.connect();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ERROR_CODE) {
            if(resultCode != RESULT_OK) {
                mSignInClick = false;
            }
            mIntentInProgress = false;
            if(!mClient.isConnected()) {
                mClient.reconnect();
            }
        }
    }
}
