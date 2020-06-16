package com.example.testparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_PARCELABLE = "Extra_Parcelable";

    private TextView textView1,textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Car car = intent.getParcelableExtra(EXTRA_PARCELABLE);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        textView1.setText(car.getName());
        textView1.setText(car.getColor());
        textView1.setText(car.getBrand());
    }
}