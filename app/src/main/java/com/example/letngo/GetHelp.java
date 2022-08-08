package com.example.letngo;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GetHelp extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    VPAdapter_Get_Help get_helpAdapter;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private TextView fullName;


    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);

        tabLayout = findViewById(R.id.get_help);
        viewPager = findViewById(R.id.viewPagerGetHelp);
        back = findViewById(R.id.btn_back_host);
        fullName = findViewById(R.id.Full_name);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        FragmentManager fm = getSupportFragmentManager();
        get_helpAdapter = new VPAdapter_Get_Help(fm, getLifecycle());
        viewPager.setAdapter(get_helpAdapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab get_help) {
                viewPager.setCurrentItem(get_help.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab get_help) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab get_help) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        back.setOnClickListener(v -> onBackPressed());

        if (user == null){
            TabLayout tabLayout;
            ViewPager2 viewPager;
            VPAdapter_Get_Help get_helpAdapter;

            setContentView(R.layout.activity_get_help);

            tabLayout = findViewById(R.id.get_help);
            viewPager = findViewById(R.id.viewPagerGetHelp);
            back = findViewById(R.id.btn_back_host);

            fm = getSupportFragmentManager();
            get_helpAdapter = new VPAdapter_Get_Help(fm, getLifecycle());
            viewPager.setAdapter(get_helpAdapter);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab get_help) {
                    viewPager.setCurrentItem(get_help.getPosition());
                }
                @Override
                public void onTabUnselected(TabLayout.Tab get_help) {}
                @Override
                public void onTabReselected(TabLayout.Tab get_help) {}
            });

            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    tabLayout.selectTab(tabLayout.getTabAt(position));}
            });

            back.setOnClickListener(v -> onBackPressed());
        }
        else{
            fullName.setText(user.getDisplayName());
        }

    }

}