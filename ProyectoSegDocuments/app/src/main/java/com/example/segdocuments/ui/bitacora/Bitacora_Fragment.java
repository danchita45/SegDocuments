package com.example.segdocuments.ui.bitacora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.segdocuments.R;
import com.example.segdocuments.Registro;

import com.example.segdocuments.databinding.FragmentGalleryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class Bitacora_Fragment extends Fragment {

private FragmentGalleryBinding binding;

Spinner usuarios;
EditText DescripRegistro;
Button Guardar;
    private ArrayAdapter<String> spinnerAdapter;
    private DatabaseReference databaseReference;
    int IdRegistro;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Bitacora_ViewModel bitacoraViewModel =
                new ViewModelProvider(this).get(Bitacora_ViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        DescripRegistro = root.findViewById(R.id.editTextTextMultiLine); // Asegúrate de que el ID sea el correcto
        Guardar = root.findViewById(R.id.btnGuardar); // Asegúrate de que el ID sea el correcto

        databaseReference = FirebaseDatabase.getInstance().getReference().child("SegDocuments");


        DatabaseReference registrosReference = databaseReference.child("Registros");


        registrosReference.orderByChild("IdRegistro").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long ultimoId = 0;
                for (DataSnapshot registroSnapshot : dataSnapshot.getChildren()) {
                    Registro registro = registroSnapshot.getValue(Registro.class);
                    if (registro != null) {
                        ultimoId = registro.getIdRegistro();
                    }
                }

                // Incrementa el ID para el nuevo registro
                long nuevoId = ultimoId + 1;
                // Asigna el nuevo ID a tu objeto Registro
                IdRegistro = (int) nuevoId;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Maneja errores en caso de que la consulta falle
            }
        });


        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea un nuevo registro
                Registro nuevoRegistro = new Registro();
                nuevoRegistro.setDescription(String.valueOf(DescripRegistro.getText()));
                nuevoRegistro.setIdRegistro(IdRegistro);
                Date fechaActual = Calendar.getInstance().getTime();
                nuevoRegistro.setFecha(fechaActual);
                String nuevoRegistroKey = databaseReference.child("Registros").push().getKey();
                databaseReference.child("Registros").child(nuevoRegistroKey).setValue(nuevoRegistro);
                Toast.makeText(getContext(), "Se ha Registrado en la bitacora", Toast.LENGTH_LONG).show();
                DescripRegistro.setText("");
            }
        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}