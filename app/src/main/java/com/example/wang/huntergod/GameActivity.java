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

public class GameActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mmFragmentManager;
    FragmentTransaction mmFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        NavigationView navigationView=null;
        Toolbar toolbar = null;


        Game_firstFragment fragment = new Game_firstFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_game, fragment);
        fragmentTransaction.commit();



        toolbar = (Toolbar) findViewById(R.id.toolbargame);
        toolbar.setTitle("貓咪養成");

        setSupportActionBar(toolbar);

        mmFragmentManager = getSupportFragmentManager();
        mmFragmentTransaction = mmFragmentManager.beginTransaction();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutgame);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view_game);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutgame);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.catgrow) {
            Game_catgrowFragment fragment = new Game_catgrowFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_game, fragment);
            fragmentTransaction.commit();

            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbargame);
            toolbar.setTitle("貓咪養成");

        } else if (id == R.id.catinfor) {
            Game_catinforFragment fragment = new Game_catinforFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_game, fragment);
            fragmentTransaction.commit();
            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbargame);
            toolbar.setTitle("貓咪資訊");


        } else if (id == R.id.collect) {
            Game_collectFragment fragment = new Game_collectFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_game, fragment);
            fragmentTransaction.commit();

            // mFragmentTransaction.replace(R.id.fragment_container,new TabFragment()).commit();

            Toolbar toolbargame = null;
            toolbargame = (Toolbar) findViewById(R.id.toolbargame);
            toolbargame.setTitle("累積集點");


        } else if (id == R.id.videocollection) {
            Game_videocollectionFragment fragment = new Game_videocollectionFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_game, fragment);
            fragmentTransaction.commit();
            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbargame);
            toolbar.setTitle("影片收藏");

        }
        else if (id == R.id.videoget) {
            Game_videogetFragment fragment = new Game_videogetFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_game, fragment);
            fragmentTransaction.commit();
            Toolbar toolbar = null;
            toolbar = (Toolbar) findViewById(R.id.toolbargame);
            toolbar.setTitle("收穫分享");

        }
        else if(id == R.id.logout){
            Intent intent = new Intent();
            intent.setClass(this , firstActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layoutgame);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
