package com.example.foregroundserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text_input);
    }

    public void startService(View v){
        String input = editText.getText().toString();

        Intent serviceIntent = new Intent(this,ExampleService.class);
        serviceIntent.putExtra("inputExtra",input);

        startService(serviceIntent);
    }
    public void stopService(View v){
        Intent serviceIntent = new Intent(this,ExampleService.class);
        stopService(serviceIntent);
    }
}