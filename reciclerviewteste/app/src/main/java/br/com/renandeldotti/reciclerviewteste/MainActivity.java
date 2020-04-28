package br.com.renandeldotti.reciclerviewteste;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ShareCompat;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewCounter;
    private static final String LIFECYCLE_KEY = "callback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewCounter = findViewById(R.id.counter);

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(LIFECYCLE_KEY)){
                String updateVal = getResources().getString(R.string.counter)+" = "+savedInstanceState.getString(LIFECYCLE_KEY);
                textViewCounter.setText(updateVal);
            }else {
                textViewCounter.setText("Something else");
            }
        }else{
            String s = getResources().getString(R.string.counter) + " = 1";
            textViewCounter.setText(s);
        }

        // btn1 event click ---------------------------------------------------------------------------------------------------------------------------
        Button bt1 = findViewById(R.id.open_wb);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSearch = "facebook";
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,toSearch);
                startActivity(intent);
            }
        });

        // Constraint layout reference - --------------------------------------------------------------------------------------------------------------------
        ConstraintLayout mainLayout = findViewById(R.id.act_main);

        // Add btn2 ---------------------------------------------------------------------------------------------------------------------------
        //Button bt2 = new Button(this);
        //bt2.setText(getResources().getString(R.string.open_new_intent));
        Button bt2 = findViewById(R.id.open_intent);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityDois.class);
                intent.putExtra("Extra","Olá");
                startActivity(intent);
            }
        });
        //mainLayout.addView(bt2,new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        /** bt3 share button ------------------------------------------------------------------------------------------------------------------------ */
        Button bt3 = findViewById(R.id.shareInfo);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(MainActivity.this)
                .setChooserTitle("Testing share")
                .setType("text/plain")
                .setText("oinewqonsaweq")
                .startChooser();
            }
        });
        Log.e(MainActivity.class.getSimpleName(),"onCreate");
    }

    private void checkForSettings() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean enableNotifications = preferences.getBoolean(getResources().getString(R.string.notifications_key),getResources().getBoolean(R.bool.notifications_default_value));
        boolean enableSendEmail = preferences.getBoolean(getResources().getString(R.string.sendmail_key),false);
        String colorName = preferences.getString(getResources().getString(R.string.color_key),getResources().getString(R.string.color_blue_value));
        Log.e(this.getClass().getSimpleName(),"Allow notifications: "+enableNotifications);
        Log.e(this.getClass().getSimpleName(),"Allow email: "+enableSendEmail);
        Log.e(this.getClass().getSimpleName(),"Selected color: "+colorName);
        TextView placeHolder2 = findViewById(R.id.placeHolderId2);
        if(colorName.equals(getResources().getString(R.string.color_blue_value))){
            placeHolder2.setBackgroundColor(getResources().getColor(R.color.vivid_sky_blue));
        }else{
            placeHolder2.setBackgroundColor(getResources().getColor(R.color.flicker_pink));
        }
        String sInt = preferences.getString(getResources().getString(R.string.size_key),getResources().getString(R.string.size_default)).trim();
        String defaultInt = getResources().getString(R.string.val_12345).trim();
        placeHolder2.setText(String.valueOf((Integer.parseInt(defaultInt)*Integer.parseInt(sInt))));
        TextView orientationTv = findViewById(R.id.orientationTv);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            orientationTv.setText("Landscape");
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            orientationTv.setText("Portrait");
        }else{
            orientationTv.setText("Unknown");
        }
    }

    @Override
    protected void onPause() {
        Log.e(MainActivity.class.getSimpleName(),"onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.e(MainActivity.class.getSimpleName(),"onResume");
        checkForSettings();
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.e(MainActivity.class.getSimpleName(),"onStart");
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        Log.e(MainActivity.class.getSimpleName(),"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.e(MainActivity.class.getSimpleName(),"onStop");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Chamado
        // depois de onStop
        // antes do onDestroy
        super.onSaveInstanceState(outState);
        Random random = new Random();
        int i = random.nextInt(10)+1;
        Log.e(MainActivity.class.getSimpleName(),"onSaveInstanceState");
        if (i > 5)
        outState.putString(LIFECYCLE_KEY,"2");
        else
            outState.putString(LIFECYCLE_KEY,"3");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings){
            Intent settingsIntent = new Intent(this, ActivityDois.class);
            startActivity(settingsIntent);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
