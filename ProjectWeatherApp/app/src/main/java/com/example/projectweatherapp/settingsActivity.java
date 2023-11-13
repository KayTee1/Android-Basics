package com.example.projectweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class settingsActivity extends AppCompatActivity {
    private String locationSelected = "Tampere";
    public void setLocationSelected(String locSelected){
        this.locationSelected = locSelected;
    }
    public String getLocationSelected(){
        return this.locationSelected;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void backToMain(View view) {
        EditText locationSelector = findViewById(R.id.countrySelectorEditText);

        String selectedLocation = locationSelector.getText().toString().trim();
        if (!selectedLocation.isEmpty()) {
            setLocationSelected(selectedLocation);
        }

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("LOCATION_SELECTED", getLocationSelected());
        startActivity(intent);
    }
}