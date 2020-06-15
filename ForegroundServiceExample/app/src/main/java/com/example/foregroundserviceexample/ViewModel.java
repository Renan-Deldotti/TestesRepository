package com.example.foregroundserviceexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private final static String TEST = "Testing...";
    private MutableLiveData<String> stringLiveData = new MutableLiveData<>();

    public LiveData<String> getStringLiveData() {
        stringLiveData.postValue(TEST);
        return stringLiveData;
    }
}
