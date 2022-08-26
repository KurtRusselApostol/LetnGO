package nav.account.help;

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
 * Use the {@link Host_Get_Help#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Host_Get_Help extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button host1, host2, host3, host4;

    public Host_Get_Help() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Host_Get_Help.
     */
    // TODO: Rename and change types and number of parameters
    public static Host_Get_Help newInstance(String param1, String param2) {
        Host_Get_Help fragment = new Host_Get_Help();
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
        View v = inflater.inflate(R.layout.fragment_host__get__help, container, false);

        host1 = v.findViewById(R.id.btn_host_1);
        host2 = v.findViewById(R.id.btn_host_2);
        host3 = v.findViewById(R.id.btn_host_3);
        host4 = v.findViewById(R.id.btn_host_4);

        String userhelp = getActivity().getIntent().getStringExtra("user");

        host1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Experience_Host1_Get_Help.class);
                intent.putExtra("user", userhelp);
                startActivity(intent);
            }
        });

        host2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Host2_Get_Help.class);
                intent.putExtra("user", userhelp);
                startActivity(intent);
            }
        });

        host3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Host3_Get_Help.class);
                intent.putExtra("user", userhelp);
                startActivity(intent);
            }
        });

        host4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Host4_Get_Help.class);
                intent.putExtra("user", userhelp);
                startActivity(intent);
            }
        });

        return v;
    }
}