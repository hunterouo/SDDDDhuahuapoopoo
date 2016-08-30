package com.example.wang.huntergod;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class videogetFragment extends Fragment {
    private TextView text;
    private TextView text1;
    private ImageView text2;

    public videogetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videoget, container, false);
        text = (TextView)view.findViewById(R.id.textView32);
        text1 = (TextView)view.findViewById(R.id.textView33);
        text2 = (ImageView)view.findViewById(R.id.imageViewA);
        Button button20 = (Button) view.findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AddvideoGetActivity.class);

                startActivity(intent);
            }
        });
        return view;
    }
    public void setTextView( Bundle savedInstanceState)
    {
        String date = savedInstanceState.getString("date");
        String share = savedInstanceState.getString("share");
        Uri image=Uri.parse("image");
                //= savedInstanceState.getString("image");

        text.setText( date );
        text1.setText( share );
        text2.setImageURI( image );

    }

}
