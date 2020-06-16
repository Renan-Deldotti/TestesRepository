package com.example.testparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewm1,textViewm2,textViewm3;

    private List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carList = new ArrayList<>();

        String carName = "Fusca";
        String carColor = "Green";
        String carBrand = "Volkswagen";

        carList.add(new Car(carName,carColor,carBrand));

        textViewm1 = findViewById(R.id.textView_main1);
        textViewm2 = findViewById(R.id.textView_main2);
        textViewm3 = findViewById(R.id.textView_main3);

        textViewm1.setText(carName);
        textViewm1.setText(carColor);
        textViewm1.setText(carBrand);
    }

    public void SendToNext(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(SecondActivity.EXTRA_PARCELABLE,carList.get(0));
        startActivity(intent);
    }
}