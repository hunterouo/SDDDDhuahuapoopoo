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
   /* String[] name ={"India","Pakistan",  "Sri Lanka",  "China",  "Bangladesh",  "Nepal", "Afghanistan","North Korea","South Korea", "Japan"};
    int[] cat = {
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
    };
    String[] sex = new String[]{
            "Indian Rupee",
            "Pakistani Rupee",
            "Sri Lankan Rupee",
            "Renminbi",
            "Bangladeshi Taka",
            "Nepalese Rupee",
            "Afghani",
            "North Korean Won",
            "South Korean Won",
            "Japanese Yen"
    };
    String[] adopt = new String[]{
            "已領養",
            "未領養",
            "已領養",
            "未領養",
            "已領養",
            "未領養",
            "已領養",
            "未領養",
            "已領養",
            "未領養"
    };
    ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;*/
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



       /* HashMap<String,String> map = new HashMap<String, String>();

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
        setListAdapter(adapter);*/

        return view;
    }



    /*String[] name = new String[] {
            "India",
            "Pakistan",
            "Sri Lanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Afghanistan",
            "North Korea",
            "South Korea",
            "Japan"
    };
    int[] cat = new int[]{
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
            R.drawable.cat,
    };
    String[] sex = new String[]{
            "Indian Rupee",
            "Pakistani Rupee",
            "Sri Lankan Rupee",
            "Renminbi",
            "Bangladeshi Taka",
            "Nepalese Rupee",
            "Afghani",
            "North Korean Won",
            "South Korean Won",
            "Japanese Yen"
    };
    String[] adopt = new String[]{
            "已領養",
            "未領養",
            "已領養",
            "未領養",
            "已領養",
            "未領養",
            "已領養",
            "未領養",
            "已領養",
            "未領養"
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catinfor, container, false);
        // Inflate the layout for this fragment
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("name", "名字 : " + name[i]);
            hm.put("sex","性別 : " + sex[i]);
            hm.put("adopt","領養"+ adopt[i]);
            hm.put("cat", Integer.toString(cat[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "cat","name","sex","adopt" };

        // Ids of views in listview_layout
        int[] to = {R.id.cat, R.id.name,R.id.sex,R.id.adopt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.catinfor_view_content, from, to);

        setListAdapter(adapter);
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
    }  */



}
