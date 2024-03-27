package com.espressif.cloudapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.espressif.AppConstants;
import com.espressif.ui.activities.EspApplication;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {

    private static final String TAG = TokenAuthenticator.class.getSimpleName();

    private Context context;

    TokenAuthenticator(Context context) {
        this.context = context;
    }

    @Override
    public Request authenticate(Route route, Response response) {

        Log.d(TAG, "=============== Authenticate callback ===============");
        Log.d(TAG, "Response code : " + response.code());
//        String newToken = ApiManager.getInstance(context).getNewToken();
//
//        if (!TextUtils.isEmpty(newToken)) {
//            Log.d(TAG, "Retrying with new token");
//            // Add new header to rejected request and retry it
//            return response.request().newBuilder()
//                    .header(AppConstants.HEADER_AUTHORIZATION, newToken)
//                    .build();
//        } else {
//           // doLogout();
//            return null;
//        }
        return null;
    }

//    private void doLogout() {
//        Log.d(TAG, "Logout and display Login screen");
//        ((EspApplication) context).logout();
//    }
}
