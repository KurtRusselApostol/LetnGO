package nav.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.letngo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationHosting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationHosting extends Fragment {

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationHosting() {
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
    public static NotificationHosting newInstance(String param1, String param2) {
        NotificationHosting fragment = new NotificationHosting();
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
        view = inflater.inflate(R.layout.notifications_notification_hosting, container, false);

        Button btn_checkingOut = (Button) view.findViewById(R.id.btn_checking_out1);

        btn_checkingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Checking_out.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });


        Button btn_currentlyHosting = (Button) view.findViewById(R.id.btn_currently_hosting1);
        btn_currentlyHosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Currently_Hosting.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });

        Button btn_arrivingSoon = (Button) view.findViewById(R.id.btn_arriving_soon1);
        btn_arrivingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Arriving_Soon.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });

        Button btn_upComing = (Button) view.findViewById(R.id.btn_upcoming1);
        btn_upComing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Upcoming.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });

        Button btn_allReservations = (Button) view.findViewById(R.id.btn_all_reservations1);
        btn_allReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), All_Reservation.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });

        return view;
    }
}