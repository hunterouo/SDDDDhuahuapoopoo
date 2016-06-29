package com.example.wang.huntergod;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class midinforActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midinfor);


        TextView textview3 = (TextView) findViewById(R.id.textView3);
        TextView textview6 = (TextView) findViewById(R.id.textView6);
        TextView textview7 = (TextView) findViewById(R.id.textView7);
        TextView textview8 = (TextView) findViewById(R.id.textView8);
        TextView textview9 = (TextView) findViewById(R.id.textView9);
        TextView textview10 = (TextView) findViewById(R.id.textView10);
        TextView textview11 = (TextView) findViewById(R.id.textView11);
        TextView textview12 = (TextView) findViewById(R.id.textView12);

        textview3.setTextSize(20.0f);
        textview6.setTextSize(20.0f);
        textview7.setTextSize(20.0f);
        textview8.setTextSize(20.0f);
        textview9.setTextSize(20.0f);
        textview10.setTextSize(20.0f);
        textview11.setTextSize(20.0f);
        textview12.setTextSize(20.0f);






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("中途資訊");

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_border_color_black_24dp);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.midinfor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();


        if (id == R.id.nav_information) {

        } else if (id == R.id.nav_catinfor) {


        } else if (id == R.id.nav_videoupload) {


        } else if (id == R.id.nav_videoget) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
