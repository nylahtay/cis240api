package com.example.testapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet.findViewById(R.id.btnGet);

        btnGet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(page);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0 ; i < jsonArray.length(); i++ ) {
                    try {
                        JSONObject entry = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // now get the data from each entry
                }
            }
        });
/*

 */

    }
}