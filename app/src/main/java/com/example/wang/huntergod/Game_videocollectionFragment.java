package com.example.wang.huntergod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Game_videocollectionFragment extends Fragment {


    public Game_videocollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_videocollection, container, false);
        // Inflate the layout for this fragment

        VideoView video01= (VideoView) view.findViewById(R.id.videoView0001);
        VideoView video02= (VideoView) view.findViewById(R.id.videoView0002);
        VideoView video03= (VideoView) view.findViewById(R.id.videoView0003);

        video01.setVideoPath("storage/emulated/0/Movies/cat1/eat/eat1 +.mp4");
        video02.setVideoPath("storage/emulated/0/Movies/cat1/eat/eat2 +.mp4");
        video03.setVideoPath("storage/emulated/0/Movies/cat1/eat/eat3 +.mp4");
        return view;


    }

}
