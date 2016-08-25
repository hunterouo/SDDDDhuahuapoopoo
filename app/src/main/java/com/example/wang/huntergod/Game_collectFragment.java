package com.example.wang.huntergod;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Game_collectFragment extends Fragment {
    private ProgressBar progressBar;
    private int progressStatus = 2000;
    private TextView bartext1,bartext2;
    private Handler handler = new Handler();



    public Game_collectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_game_collect, container, false);
        // Inflate the layout for this fragment
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        bartext1 = (TextView) view.findViewById(R.id.barText1);
        bartext2 = (TextView) view.findViewById(R.id.barText2);
        // Start long running operation in a background thread

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 3000) {
                    // progressStatus += 1;


                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            bartext1.setText("目前累積："+progressStatus);
                            bartext2.setText("目標分數："+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.

                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return view;
    }

}
