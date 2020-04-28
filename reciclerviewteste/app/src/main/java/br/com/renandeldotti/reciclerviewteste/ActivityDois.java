package br.com.renandeldotti.reciclerviewteste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActivityDois extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dois);
        if (getIntent().hasExtra("Extra")) {
            Log.e("EWONQEQO", "" + getIntent().getStringExtra("Extra"));
            TextView textView = findViewById(R.id.textView);
            textView.setText(getIntent().getStringExtra("Extra"));
            setTitle("Activity Dois");
        }else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.settings_container, new SettingsFragment())
                    .commit();
            setTitle(R.string.settings);
        }
    }
}
