package com.example.wang.huntergod;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wang on 2016/6/28.
 */
public class nav_videoupload extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView=inflater.inflate(R.layout.nav_videoupload,container,false);
        return myView;
    }
}
