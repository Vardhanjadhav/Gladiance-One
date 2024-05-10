package com.espressif.ui.adapters;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.activities.API.ApiService;
import com.espressif.ui.activities.API.RetrofitClient;
import com.espressif.ui.activities.Login.LoginActivity;
import com.espressif.ui.models.ActiveSceneRes;
import com.espressif.ui.models.scenelist.ObjectTag;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.SceneViewHolder> {

    private int selectedPosition = RecyclerView.NO_POSITION;
    private List<ObjectTag>arraylist;
    private Context context;



    public SceneAdapter(ArrayList<ObjectTag> arraylist,Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public SceneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scene, parent, false);
        return new SceneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SceneViewHolder holder, int position) {
        ObjectTag scene = arraylist.get(position);

        holder.sceneNameTextView.setText(scene.getName());

        int currentNightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        // Check if it's night mode (dark theme)
        boolean isDarkTheme = currentNightMode == Configuration.UI_MODE_NIGHT_YES;

        // For Scene card image change on dark/light theme on static ref (HardCoding)
//        if (isDarkTheme) {
//            if (scene.getRef() == 5001) {
//                holder.iv.setImageResource(R.drawable.lightonwhite);
//            } else if (scene.getRef() == 5002) {
//                holder.iv.setImageResource(R.drawable.switchoffwhite);
//            } else if (scene.getRef() == 5003) {
//                holder.iv.setImageResource(R.drawable.relaxwhite);
//            } else {
//                holder.iv.setImageResource(R.drawable.wether);
//            }
//        } else{
//            if (scene.getRef() == 5001) {
//                holder.iv.setImageResource(R.drawable.lightonblack);
//            } else if (scene.getRef() == 5002) {
//                holder.iv.setImageResource(R.drawable.switchoffblack);
//            } else if (scene.getRef() == 5003) {
//                holder.iv.setImageResource(R.drawable.relaxblack);
//            } else {
//                holder.iv.setImageResource(R.drawable.cloudyblack);
//            }
//        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long sceneRef = scene.getRef();
                String sceneRefString = String.valueOf(sceneRef);

                SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
                String GUID = LoginActivity.getUserId(sharedPreferences);
                Log.e(TAG, "Project Space GUID/LoginDeviceId: "+ GUID);
                String loginDeviceId = GUID.trim();

                SharedPreferences  sharedPreferences2 = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                String savedLoginDeviceId = sharedPreferences2.getString("LoginToken", "");
                Log.e(TAG, "Project Space loginToken: "+savedLoginDeviceId );
                String loginToken = savedLoginDeviceId.trim();

                SharedPreferences  sharedPreferences3 = context.getSharedPreferences("MyPrefsPSR", MODE_PRIVATE);
                String saveProjectSpaceRef = sharedPreferences3.getString("Project_Space_Ref", "");
                Log.e(TAG, "Project Space Ref: "+saveProjectSpaceRef );
                String projectSpaceRef = saveProjectSpaceRef.trim();

                activateScene(context, sceneRefString, projectSpaceRef, loginToken, loginDeviceId);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public static class SceneViewHolder extends RecyclerView.ViewHolder {
        TextView sceneNameTextView;
        ImageView iv;
        LinearLayout linearLayout;
        public SceneViewHolder(@NonNull View itemView) {
            super(itemView);
            sceneNameTextView = itemView.findViewById(R.id.scene_name_text_view);
            linearLayout = itemView.findViewById(R.id.llMoodSelect);
            iv = itemView.findViewById(R.id.imageView);

        }
    }

    private void activateScene(Context context, String gaaProjectSceneRef, String gaaProjectSpaceRef, String loginToken, String loginDeviceId) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ActiveSceneRes> call = apiService.activateScene(gaaProjectSceneRef, gaaProjectSpaceRef, loginToken, loginDeviceId);

        call.enqueue(new Callback<ActiveSceneRes>() {
            @Override
            public void onResponse(Call<ActiveSceneRes> call, Response<ActiveSceneRes> response) {
                if (response.isSuccessful()) {
                    ActiveSceneRes activeSceneRes = response.body();
                    if (activeSceneRes != null) {
                    }
                    // Handle successful response, if needed
                    // For example, show a success message
                    Toast.makeText( context,"Scene activated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle unsuccessful response
                    // For example, show an error message
                    Toast.makeText(context, "Failed to scene", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ActiveSceneRes> call, Throwable t) {
                // Handle failure
                // For example, show a failure message
                Toast.makeText(context, "Failed to activate scene", Toast.LENGTH_SHORT).show();
            }
        });
    }

}