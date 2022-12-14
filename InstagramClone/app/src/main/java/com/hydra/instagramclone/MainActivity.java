package com.hydra.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity  {
TabItem authphone,authemail;
private TextView alreadyhaveacc;
TabLayout tabLayoutforauth;
ViewPager viewPagerforauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.parseColor("#F2357CC8"));
authemail=(TabItem)findViewById(R.id.authemail);
authphone=(TabItem)findViewById(R.id.authphone);
alreadyhaveacc=(TextView)findViewById(R.id.alredayhavelogin);
tabLayoutforauth=(TabLayout)findViewById(R.id.tablayoutforauth);
viewPagerforauth=(ViewPager)findViewById(R.id.viewpagerforauth);
alreadyhaveacc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,LoginAct.class);
        startActivity(intent);
        finish();
    }
});
PageAdpatorForAuth pageAdpatorForAuth=new PageAdpatorForAuth(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayoutforauth.getTabCount());
viewPagerforauth.setAdapter(pageAdpatorForAuth);
tabLayoutforauth.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPagerforauth.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});
viewPagerforauth.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutforauth));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MainActivity.this,LoginAct.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imyim = (InputMethodManager)this.getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imyim.hideSoftInputFromWindow(this.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        return true;
    }
}