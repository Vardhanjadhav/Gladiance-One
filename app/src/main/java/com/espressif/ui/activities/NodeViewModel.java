package com.espressif.ui.activities;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.espressif.ui.models.NodeResponseModel;

public class NodeViewModel extends ViewModel {
//    private NodeRepository nodeRepository;
//    private MutableLiveData<NodeResponseModel> nodeLiveData = new MutableLiveData<>();
//    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
//
//    String macId;
////    public void NodeViewModel(Context context) {
////
////        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
////         macId = preferences.getString("mac", "");
////
////    }
//
//
//    public NodeViewModel() {
//        nodeRepository = new NodeRepository();
////        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
////        macId = preferences.getString("mac", "");
//
//    }
//
//    public void getNode(String macId) {
//        Log.d(TAG, "getMac2: "+macId);
//        nodeRepository.getNode(macId, new NodeRepository.NodeCallback() {
//            @Override
//            public void onSuccess(NodeResponseModel nodeResponseModel) {
//                nodeLiveData.setValue(nodeResponseModel);
//            }
//
//            @Override
//            public void onError(String error) {
//                errorLiveData.setValue(error);
//            }
//        });
//    }
//
//    public MutableLiveData<NodeResponseModel> getNodeLiveData() {
//        return nodeLiveData;
//    }
//
//    public MutableLiveData<String> getErrorLiveData() {
//        return errorLiveData;
//    }
}
