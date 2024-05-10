package com.espressif.cloudapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.espressif.AppConstants;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AlexaTokenAuthenticator implements Authenticator {

    private static final String TAG = AlexaTokenAuthenticator.class.getSimpleName();

    private Context context;

    AlexaTokenAuthenticator(Context context) {
        this.context = context;
    }

    @Override
    public Request authenticate(Route route, Response response) {

        Log.d(TAG, "=============== Authenticate callback ===============");
        Log.d(TAG, "Response code : " + response.code());
        String newToken = AlexaApiManager.getInstance(context).getNewToken();
        String headerValue = "Bearer " + newToken;

        if (!TextUtils.isEmpty(newToken)) {
            Log.e(TAG, "Retrying with new token");
            // Add new header to rejected request and retry it
            return response.request().newBuilder()
                    .header(AppConstants.HEADER_AUTHORIZATION, headerValue)
                    .build();
        } else {
            // TODO
            return null;
        }
    }
}