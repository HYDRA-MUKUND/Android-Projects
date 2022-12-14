package com.hydra.firebasetutone;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class adpatorforpage  extends FragmentPagerAdapter {

    private int tabcount;
    public adpatorforpage(@NonNull FragmentManager fm, int behavior,int tabcount) {
        super(fm, behavior);
        this.tabcount=tabcount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Login();
            case 1:
                return new registerfr();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
