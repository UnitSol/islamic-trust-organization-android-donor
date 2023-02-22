package com.example.islamictrustorganization.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.islamictrustorganization.Fragments.CompletedProjectFragment;
import com.example.islamictrustorganization.Fragments.DashboardFragment;
import com.example.islamictrustorganization.Fragments.OnGoingProjectFragment;

public class ViewPageAdapter extends FragmentStateAdapter {

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DashboardFragment();
            case 1:
                return new CompletedProjectFragment();
            case 2:
                return new OnGoingProjectFragment();
            default:
                return new DashboardFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
