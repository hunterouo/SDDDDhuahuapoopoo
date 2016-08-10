package com.example.wang.huntergod;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;


public class midinforActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midinfor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        NavigationView navigationView=null;
        Toolbar toolbar = null;


        FirstFragment fragment = new FirstFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("APP");

        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (resultCode == 1001) {
            Bundle data = new Bundle();//Use bundle to pass data
            data.putString("homename",  resultData.getStringExtra("homename"));
            data.putString("email",  resultData.getStringExtra("email"));
            data.putString("address",  resultData.getStringExtra("address"));
            data.putString("html",  resultData.getStringExtra("html"));
            ((inforFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container)).setTextView(data);
        }
    }

    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.nav_information) {
            inforFragment fragment = new inforFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("中途資訊");

        } else if (id == R.id.nav_catinfor) {
           catinforFragment fragment = new catinforFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            /*Intent intent = new Intent();
            intent.setClass(this , catinforActivity.class);
            startActivity(intent);*/
            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("貓咪資訊");


        } else if (id == R.id.nav_videoupload) {
            videouploadFragment videouploadFragment = new videouploadFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, videouploadFragment);
            fragmentTransaction.commit();

           // mFragmentTransaction.replace(R.id.fragment_container,new TabFragment()).commit();

            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("影片上傳");


        } else if (id == R.id.nav_videoget) {
            videogetFragment fragment = new videogetFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("收穫分享");

        }
        else if(id == R.id.logout){
            Intent intent = new Intent();
            intent.setClass(this,firstActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
