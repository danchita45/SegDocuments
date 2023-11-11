package com.example.segdocuments.ui.nuevo_registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.segdocuments.databinding.FragmentSlideshowBinding;

public class Nuevo_Registro_Fragment extends Fragment {

private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
            Nuevo_Registro_ViewModel nuevoRegistroViewModel =
                    new ViewModelProvider(this).get(Nuevo_Registro_ViewModel.class);

    binding = FragmentSlideshowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
        //Muestra el tescto de la claseViewModel
        //final TextView textView = binding.textSlideshow;
        //nuevoRegistroViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}