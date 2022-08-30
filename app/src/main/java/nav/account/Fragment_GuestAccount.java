package nav.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.letngo.R;

import login.system.new_login;
import nav.account.help.GetHelp;
import nav.account.safety.safety_center;
import nav.account.support.Terms_Services;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_GuestAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_GuestAccount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_GuestAccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_GuestAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_GuestAccount newInstance(String param1, String param2) {
        Fragment_GuestAccount fragment = new Fragment_GuestAccount();
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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment__guest_account, container, false);
//    }

//START SIGNUP METHOD
    public Button button,terms_service,safety,support,get_help;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    //return inflater.inflate(R.layout.fragment_account, container, false);

    View v = inflater.inflate(R.layout.account_guest_account, container, false);
     button = v.findViewById(R.id.buttonLogin);
     terms_service = v.findViewById(R.id.btn_terms);
     safety = v.findViewById(R.id.btn_safety_center);
     support = v.findViewById(R.id.btn_contact_support);
     get_help = v.findViewById(R.id.btn_get_help);

    terms_service.setOnClickListener(this::onClick);
    safety.setOnClickListener(this::onClick);
    support.setOnClickListener(this::onClick);
    get_help.setOnClickListener(this::onClick);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), new_login.class);
            startActivity(intent);
        }
    });

    return  v;
}

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_terms:
                Intent intent = new Intent(getActivity(), Terms_Services.class);
                startActivity(intent);
                break;
            case R.id.btn_safety_center:
                Intent intent1 = new Intent(getActivity(), safety_center.class);
                startActivity(intent1);
                break;

                /*    case R.id.btn_contact_support: //CHANGE EDIT PROFILE CLASS TO CONTACT SUPPORT XML
                Intent intent2 = new Intent(getActivity(), Edit_Profile.class);
                startActivity(intent2);
                break;   */

            case R.id.btn_get_help:
                Intent intent2 = new Intent(getActivity(), GetHelp.class);
                startActivity(intent2);
                break;
        }
    }

}

