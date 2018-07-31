package com.natasa_tsibi.app1hua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    Spinner sp1, sp2;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // create Objects type Spinner
        sp1 = (Spinner) findViewById(R.id.spinner_User);
        sp2 = (Spinner) findViewById(R.id.spinner_Dt);

        search = (Button) findViewById(R.id.button_search); // create Object Button

        DBHelper find = new DBHelper(this);

        ArrayList<String> timestamps = new ArrayList<>();
        ArrayList<String> userids = new ArrayList<>();
        ArrayList<User> allusers = (ArrayList<User>) find.getUsers();

        timestamps.add("-");
        userids.add("");

        for(int i =0 ; i < allusers.size(); i++){
            timestamps.add(allusers.get(i).getTimestamp());
            userids.add(allusers.get(i).getUid());
        }

        ArrayAdapter<String> uidAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,userids);
        ArrayAdapter<String> dtAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,timestamps);

        sp1.setAdapter(uidAdapter);
        sp2.setAdapter(dtAdapter);

        // move to Activity3
        search.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                        if(sp1.getSelectedItem().toString().equals("") && sp2.getSelectedItem().toString().equals("-")){
                            Toast.makeText(getApplicationContext(),"Nothing selected",Toast.LENGTH_SHORT).show();
                        } else {
                            Intent next = new Intent(v.getContext(), Activity3.class);
                            next.putExtra("uid", sp1.getSelectedItem().toString());
                            next.putExtra("dt", sp2.getSelectedItem().toString());
                            startActivity(next);
                        }
                    }

        });

    }


}
