package com.example.androidactivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int clickCounter = 0;
    private String headerValue = "Header";
    private int headerTextBgColor = R.color.white;
    private int[] colorArray = {
            R.color.black,
            R.color.white,
            R.color.orange,
            R.color.green,
            R.color.blue,
            R.color.red
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CREATE", "Hello TAMK activity onCreate");
        setContentView(R.layout.activity_main);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        clickCounter = savedInstanceState.getInt("COUNTER_VALUE", 0);
        headerValue = savedInstanceState.getString("HEADER_VALUE", "Header");
        headerTextBgColor = savedInstanceState.getInt("HEADER_BG_COLOR", R.color.white);

        TextView counterText = findViewById(R.id.counterTextView);
        counterText.setText(getString(R.string.counter_textView) + " " + clickCounter);

        EditText headerText = findViewById(R.id.headerEditText);
        int colorValue = getResources().getColor(headerTextBgColor, null);
        headerText.setBackgroundColor(colorValue);
    }
    @Override
    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("COUNTER_VALUE", clickCounter);
        bundle.putString("HEADER_VALUE", headerValue);
        bundle.putInt("HEADER_BG_COLOR", headerTextBgColor);
    }

    public void buttonClicked(View view) {
        clickCounter++;
        TextView counterText = findViewById(R.id.counterTextView);
        counterText.setText(getString(R.string.counter_textView) + " " + clickCounter);

        int randomIndex = new Random().nextInt(colorArray.length);
        headerTextBgColor = colorArray[randomIndex];
        EditText headerText = findViewById(R.id.headerEditText);
        int colorValue = getResources().getColor(headerTextBgColor, null);
        headerText.setBackgroundColor(colorValue);

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("PAUSE", "Hello TAMK activity onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("RESUME", "Hello TAMK activity onResume");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("DESTROY", "Hello TAMK activity onDestroy");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("START", "Hello TAMK activity onStart");
    }

}