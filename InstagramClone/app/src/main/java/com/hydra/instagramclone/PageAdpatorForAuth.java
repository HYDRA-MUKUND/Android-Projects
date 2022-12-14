package com.hydra.instagramclone;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdpatorForAuth extends FragmentPagerAdapter {
    private int authcount;
    public PageAdpatorForAuth(@NonNull FragmentManager fm, int behavior,int authcount) {
        super(fm, behavior);
        this.authcount=authcount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Phonefrag();
            case 1:
                return new EmailFrag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return authcount;
    }
}
