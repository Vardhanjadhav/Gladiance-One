package com.espressif.ui.navigation.ControlBouquet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{

    private Context context;
    private List<String> tvtitle;
    private List<String> tvmessege;

    public MessageAdapter(Context context, List<String> tvtitle, List<String> tvmessege) {
        this.context = context;
        this.tvtitle = tvtitle;
        this.tvmessege = tvmessege;
    }


    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.messaging_recycler, parent, false);
        return new MessageAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        holder.TextView1.setText(tvtitle.get(position));
        holder.TextView2.setText(tvmessege.get(position));
    }

    @Override
    public int getItemCount() {
        return tvtitle.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {

        TextView TextView1, TextView2;

        public MyViewHolder(View v) {
            super(v);
            TextView1 = v.findViewById(R.id.tvtitle);
            TextView2 = v.findViewById(R.id.tvMessage);

        }
    }
}
