package com.example.currencyconventer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private float defaultConversionRate_USD_EUR = 0.94f;
    private float defaultConversionRate_EUR_USD = 1.06f;
    public float getConversionRate_EUR_USD(){
        return this.defaultConversionRate_EUR_USD;
    }
    public float getConversionRate_USD_EUR(){
        return this.defaultConversionRate_USD_EUR;
    }
    private float conversionRate = 1.06f;
    public void setConversionRate(float conversionRate){
        this.conversionRate = conversionRate;
    }
    public float getConversionRate() {
        return this.conversionRate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        boolean switchState = intent.getBooleanExtra("SWITCH_STATE", false);

        TextView homeCurrencyTextView = findViewById(R.id.homeCurrencyTextView);
        TextView destinationCurrencyTextView = findViewById(R.id.destinationCurrencyTextView);
        if(switchState == true){
            setConversionRate(getConversionRate_USD_EUR());

            homeCurrencyTextView.setText(R.string.usd);
            destinationCurrencyTextView.setText(R.string.eur);
        }
        else{
            setConversionRate(getConversionRate_EUR_USD());

            homeCurrencyTextView.setText(R.string.eur);
            destinationCurrencyTextView.setText(R.string.usd);
        }

        float conversionRate = intent.getFloatExtra("CONVERSION_RATE", getConversionRate());
        if(conversionRate != getConversionRate_EUR_USD() || conversionRate != getConversionRate_USD_EUR()){
            setConversionRate(conversionRate);
        }
    }


    public void openSettings(View view) {
        Intent intent = new Intent(this, ConverterSettingsActivity.class);

        intent.putExtra("CURRENT_CONVERSION_RATE", getConversionRate());
        startActivity(intent);

    }

    public void convertCurrency(View view) {
        EditText homeCurrencyEditText = findViewById(R.id.homeCurrencyEditText);
        TextView destinationCurrencyText = findViewById(R.id.destinationCurrencyText);

        String homeCurrencyInput = homeCurrencyEditText.getText().toString();
        float homeCurrencyAmount = Float.parseFloat(homeCurrencyInput);

        float destinationCurrency = homeCurrencyAmount * getConversionRate();
        destinationCurrencyText.setText(String.valueOf(destinationCurrency));
    }
}