package com.example.letngo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter_Notification extends FragmentStateAdapter {

    public VPAdapter_Notification(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new NotificationHosting();
        }
        return new NotificationTraveller();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}