package com.example.letngo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationHosting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationTraveller extends Fragment {

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationTraveller() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationHosting.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationTraveller newInstance(String param1, String param2) {
        NotificationTraveller fragment = new NotificationTraveller();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notification_traveller, container, false);

        Button btn_safety_notice1 = (Button) view.findViewById(R.id.btn_Safety_Notice1);

        btn_safety_notice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Safety_notice.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });


        Button btn_travel_tips1 = (Button) view.findViewById(R.id.btn_Travel_Tips1);
        btn_travel_tips1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Travel_Tips.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });

        Button travel_offers1 = (Button) view.findViewById(R.id.btn_Travel_Offers1);
        travel_offers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Travel_Offers.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });

        Button whats_new1 = (Button) view.findViewById(R.id.btn_Whats_new1);
        whats_new1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Whats_New.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });


        return view;
    }
}