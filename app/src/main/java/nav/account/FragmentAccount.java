package nav.account;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.letngo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import nav.account.help.GetHelp;
import nav.account.hosting.Manage_Hosting;
import nav.account.hosting.start_hosting;
import nav.account.notif.notif_settings1;
import nav.account.privacy.PrivacyPolicy;
import nav.account.safety.safety_center;
import nav.account.support.Terms_Services;
import nav.account.support.letngo_support;
import nav.categories.HomePage;

public class FragmentAccount extends Fragment {

    public CardView start, manage, edit, how, logout, notif_settings,
            terms_services, safety, get_help, payment, security, feedback,privacy;

    public FragmentAccount() {
        // Required empty public constructor

    }

    public static FragmentAccount newInstance() {
        return new FragmentAccount();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        View v = inflater.inflate(R.layout.account_account, container, false);

        ///////////////Button Id Layout//////////////////////
        TextView fullName = v.findViewById(R.id.up_fullName);
        TextView timeline = v.findViewById(R.id.btn_timeline);
        start = v.findViewById(R.id.btn_list_space);
        manage = v.findViewById(R.id.btn_host_exp);
        edit = v.findViewById(R.id.btn_personal_info);
        how = v.findViewById(R.id.btn_how_works);
        logout = v.findViewById(R.id.btn_logout);
        notif_settings = v.findViewById(R.id.btn_notification);
        terms_services = v.findViewById(R.id.btn_terms);
        safety = v.findViewById(R.id.btn_safety_center);
        get_help = v.findViewById(R.id.btn_get_help);
        payment = v.findViewById(R.id.btn_payment_payout);
        feedback = v.findViewById(R.id.btn_feedback);
        security = v.findViewById(R.id.securityButton);
        privacy = v.findViewById(R.id.btn_privacyPolicy);

        ///////////// Button Click Listener ////////////
        timeline.setOnClickListener(this::onClick);
        start.setOnClickListener(this::onClick);
        manage.setOnClickListener(this::onClick);
        edit.setOnClickListener(this::onClick);
        how.setOnClickListener(this::onClick);
        logout.setOnClickListener(this::onClick);
        notif_settings.setOnClickListener(this::onClick);
        terms_services.setOnClickListener(this::onClick);
        safety.setOnClickListener(this::onClick);
        get_help.setOnClickListener(this::onClick);
        payment.setOnClickListener(this::onClick);
        feedback.setOnClickListener(this::onClick);
        security.setOnClickListener(this::onClick);
        privacy.setOnClickListener(this::onClick);

        ////View of Full Name of User////////////
        assert user != null;
        fullName.setText(user.getDisplayName());

        /////////////Underline Design/////////////
        timeline.setPaintFlags(timeline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        return v;
    }

    @SuppressLint("NonConstantResourceId")
    private void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_timeline:
                Intent intent = new Intent(getActivity(), Timeline.class);
                startActivity(intent);
                break;

            case R.id.btn_personal_info:
                Intent intent1 = new Intent(getActivity(), Edit_Profile.class);
                startActivity(intent1);
                break;

            case R.id.securityButton:
                Intent intent2 = new Intent(getActivity(), nav.account.security.security.class);
                startActivity(intent2);
                break;

            case R.id.btn_notification:
                Intent intent3 = new Intent(getActivity(), notif_settings1.class);
                startActivity(intent3);
                break;

            case R.id.btn_payment_payout:
                Intent intent4 = new Intent(getActivity(), nav.account.payment.payment.class);
                startActivity(intent4);
                break;

            case R.id.btn_host_exp:
                Intent intent5 = new Intent(getActivity(), Manage_Hosting.class);
                startActivity(intent5);
                break;

            case R.id.btn_list_space:
                Intent intent6 = new Intent(getActivity(), start_hosting.class);
                startActivity(intent6);
                break;

            case R.id.btn_how_works:
                Intent intent7 = new Intent(getActivity(), letngo_support.class);
                startActivity(intent7);
                break;

            case R.id.btn_safety_center:
                Intent intent8 = new Intent(getActivity(), safety_center.class);
                String user = "account_user";
                intent8.putExtra("user", user);
                startActivity(intent8);
                break;

            case R.id.btn_get_help:
                Intent intent9 = new Intent(getActivity(), GetHelp.class);
                String userhelp = "account_user";
                intent9.putExtra("user", userhelp);
                startActivity(intent9);
                break;

            case R.id.btn_feedback:
                Intent intent10 = new Intent(getActivity(), Feedback.class);
                startActivity(intent10);
                break;

            case R.id.btn_terms:
                Intent intent11 = new Intent(getActivity(), Terms_Services.class);
                startActivity(intent11);
                break;

                case R.id.btn_privacyPolicy:
                Intent intent12 = new Intent(getActivity(), PrivacyPolicy.class);
                startActivity(intent12);
                break;

            case R.id.btn_logout:
                Intent intent13 = new Intent(getActivity(), HomePage.class);
                FirebaseAuth.getInstance().signOut();
                //Clearing login data from local storage, Following code will be commented temporarily
                //SharedPreferences sp = this.getActivity().getSharedPreferences("Login", MODE_PRIVATE);

                //String ema = sp.getString("ema", null);
                //String pass = sp.getString("pass", null);
                startActivity(intent13);
                break;

        }
    }
}
