package com.espressif.ui.activities;

import android.os.Bundle;

public interface ApiResponseListener {

    void onSuccess(Bundle data);

    void onResponseFailure(Exception exception);

    void onNetworkFailure(Exception exception);
}
