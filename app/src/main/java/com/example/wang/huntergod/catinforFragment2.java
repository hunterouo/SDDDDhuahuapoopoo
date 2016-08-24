package com.example.wang.huntergod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class catinforFragment2 extends ListFragment {
    public catinforFragment2() {
        // Required empty public constructor
    }

    private TextView text;
    private TextView text1;
    private TextView text2;



    String[] name ={

    };
    int[] cat = {R.drawable.blackcat03

    };
    String[] sex ={"母"

    };
    String[] adopt ={"以領養"

    };
    ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HashMap<String,String> map = new HashMap<String, String>();

        for(int i=0;i<name.length;i++){
            map=new HashMap<String, String>();
            map.put("name", "名字 : " + name[i]);
            map.put("sex","性別 : " + sex[i]);
            map.put("adopt","領養："+ adopt[i]);
            map.put("cat", Integer.toString(cat[i]) );
            data.add(map);
        }

        String[] from = { "cat","name","sex","adopt" };
        int[] to = {R.id.cat, R.id.name,R.id.sex,R.id.adopt};
        adapter = new SimpleAdapter(getActivity(),data,R.layout.catinfor_view_content,from , to);
        setListAdapter(adapter);
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        text = (TextView)view.findViewById(R.id.textView2);
        text1 = (TextView)view.findViewById(R.id.textView3);
        text2 = (TextView)view.findViewById(R.id.textView7);


        return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void setTextView( Bundle savedInstanceState)
    {
        String catname = savedInstanceState.getString("catname");
        String catsex = savedInstanceState.getString("catsex");
        String catadopt = savedInstanceState.getString("catadopt");

        text.setText( catname );
        text1.setText( catsex );
        text2.setText( catadopt );

    }

    @Override
    public void onStart() {

        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

                Toast.makeText(getActivity(), data.get(pos).get("name"),Toast.LENGTH_SHORT).show();

            }
        });
    }

}
