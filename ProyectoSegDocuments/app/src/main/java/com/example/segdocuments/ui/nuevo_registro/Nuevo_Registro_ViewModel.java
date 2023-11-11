package com.example.segdocuments.ui.nuevo_registro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Nuevo_Registro_ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Nuevo_Registro_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Nuevo Registro");
    }

    public LiveData<String> getText() {
        return mText;
    }
}