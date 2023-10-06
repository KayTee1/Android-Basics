package com.example.currencyconventer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class ConverterSettingsActivity extends AppCompatActivity {
    private float defaultConversionRate_EUR_USD = 1.06f;
    public float getConversionRate_EUR_USD(){
        return this.defaultConversionRate_EUR_USD;
    }
    private float defaultConversionRate_USD_EUR = 0.94f;
    public float getConversionRate_USD_EUR(){
        return this.defaultConversionRate_USD_EUR;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_settings);
        // Reading intent
        Intent intent = getIntent();
        float multiplier = intent.getFloatExtra("CURRENT_CONVERSION_RATE", getConversionRate_EUR_USD());
        EditText conversionRateEditText = findViewById(R.id.conversionRateEditText);
        conversionRateEditText.setText(String.valueOf(multiplier));

    }

    public void backToConverter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Switch converterDirectionSwitch = findViewById(R.id.converterDirectionSwitch);
        Boolean switchState = converterDirectionSwitch.isChecked();

        EditText conversionRateEditText = findViewById(R.id.conversionRateEditText);

        if(switchState == true){
            conversionRateEditText.setText(String.valueOf(getConversionRate_USD_EUR()));
        }
        else{
            conversionRateEditText.setText(String.valueOf(getConversionRate_EUR_USD()));
        }
        String conversionRateInput = conversionRateEditText.getText().toString();
        float conversionRateAmount = Float.parseFloat(conversionRateInput);

        intent.putExtra("CONVERSION_RATE", conversionRateAmount);
        intent.putExtra("SWITCH_STATE", switchState);
        startActivity(intent);
    }
}