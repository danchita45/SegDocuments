package com.example.segdocuments.ui.gallery;

import android.app.Person;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.segdocuments.Persona;
import com.example.segdocuments.R;
import com.example.segdocuments.Registro;
import com.example.segdocuments.databinding.FragmentGalleryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GalleryFragment extends Fragment {

private FragmentGalleryBinding binding;

Spinner usuarios;
EditText DescripRegistro;
Button Guardar;
    private ArrayAdapter<String> spinnerAdapter;
    private DatabaseReference databaseReference;
    int IdRegistro;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        usuarios = root.findViewById(R.id.spinner); // Asegúrate de que el ID sea el correcto
        DescripRegistro = root.findViewById(R.id.editTextTextMultiLine); // Asegúrate de que el ID sea el correcto
        Guardar = root.findViewById(R.id.btnGuardar); // Asegúrate de que el ID sea el correcto


        databaseReference = FirebaseDatabase.getInstance().getReference().child("SegDocuments");




        spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usuarios.setAdapter(spinnerAdapter);
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


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String nombre = dataSnapshot.child("nombre").getValue(String.class);
                    if(nombre!=null){
                        spinnerAdapter.add(nombre);
                    }

                }
                spinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registro nuevoRegistro = new Registro();
                nuevoRegistro.setDescription(String.valueOf(DescripRegistro.getText()));
                nuevoRegistro.setIdRegistro(IdRegistro);
                Date fechaActual = Calendar.getInstance().getTime();
                nuevoRegistro.setFecha(fechaActual);
                nuevoRegistro.setIdPersona(01);

                String nuevoRegistroKey = databaseReference.child("Registros").push().getKey();
                databaseReference.child("Registros").child(nuevoRegistroKey).setValue(nuevoRegistro);


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