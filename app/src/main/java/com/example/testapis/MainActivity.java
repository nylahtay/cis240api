package com.example.testapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        String page = new Communicator().executeHttpGet("Some URL");
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

 */

    }
}