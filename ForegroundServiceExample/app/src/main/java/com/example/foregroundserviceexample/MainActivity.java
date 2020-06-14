package com.example.foregroundserviceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        ContextCompat.startForegroundService(this,serviceIntent);
    }
    public void stopService(View v){
        Intent serviceIntent = new Intent(this,ExampleService.class);
        Intent musicServiceIntent = new Intent(this,TestMusicService.class);
        stopService(serviceIntent);
        stopService(musicServiceIntent);
    }

    public void startMediaService(View v) {
        Intent musicServiceIntent = new Intent(this,TestMusicService.class);
        ContextCompat.startForegroundService(this,musicServiceIntent);
    }
}