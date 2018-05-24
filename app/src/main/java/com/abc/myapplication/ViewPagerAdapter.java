package com.abc.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        EventFragment eventFragment = new EventFragment();
        position = position + 1;
        Bundle bundle = new Bundle();

        if(position == 1){ bundle.putString("message", "Daily"); }
        else if(position == 2){ bundle.putString("message", "Weekly"); }
        else if(position == 3){ bundle.putString("message", "Monthly"); }

        eventFragment.setArguments(bundle);

        return eventFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position = position + 1;
        String title = "";

        if(position == 1){ title = "Daily"; }
        else if(position == 2){ title = "Weekly"; }
        else if(position == 3){ title = "Monthly"; }
        else { title = "Fragment :"+position; }

        return title;
    }
}
