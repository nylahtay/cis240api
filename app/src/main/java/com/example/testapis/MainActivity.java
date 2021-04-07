package com.example.testapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    //three strings for the user input
    String inputCountry, inputMonth, inputDay;

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

        //TODO replace this with the user input
        //temporarily set the country, month and day
        inputCountry = "us";
        inputMonth = "7";
        inputDay = "15";

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.abalin.net/namedays?country="+inputCountry+"&month="+inputMonth+"&day="+inputDay;
        String[] names;

// Request a string response from the provided URL.
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            //this will grab the object of namedays
                            JSONObject data = response.getJSONObject("data").getJSONObject("namedays");

                            //this grabs the string from the JSONobject namedays
                            String names = data.getString(inputCountry);
                            textView.setText("Response is: "+ names);

                            //optional, this will list the names as an String array
                            String[] namesArray = names.split(",");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            textView.setText("didn't work" + response);

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