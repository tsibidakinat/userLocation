package com.natasa_tsibi.app1hua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editID, editUsrId, editLong, editLa, editDt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper myDB = new DBHelper(MainActivity.this);

        // create Objects type EditText
        editID = (EditText) findViewById(R.id.eT_ID);
        editUsrId = (EditText) findViewById(R.id.eT_userID);
        editLong =  (EditText)findViewById(R.id.eT_longitude);
        editLa = (EditText) findViewById(R.id.eT_latitude);
        editDt = (EditText) findViewById(R.id.eT_timestamp);

        // create Objects type Button
        Button btnAdd = (Button) findViewById(R.id.button_add);
        Button btnNext = (Button) findViewById(R.id.button_next);
        Button btnUpD = (Button) findViewById(R.id.button_update);

        // set auto complete dateTime
        SimpleDateFormat autoF = new SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.getDefault());
        final String autoDate = autoF.format(Calendar.getInstance().getTimeInMillis());
        editDt.setText(autoDate);

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkFields()) {
                            int id = editID.getId();
                            String uid = editUsrId.getText().toString();
                            float lng = Float.parseFloat(editLong.getText().toString());
                            float lat = Float.parseFloat(editLa.getText().toString());
                            String ts = editDt.getText().toString();

                            User add = new User(id,uid,lng,lat,ts);
                            boolean isInserted = myDB.insertUser(add);
                            if (isInserted)
                                Toast.makeText(getApplicationContext(),"Data inserted successfully",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(),"Data failed to be inserted",Toast.LENGTH_SHORT).show();

                            // clear EditTexts after insert
                            editUsrId.setText("");
                            editLong.setText("");
                            editLa.setText("");
                            editDt.setText(autoDate);
                        }
                    }
                }
        );

        // move to Activity2
        btnNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent next = new Intent(v.getContext(),Activity2.class);
                        startActivity(next);
                    }
                }
        );

        btnUpD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()) {
                    int id = editID.getId();
                    String uid = editUsrId.getText().toString();
                    float lng = Float.parseFloat(editLong.getText().toString());
                    float lat = Float.parseFloat(editLa.getText().toString());
                    String ts = editDt.getText().toString();

                    User update = new User(id,uid,lng,lat,ts);
                    boolean isInserted = myDB.updateUser(update);
                    if (isInserted)
                        Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"Data failed to be updated",Toast.LENGTH_SHORT).show();

                    // clear EditTexts after insert
                    editUsrId.setText("");
                    editLong.setText("");
                    editLa.setText("");
                    editDt.setText(autoDate);
                }

            }
        });

    }

    // method to check if fields are empty and display error
    private boolean checkFields() {
        if (editUsrId.getText().length() == 0) {
            editUsrId.setError("Field cannot be left blank.");
            return false;
        } else if (editLong.getText().length() == 0) {
            editLong.setError("Field cannot be left blank.");
            return false;
        } else if (editLa.getText().length() == 0) {
            editLa.setError("Field cannot be left blank.");
            return false;
        } else if (editDt.getText().length() == 0) {
            editDt.setError("Field cannot be left blank");
            return false;
        }

        return true;
    }


}