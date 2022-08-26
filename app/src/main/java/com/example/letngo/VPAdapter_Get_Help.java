package com.example.letngo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nav.account.Experience_Host_Get_Help;
import nav.account.Guest_Get_Help;
import nav.account.Host_Get_Help;

public class VPAdapter_Get_Help extends FragmentStateAdapter {

    public VPAdapter_Get_Help (@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new Guest_Get_Help();
            case 2:
                return new Host_Get_Help();
        }
        return new Experience_Host_Get_Help();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}