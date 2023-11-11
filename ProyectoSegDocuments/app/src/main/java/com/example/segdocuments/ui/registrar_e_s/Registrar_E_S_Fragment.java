package com.example.segdocuments.ui.registrar_e_s;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.segdocuments.databinding.FragmentHomeBinding;

public class Registrar_E_S_Fragment extends Fragment {

private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        Registrar_E_S_ViewModel registrarESViewModel =
                new ViewModelProvider(this).get(Registrar_E_S_ViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

            //Muestra el tescto de la claseViewModel
       // final TextView textView = binding.textHome;
       // registrarESViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}