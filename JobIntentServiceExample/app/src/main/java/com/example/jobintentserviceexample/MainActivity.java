package com.example.jobintentserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // JobService + IntentService
    // Should be used when developing for API 26+
    // and you want to do service work
    // the class auto starts job or intent services according
    // to the API level

    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.edit_text_input);
    }

    public void enqueueWork(View v) {
        String input = editTextInput.getText().toString();

        Intent serviceIntent = new Intent(this, ExampleJobIntentService.class);
        serviceIntent.putExtra("inputExtra", input);

        ExampleJobIntentService.enqueueWork(this, serviceIntent);
    }
}