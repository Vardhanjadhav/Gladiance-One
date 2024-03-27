package com.espressif.ui.activities;

import android.content.Context;

import com.espressif.AppConstants;
import com.espressif.cloudapi.TokenAuthenticator;
import com.espressif.ui.models.ResponseModelNode;
import com.espressif.wifi_provisioning.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofitClient = null;

    public static Retrofit getClient(Context context) {
//
//        InputStream cert = null;
//        Certificate ca = null;
//        OkHttpClient okHttpClient = null;
//        TokenAuthenticator authAuthenticator;
//
//        try {
//
//            // loading CAs from an InputStream
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
////            cert = context.getResources().openRawResource(R.raw.rainmaker_ca);
////            ca = cf.generateCertificate(cert);
//
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                cert.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // creating a KeyStore containing our trusted CAs
//        String keyStoreType = KeyStore.getDefaultType();
//        KeyStore keyStore = null;
//        authAuthenticator = new TokenAuthenticator(context);
//
//        try {
//            keyStore = KeyStore.getInstance(keyStoreType);
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("ca", ca);
//
//            // creating a TrustManager that trusts the CAs in our KeyStore
//            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//            tmf.init(keyStore);
//
//            // creating an SSLSocketFactory that uses our TrustManager
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, tmf.getTrustManagers(), null);
//
//            // creating an OkHttpClient that uses our SSLSocketFactory
//            okHttpClient = new OkHttpClient.Builder()
//                    .authenticator(authAuthenticator)
//                    .sslSocketFactory(sslContext.getSocketFactory(), systemDefaultTrustManager())
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .writeTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(15, TimeUnit.SECONDS)
//                    .build();
//
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//
//        retrofitClient = new Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL + AppConstants.PATH_SEPARATOR + AppConstants.CURRENT_VERSION + AppConstants.PATH_SEPARATOR)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(okHttpClient)
//                .build();
//
        retrofitClient = new Retrofit.Builder()
                .baseUrl("https://enscloud.in/gladiancedev-gladiance-web-api/mobileapp/")
                .build();

        return retrofitClient;
    }

    private static X509TrustManager systemDefaultTrustManager() {

        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }
    }

//    private static final String BASE_URL = "https://your-base-url.com/";
//
//    private static Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//    public static ApiService getApiService() {
//        return retrofit.create(ApiService.class);
//    }
//
//
//
//    ////// Node ID Get Api ///////
//
//
//    private static final String BASE_URL_NODE = "https://enscloud.in/";
//
//    private static Retrofit retrofit_node = null;
/////called in AddDeviceActivity but not called that fetch method in it
//    public static Retrofit getClient() {
//        if (retrofit_node == null) {
//            retrofit_node = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit_node;
//    }


    //


//    private static final String BASE_URL2 = "https://enscloud.in/";
//    private static Retrofit retrofit_node;
//
//    public static ApiService getApiServices() {
//        if (retrofit_node == null) {
//            retrofit_node = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(ApiService.class);
//    }
//
//    public static void makeApiCall(String nodeId, final ApiResponseCallback callback) {
//        ApiService apiService = getApiService();
//
//        Call<ResponseModelNode> call = apiService.getData(nodeId);
//        call.enqueue(new Callback<ResponseModelNode>() {
//            @Override
//            public void onResponse(Call<ResponseModelNode> call, Response<ResponseModelNode> response) {
//                if (response.isSuccessful()) {
//                    // Handle the successful response
//                    ResponseModelNode data = response.body();
//                    callback.onSuccess(data);
//                } else {
//                    // Handle the error response
//                    callback.onError(response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModelNode> call, Throwable t) {
//                // Handle the network error
//                callback.onError(t.getMessage());
//            }
//        });
//    }




}
