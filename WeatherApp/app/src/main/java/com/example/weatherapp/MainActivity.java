package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


//import io.github.cdimascio.dotenv.Dotenv;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getWeatherData(View view) {
        String city = "tampere";
        String API_KEY = " APIKEY ";
        String WEATHER_URL = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, API_KEY);


        StringRequest request = new StringRequest(Request.Method.GET, WEATHER_URL, response -> {
            parseWeatherJsonAndUpdateUi( response );
        }, error -> {
            Toast.makeText(this, "Internet Error", Toast.LENGTH_LONG).show();
        });
        Volley.newRequestQueue(this).add(request);
    }
    private void parseWeatherJsonAndUpdateUi(String response){
        try {
            JSONObject weatherJSON = new JSONObject(response);
            //String weather = weatherJSON.getJSONObject("weather").getString("main");
            String weather = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("main");
            double temperature = weatherJSON.getJSONObject("main").getDouble("temp");
            double windSpeed = weatherJSON.getJSONObject("wind").getDouble("speed");

            TextView weatherTextView = findViewById(R.id.weatherDescriptionTextView);
            TextView temperatureTextView = findViewById(R.id.weatherTemperatureTextView);
            TextView windSpeedTextView = findViewById(R.id.weatherWindSpeedTextView);

            weatherTextView.setText(weather);
            temperatureTextView.setText(String.format(" %sC", temperature));
            windSpeedTextView.setText(String.format(" %s m/s", windSpeed));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void openForeca(View view) {
        String foreca = "https://www.foreca.fi";
        Uri forecaUri = Uri.parse(foreca);
        Intent intent = new Intent(Intent.ACTION_VIEW, forecaUri);

        try{
            startActivity( intent );
        }
        catch( Exception e){
            Toast.makeText(this, "No Browser", Toast.LENGTH_LONG).show();
        }
    }
}
