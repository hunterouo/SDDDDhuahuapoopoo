package com.example.wang.huntergod;


import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class catinforFragment2 extends ListFragment {
    public catinforFragment2() {
        // Required empty public constructor
    }
    String[] name ={"大頭","莉娜"

    };
    int[] cat = {R.drawable.cat001,R.drawable.cat002

    };
    String[] sex ={"母","母"

    };
    String[] adopt ={"已領養","已領養"

    };
    ArrayList<HashMap<String,Object>> data=new ArrayList<HashMap<String, Object>>();
    SimpleAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HashMap<String,Object> map;
        for(int i=0;i<name.length;i++){
            map = new HashMap<String, Object>();
            Drawable catImage = ResourcesCompat.getDrawable(getResources(), cat[i], null);

            map.put("name", "名字 : " + name[i]);
            map.put("sex","性別 : " + sex[i]);
            map.put("adopt","領養："+ adopt[i]);
            map.put("cat", catImage );
            data.add(map);
        }

        String[] from = { "cat","name","sex","adopt" };
        int[] to = {R.id.cat, R.id.name,R.id.sex,R.id.adopt};
        adapter = new SimpleAdapter( getActivity(), data, R.layout.catinfor_view_content, from, to);
        setListAdapter(adapter);
        adapter.setViewBinder(new ViewBinder(){
            public boolean setViewValue(View view,Object data,String textRepresentation){
                if(view instanceof ImageView && data instanceof Drawable){
                    ImageView iv=(ImageView)view;
                    iv.setImageDrawable((Drawable)data);
                    return true;
                }
                else return false;
            }
        });


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {

        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {

                Toast.makeText(getActivity(), (Integer) data.get(pos).get("name"), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void setTextView( Bundle savedInstanceState)
    {
        String catname = savedInstanceState.getString("catname");
        String catgender = savedInstanceState.getString("catgender");
        String catadopt = savedInstanceState.getString("catadopt");
        Uri uri = Uri.parse(savedInstanceState.getString("image"));
        Drawable catImage;
        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
            catImage = Drawable.createFromStream(inputStream, uri.toString() );
        } catch (Exception e) {
            catImage = ResourcesCompat.getDrawable(getResources(), R.drawable.blackcat03, null);
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("name", "名字 : " + catname);
        map.put("sex","性別 : " + catgender);
        map.put("adopt","領養："+ catadopt);
        map.put("cat", catImage );
        data.add(map);
        adapter.notifyDataSetChanged();
    }

}
