package com.example.testapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //button to send off the request
    Button btnGet;

    //three strings for the user input
    String inputCountry, inputMonth, inputDay;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the button
        btnGet = findViewById(R.id.btnGet);

        //this is the textview for the results.
        textView = (TextView) findViewById(R.id.nameView);

        //TODO replace this with the user input
        //temporarily set the country, month and day
        inputCountry = "us";
        inputMonth = "7";
        inputDay = "15";

        //TODO use the button call the method requestGetNames()
        requestGetNames();
    }


    //This method sends a request for the names and populates the textview with the data
    //optional TODO use parameters for the inputs, and return the names as as string instead of populating the textview directly.
    private void requestGetNames()
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        //Build out the url using the user input parameters
        //(Example: https://api.abalin.net/namedays?country=us&month=7&day=15)
        String url ="https://api.abalin.net/namedays?country="+inputCountry+"&month="+inputMonth+"&day="+inputDay;

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the response string.
                        try {
                            //this will grab the JSON object of namedays from the response string
                            JSONObject data = response.getJSONObject("data").getJSONObject("namedays");

                            //this grabs the JSON string from the namedays object
                            String namesString = data.getString(inputCountry);

                            //set the textView to show the names.
                            textView.setText(namesString);

                            //TODO this code isn't use
                            //optional, this will list the names as an String array
                            String[] namesArray = namesString.split(",");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            textView.setText("Error:" + response);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Ooops there was an error... check your data and try again");
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }
}