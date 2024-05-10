package com.espressif.ui.fragment.ControlBouquet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.espressif.ui.adapters.MessageAdapter;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class MessagingFragment extends Fragment {



    public MessagingFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerViewMessaging;
    MessageAdapter adapterMessaging;
    private List<String> tvtitle;
    private List<String> tvmessaging;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messaging, container, false);
        recyclerViewMessaging = view.findViewById(R.id.rv_messaging);


        tvtitle = new ArrayList<>();
        tvmessaging = new ArrayList<>();



        adapterMessaging = new MessageAdapter(requireContext(),tvtitle,tvmessaging);

        tvtitle.add("From : Reception Desk");
        tvtitle.add("From : Reception Desk");
        tvtitle.add("From : Reception Desk");
        tvtitle.add("From : Reception Desk");
        tvtitle.add("From : Reception Desk");
        tvtitle.add("From : Reception Desk");

        tvmessaging.add("Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.");
        tvmessaging.add("Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.");
        tvmessaging.add("Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.");
        tvmessaging.add("Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.");
        tvmessaging.add("Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.");
        tvmessaging.add("Welcome to Taj Blue Diamond! We are excited to hava you stay with us and hope you hava a comfortable and enjoyable visit.");




        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
        recyclerViewMessaging.setLayoutManager(gridLayoutManager);
        recyclerViewMessaging.setHasFixedSize(true);
        recyclerViewMessaging.setAdapter(new MessageAdapter(requireContext(),tvtitle,tvmessaging));

        return view;
    }
}