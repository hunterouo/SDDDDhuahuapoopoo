package com.example.wang.huntergod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
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
    String[] name ={

    };
    int[] cat = {

    };
    String[] sex ={

    };
    String[] adopt ={

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

        return super.onCreateView(inflater, container, savedInstanceState);
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
