package login.system;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import nav.account.FragmentAccount;
import nav.categories.FragmentCategories;
import nav.explore.FragmentExplore;
import nav.newsfeed.FragmentWishlist;
import nav.notifications.FragmentNotification;
import com.example.letngo.R;
//import nav.newsfeed.messages;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHompage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hompage);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        replaceFragment(new FragmentExplore());
        bottomNavigationView.setSelectedItemId(R.id.fragmentNewsfeed);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.fragmentNewsfeed:
                    replaceFragment(new FragmentWishlist());
                    break;
                case R.id.fragmentCategories:
                    replaceFragment(new FragmentCategories());
                    break;
                case R.id.fragmentExplore:
                    replaceFragment(new FragmentExplore());
                    break;
                case R.id.fragmentNotification:
                    replaceFragment(new FragmentNotification());
                    break;
                case R.id.fragmentAccount:
                    replaceFragment(new FragmentAccount());
                    break;
            }
            return true;
        });

        //This block of code directs to messages.java class which can be repurposed. For now, it is commented
        //out since it serves no purpose.
        //imageView = findViewById(R.id.imageMessage);
        //imageView.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //show_Message();
            //}
        //});

    }


//    private void show_Message() {
//        Intent intent = new Intent(UserHompage.this, messages.class);
//        startActivity(intent);
//    }


    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlayout,fragment).commit();
    }
    boolean DoublePressToExit=false;
    @Override
    public void onBackPressed() {
        if (DoublePressToExit){
            finishAffinity();
        }else{
            DoublePressToExit=true;
            Toast.makeText(this,"Press again to exit", Toast.LENGTH_SHORT).show();
            Handler handler=new Handler(getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DoublePressToExit=false;
                }
            },2000);
        }
    }
}