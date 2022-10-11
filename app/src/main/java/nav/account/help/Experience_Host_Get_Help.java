package nav.account.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.letngo.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Experience_Host_Get_Help#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Experience_Host_Get_Help extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button exp_host1, exp_host2, exp_host3, exp_host4;

    public Experience_Host_Get_Help() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Experience_Host_Get_Help.
     */
    // TODO: Rename and change types and number of parameters
    public static Experience_Host_Get_Help newInstance(String param1, String param2) {
        Experience_Host_Get_Help fragment = new Experience_Host_Get_Help();
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
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.account_help_experience_host_get_help, container, false);

        exp_host1 = v.findViewById(R.id.btn_experience_host_1);
        exp_host2 = v.findViewById(R.id.btn_experience_host_2);
        exp_host3 = v.findViewById(R.id.btn_experience_host_3);
        exp_host4 = v.findViewById(R.id.btn_experience_host_4);

        String userhelp = requireActivity().getIntent().getStringExtra("user");

        exp_host1.setOnClickListener(v1 -> {
            Intent intent = new Intent(getContext(), Experience_Host1_Get_Help.class);
            intent.putExtra("user", userhelp);
            startActivity(intent);
        });

        exp_host2.setOnClickListener(v12 -> {
            Intent intent = new Intent(getContext(), Experience_Host2_Get_Help.class);
            intent.putExtra("user", userhelp);
            startActivity(intent);
        });

        exp_host3.setOnClickListener(v13 -> {
            Intent intent = new Intent(getContext(), Experience_Host3_Get_Help.class);
            intent.putExtra("user", userhelp);
            startActivity(intent);
        });

        exp_host4.setOnClickListener(v14 -> {
            Intent intent = new Intent(getContext(), Experience_Host4_Get_Help.class);
            intent.putExtra("user", userhelp);
            startActivity(intent);
        });

        return v;
    }
}