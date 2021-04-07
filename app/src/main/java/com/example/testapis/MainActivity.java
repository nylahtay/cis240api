package com.example.testapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = findViewById(R.id.btnGet);

        /*
        btnGet.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          JSONArray jsonArray = null;
                                          try {
                                              jsonArray = new JSONArray(page);
                                          } catch (JSONException e) {
                                              e.printStackTrace();
                                          }
                                          for (int i = 0; i < jsonArray.length(); i++) {
                                              try {
                                                  JSONObject entry = (JSONObject) jsonArray.get(i);
                                              } catch (JSONException e) {
                                                  e.printStackTrace();
                                              }
                                              // now get the data from each entry
                                          }
                                      }
                                  });
         */


        final TextView textView = (TextView) findViewById(R.id.testText);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.abalin.net/today?country=ru";
        String[] names;

// Request a string response from the provided URL.
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jArry = response.getJSONArray("namedays");
                            textView.setText("Response is: "+ jArry);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);

    }
}