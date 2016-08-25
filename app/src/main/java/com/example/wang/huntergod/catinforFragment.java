package com.example.wang.huntergod;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class catinforFragment extends Fragment {



   public catinforFragment() {
       // Required empty public constructor
   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catinfor, container, false);
        Button button8 = (Button) view.findViewById(R.id.button8);
        Button button9 = (Button) view.findViewById(R.id.button9);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddCatActivity.class);

                startActivity(intent);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FixCatActivity.class);

                startActivity(intent);
            }
        });




        return view;
    }


}
