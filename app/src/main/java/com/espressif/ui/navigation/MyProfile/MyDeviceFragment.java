package com.espressif.ui.navigation.MyProfile;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;


public class MyDeviceFragment extends Fragment {



    public MyDeviceFragment() {
        // Required empty public constructor
    }
    Button AddDevice;

    RecyclerView RecycleViewMyDevice;

    private List<String> mDLabel;
    private List<String> mDLabelName;
    private List<String> mDProject;
    private List<String> mDProjectName;
    private List<String> mDSpace;
    private List<String> mDSpaceName;
    private List<String> mDArea;
    private List<String> mDAreaName;
    private List<Integer> mDDelete;
    private List<Integer> mDRefresh;

    private MyDeviceAdapter MyDeviceadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_device, container, false);

        AddDevice = view.findViewById(R.id.AddDevice);

        AddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddDeviceFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.MyDeviceFragment, fragment).addToBackStack(null)
                        .commit();

            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
                Log.d(TAG, "handleOnBackPressed: ");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        Spinner dropdownSpinner = view.findViewById(R.id.dropdownSpinner1);

        // Create a list of items for the dropdown
        List<String> items = new ArrayList<>();
        items.add("Home");
        items.add("Hotel");
        items.add("Office");
        items.add("Hospital");
        items.add("Other");

        // Create a custom adapter with your custom layout for dropdown items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.dropdown_item1,
                R.id.text1,
                items
        );

        // Set the adapter for the dropdown spinner
        dropdownSpinner.setAdapter(adapter);

        //Recycle view code
        RecycleViewMyDevice = view.findViewById(R.id.recycleViewMyDevice);

        mDLabel = new ArrayList<>();
        mDLabelName = new ArrayList<>();
        mDProject = new ArrayList<>();
        mDProjectName = new ArrayList<>();
        mDSpace = new ArrayList<>();
        mDSpaceName = new ArrayList<>();
        mDArea = new ArrayList<>();
        mDAreaName = new ArrayList<>();
        mDDelete = new ArrayList<>();
        mDRefresh = new ArrayList<>();

        MyDeviceadapter = new MyDeviceAdapter(requireContext(),mDLabel,mDLabelName,mDProject,mDProjectName,mDSpace,mDSpaceName,mDArea,mDAreaName,mDDelete,mDRefresh);


        mDLabel.add("Label :");
        mDLabel.add("Label :");
        mDLabel.add("Label :");
        mDLabel.add("Label :");

        mDLabelName.add("Dev1 AC");
        mDLabelName.add("Dev2 Fan");
        mDLabelName.add("Dev2 Light");
        mDLabelName.add("Dev2 Fan");

        mDProject.add("Project :");
        mDProject.add("Project :");
        mDProject.add("Project :");
        mDProject.add("Project :");

        mDProjectName.add("ABC");
        mDProjectName.add("XYZ");
        mDProjectName.add("ABC");
        mDProjectName.add("XYZ");

        mDSpace.add("Space :");
        mDSpace.add("Space :");
        mDSpace.add("Space :");
        mDSpace.add("Space :");

        mDSpaceName.add("Veloce");
        mDSpaceName.add("Veloce");
        mDSpaceName.add("Veloce");
        mDSpaceName.add("Veloce");

        mDArea.add("Area :");
        mDArea.add("Area :");
        mDArea.add("Area :");
        mDArea.add("Area :");

        mDAreaName.add("Developer Room");
        mDAreaName.add("Developer Room");
        mDAreaName.add("Developer Room");
        mDAreaName.add("Developer Room");


        mDDelete.add(R.drawable.delete);
        mDDelete.add(R.drawable.delete);
        mDDelete.add(R.drawable.delete);
        mDDelete.add(R.drawable.delete);

        mDRefresh.add(R.drawable.reload);
        mDRefresh.add(R.drawable.reload);
        mDRefresh.add(R.drawable.reload);
        mDRefresh.add(R.drawable.reload);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false);
        RecycleViewMyDevice.setLayoutManager(gridLayoutManager);
        RecycleViewMyDevice.setHasFixedSize(true);
        RecycleViewMyDevice.setAdapter(new MyDeviceAdapter(requireContext(),mDLabel,mDLabelName,mDProject,mDProjectName,mDSpace,mDSpaceName,mDArea,mDAreaName,mDDelete,mDRefresh));



        return view;
    }
}