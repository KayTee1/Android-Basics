package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean sayHello = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buttonClicked(View view) {
        TextView helloText = findViewById(R.id.helloTextView);
        if(sayHello){
            helloText.setText(R.string.click_to_hello);
        }
        else{
            helloText.setText(R.string.button_clicked);
        }
        sayHello = !sayHello;
    }
}