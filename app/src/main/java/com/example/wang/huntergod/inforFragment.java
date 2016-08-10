package com.example.wang.huntergod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class inforFragment extends Fragment {


    public inforFragment() {
        // Required empty public constructor

    }
    private TextView text;
    private TextView text1;
    private TextView text2;
    private TextView text3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Required empty public constructor
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        text = (TextView)view.findViewById(R.id.textView2);
        text1 = (TextView)view.findViewById(R.id.textView3);
        text2 = (TextView)view.findViewById(R.id.textView7);
        text3 = (TextView)view.findViewById(R.id.textView9);
        Button button6 = (Button) view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),editmidinforActivity.class);

                startActivityForResult(intent, 0);
            }
        });


        return view;
    }
    public void setTextView( Bundle savedInstanceState)
    {
        String homename = savedInstanceState.getString("homename");
        String email = savedInstanceState.getString("email");
        String address = savedInstanceState.getString("address");
        String html = savedInstanceState.getString("html");
        text.setText( homename );
        text1.setText( email );
        text2.setText( address );
        text3.setText( html );
    }

}

