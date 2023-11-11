package com.example.segdocuments.ui.registrar_e_s;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Registrar_E_S_ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Registrar_E_S_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Registra un ES");
    }

    public LiveData<String> getText() {
        return mText;
    }
}