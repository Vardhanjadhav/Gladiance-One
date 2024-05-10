package com.espressif.cloudapi;

import android.content.Context;

import com.espressif.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlexaApiClient {

    private static Retrofit alexaApiClient = null;

    static Retrofit getAlexaApiClient(Context context) {

        OkHttpClient okHttpClient = null;
        AlexaTokenAuthenticator authAuthenticator;

        authAuthenticator = new AlexaTokenAuthenticator(context);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .authenticator(authAuthenticator)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        alexaApiClient = new Retrofit.Builder()
                .baseUrl(AppConstants.ALEXA_API_ENDPOINTS_URL + AppConstants.PATH_SEPARATOR)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return alexaApiClient;
    }
}
