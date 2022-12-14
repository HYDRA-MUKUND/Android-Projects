package com.hydra.firebasetutone;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
EditText email,password;
TabLayout tabLayout;
TabItem loginitem,registeritem;
ViewPager viewPager;
    adpatorforpage ff;
TextView login,newuser,forgotpass;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       tabLayout=(TabLayout)findViewById(R.id.tabLayout);
       loginitem=(TabItem)findViewById(R.id.logintab);
        registeritem=(TabItem)findViewById(R.id.regtab);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        firebaseAuth=FirebaseAuth.getInstance();
ff=new adpatorforpage(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayout.getTabCount());


  viewPager.setAdapter(ff);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

}

}