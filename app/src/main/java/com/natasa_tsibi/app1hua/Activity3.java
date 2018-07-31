package com.natasa_tsibi.app1hua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //List<String> list = new ArrayList<>();
        DBHelper search = new DBHelper(this);
        String uid, dt;

        if (savedInstanceState == null) {
            Bundle info = getIntent().getExtras();
            if (info == null) {
                uid = null;
                dt = null;
            } else {
                uid = info.getString("uid");
                dt = info.getString("dt");
            }
        } else {
            uid = (String) savedInstanceState.getSerializable("uid");
            dt = (String) savedInstanceState.getSerializable("dt");
        }

        ArrayList<String> list = new ArrayList<>();
        List<User> result = search.searchUsers(uid, dt);
        String listItem;

        // get all Results to list
        if(result.size()==0){
            listItem = "";
            listItem+= "No user found with given data";
            list.add(listItem);
        } else {
            for (int i = 0; i < result.size(); i++) {
                listItem = "";
                listItem += "Id: " + result.get(i).getId() + "\n";
                listItem += "User Id: " + result.get(i).getUid() + "\n";
                listItem += "Longitude: " + result.get(i).getLongitute() + "\n";
                listItem += "Latitude: " + result.get(i).getLatitude() + "\n";
                listItem += "Timestamp: " + result.get(i).getTimestamp();
                list.add(listItem);
            }
        }
        ListView lv = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> lvAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        lv.setAdapter(lvAdapter);
    }

}
