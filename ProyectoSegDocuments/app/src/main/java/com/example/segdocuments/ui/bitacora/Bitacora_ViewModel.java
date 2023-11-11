package com.example.segdocuments.ui.bitacora;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Bitacora_ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Bitacora_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Nueva Bitacora");
    }

    public LiveData<String> getText() {
        return mText;
    }
}