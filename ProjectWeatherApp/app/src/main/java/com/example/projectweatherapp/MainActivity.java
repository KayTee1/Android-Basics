package com.example.projectweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String selectedLocation = "Tampere";
    public void setSelectedLocation(String loc){
        this.selectedLocation = loc;
    }
    public String getSelectedLocation(){
        return this.selectedLocation;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        String selectedLocation = intent.getStringExtra("LOCATION_SELECTED");
        setSelectedLocation(selectedLocation);

        TextView currentLocation = findViewById(R.id.selectedLocation);
        currentLocation.setText(getSelectedLocation());

        updateWeather(null);
    }

    public void updateWeather(View view) {
        //TODO: in settings use cityname or location(lat lng)
        // if use location hide city EditText

        String location = getSelectedLocation();
        String API_KEY = " api key here ";
        String WEATHER_URL = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", location, API_KEY);


        StringRequest request = new StringRequest(Request.Method.GET, WEATHER_URL, this::parseWeatherJsonAndUpdateUi, error -> {
            Toast.makeText(this, "Internet Error", Toast.LENGTH_LONG).show();
        });
        Volley.newRequestQueue(this).add(request);
    }

    @SuppressLint("DefaultLocale")
    private void parseWeatherJsonAndUpdateUi(String response){
        try {
            JSONObject weatherJSON = new JSONObject(response);
            String weather = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("main");
            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");
            double windSpeed = weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView weatherTextView = findViewById(R.id.descriptionValueTextView);
            TextView temperatureTextView = findViewById(R.id.temperatureValueTextView);
            TextView windSpeedTextView = findViewById(R.id.windspeedValueTextView);

            weatherTextView.setText(weather);
            temperatureTextView.setText(String.format(" %.1f C", temperature));
            windSpeedTextView.setText(String.format(" %.1f m/s", windSpeed));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, settingsActivity.class);

        startActivity(intent);
    }
}