package com.example.testinstance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    public static final String TEXT_ARGS = "text_args0";
    private static final String TAG = FirstFragment.class.getSimpleName();
    private ArrayList<String> toSaveList;

    public FirstFragment() {
    }

    /**
     * Create a new instance of this fragment
     *
     * @param textChanged the text to change
     * @return a instance of this fragment
     */
    public static FirstFragment newInstance(String textChanged) {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_ARGS, textChanged);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        TextView textView = view.findViewById(R.id.text_view_ff);
        String textChanged;
        if (getArguments() != null) {
            textChanged = getArguments().getString(TEXT_ARGS);
            textView.setText(textChanged);
        }

        toSaveList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            toSaveList.add("String to save + " + i);
        }

        TextView textView1 = view.findViewById(R.id.text_view_list);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("List:\n");
        for (String s : toSaveList) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }
        textView1.setText(stringBuilder.toString());

        Log.e(TAG, "fromPrefs: " + getFromPrefs().toString());

        Button saveInPrefsButton = view.findViewById(R.id.saveInPrefsButton);
        saveInPrefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInPrefs();
            }
        });

        return view;
    }

    private void saveInPrefs() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Some_NAME_MY_APP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(toSaveList);
        editor.putString("LIST_IN_JSON", json);
        editor.apply();

        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<String> getFromPrefs() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Some_NAME_MY_APP", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("LIST_IN_JSON", "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType(); // Use a <PARAMETER> that fits
        ArrayList<String> fromPrefArrayList = gson.fromJson(json, type);
        if (fromPrefArrayList == null) {
            fromPrefArrayList = new ArrayList<>();
            fromPrefArrayList.add("Nothing");
        } else if (fromPrefArrayList.isEmpty()) {
            fromPrefArrayList.add("Wasn't null, but was empty");
        }
        return fromPrefArrayList;
    }
}
