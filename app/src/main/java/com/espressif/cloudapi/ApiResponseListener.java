package com.espressif.cloudapi;

import android.os.Bundle;

public interface ApiResponseListener {
    void onSuccess(Bundle data);

    void onResponseFailure(Exception exception);

    void onNetworkFailure(Exception exception);
}
