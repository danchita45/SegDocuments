package com.example.segdocuments.ui.entrada;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.segdocuments.R;

public class Reg_EntradaFragment extends Fragment {

    private RegEntradaViewModel mViewModel;

    public static Reg_EntradaFragment newInstance() {
        return new Reg_EntradaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reg_entrada, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegEntradaViewModel.class);
        // TODO: Use the ViewModel
    }

}