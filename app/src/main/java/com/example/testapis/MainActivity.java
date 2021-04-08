package com.example.testapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

    //three spinners
    Spinner spinnerCountry, spinnerMonth, spinnerDay;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the spinners
        spinnerCountry = findViewById(R.id.cntryspin);
        spinnerDay = findViewById(R.id.dayspinner);
        spinnerMonth = findViewById(R.id.mnthspinner);

        //set the button
        btnGet = findViewById(R.id.btnGet);

        //this is the textview for the results.
        textView = (TextView) findViewById(R.id.nameView);

        //TODO THIS IS TEST DATA Delete when done
        //temporarily set the country, month and day
        inputCountry = "us";
        inputMonth = "7";
        inputDay = "15";

        //TODO This is calling the requestGetNames() with the est data, DELTE when done testing
        requestGetNames();



        //this will check to see if the spinner Country has been changed
        //on change it should update teh inputCountry code based on what country was selected
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinnerCountry.getSelectedItemPosition())
                {
                    case 0:
                        inputCountry = "us";
                        break;
                    case 1:
                        inputCountry = "at";
                        break;
                    case 2:
                        inputCountry = "hr";
                        break;
                    case 3:
                        inputCountry = "es";
                        break;
                    case 4:
                        inputCountry = "it";
                        break;
                    case 5:
                        inputCountry = "fr";
                        break;
                    default:
                        inputCountry = "us";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Update the number of days based on what month is selected.
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //check to see if feb
                if (spinnerMonth.getSelectedItemPosition() == 1)
                {
                    //TODO update the spinner day to only show 29 days

                    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, spinnerDay, R.array.mnthspinner);

                }
                else {
                    //else check to see if selected month is one that has 30 days

                    //set april (4), June (6), September (9) and November(11) to be 30day Months
                    int[] thirdayDayMonths = {4, 6, 9, 11};

                    for (int i = 0; i < thirdayDayMonths.length; i++) {
                        if (spinnerMonth.getSelectedItemPosition() + 1 == thirdayDayMonths[i]) {
                            //TODO update the spinnerDay to show 30 days
                        }
                        else {
                            //otherwise set the days to be 31.

                            //TODO update the spinnerDay to show 31 days again
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //button to send off the request.
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMonth = spinnerMonth.getSelectedItem().toString();
                inputDay = spinnerDay.getSelectedItem().toString();

                requestGetNames();
            }
        });
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

                            //TODO this code isn't use
                            //optional, this will list the names as an String array
                            String[] namesArray = namesString.split(",");
                            String formatedNames = "Names:  ";

                            // Grabs every individual name and puts it in the formated string
                            for (int i = 0; i < namesArray.length; i++){
                                if (i != namesArray.length - 1){
                                    formatedNames += namesArray[i] + ", ";
                                }
                                else {
                                    formatedNames += namesArray[i];
                                }
                            }

                            //set the textView to show the names.
                            textView.setText(formatedNames);

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