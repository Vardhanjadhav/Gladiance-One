package com.espressif.ui.activities.AlexaLinking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.espressif.AppConstants;
import com.espressif.cloudapi.AlexaApiManager;

public class AlexaLinkingWorker extends Worker {

    private static final String TAG = AlexaLinkingWorker.class.getSimpleName();

    public AlexaLinkingWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.e(TAG, "Get alexa linking work");
        AlexaApiManager apiManager = AlexaApiManager.getInstance(getApplicationContext());
        Data data = getInputData();
        String type = data.getString(AppConstants.KEY_EVENT_TYPE);
        String alexaAuthCode = data.getString(AppConstants.KEY_AUTH_CODE);
        boolean success = false;

        if (!TextUtils.isEmpty(type)) {

            if (type.equals(AppConstants.EVENT_ENABLE_SKILL)) {
                Log.d(TAG, "Alexa Auth code : " + alexaAuthCode);
                success = apiManager.enableAlexaSkill(alexaAuthCode);
                Log.d(TAG, "Enable alexa skill : result : " + success);

            } else if (type.equals(AppConstants.EVENT_DISABLE_SKILL)) {
                success = apiManager.disableAlexaSkill();
                Log.d(TAG, "Disable alexa skill : result : " + success);

            } else if (type.equals(AppConstants.EVENT_GET_STATUS)) {
                success = apiManager.getStatus();
                Log.d(TAG, "Get Status : " + success);
            }
        }
        if (success) {
            return Result.success();
        } else {
            return Result.failure();
        }
    }
}
