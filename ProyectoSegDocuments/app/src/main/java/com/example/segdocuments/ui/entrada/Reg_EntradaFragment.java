package com.example.segdocuments.ui.entrada;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.segdocuments.R;
import com.example.segdocuments.ui.Entrada;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reg_EntradaFragment extends Fragment {

    private RegEntradaViewModel mViewModel;
    EditText Numero, Placas, Calle;
    ImageButton imgbGuardarEntrada,foto;
    DatabaseReference databaseReference;
    private Uri selectedImageUri;
    ImageButton imgbFoto;
    public static Reg_EntradaFragment newInstance() {
        return new Reg_EntradaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reg_entrada, container, false);
        Numero = rootView.findViewById(R.id.Numero);
        Placas = rootView.findViewById(R.id.Placas);
        Calle = rootView.findViewById(R.id.calle);
        imgbGuardarEntrada = rootView.findViewById(R.id.imgb_Guardar_Entrada);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("SegDocuments");
        imgbGuardarEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarEntrada();
            }
        });


        return rootView;

    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            // Obtener la Uri de la imagen seleccionada
            selectedImageUri = data.getData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegEntradaViewModel.class);
        // TODO: Use the ViewModel
    }
    private void guardarEntrada() {
        // Obtener los valores de los EditText

        String entradaId = databaseReference.child("Entrada").push().getKey();
        // Crear un mapa de valores para la nueva entrada
        Map<String, Object> entradaValues = new HashMap<>();
        int numero = Integer.parseInt(Numero.getText().toString());
        String placas = Placas.getText().toString();
        String calle = Calle.getText().toString();
        final long[] ID = {0};

        Entrada nuevaEntrada = new Entrada();
        String Dia,Mes,Anio;
        Dia = String.valueOf(new Date().getDay());
        Mes = String.valueOf(new Date().getMonth());
        Anio = String.valueOf(new Date().getYear());

        nuevaEntrada.setNumeroCasa(numero);
        nuevaEntrada.setPlaca(placas);
        nuevaEntrada.setCalle(calle);
        nuevaEntrada.setFechaEntrada(Dia + "/" + Mes +"/"+ Anio);
        nuevaEntrada.setStatus("Registrada");
        if (selectedImageUri!=null){

            subirImagenAFirebaseStorage(selectedImageUri);

        }else{
            databaseReference.child("Entrada").child(entradaId).setValue(nuevaEntrada);
        }
        Numero.setText("");
        Placas.setText("");
        Calle.setText("");
        Toast.makeText(getActivity(), "Entrada guardada correctamente", Toast.LENGTH_SHORT).show();
    }


    private void subirImagenAFirebaseStorage(Uri imagenUri) {
        // Obtener una referencia al almacenamiento de Firebase
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        // Crear una referencia única para la imagen en Firebase Storage
        String imageName = "nombre_unico_para_la_imagen";
        StorageReference imageReference = storageReference.child("imagenes/" + imageName);

        // Subir la imagen
        imageReference.putFile(imagenUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // La imagen se subió exitosamente
                    // Obtener la URL de descarga de la imagen
                    imageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        // Ahora, puedes usar la URL de descarga en tu objeto y guardarlo en Firebase Realtime Database
                        guardarEntradaConImagen(uri);
                    });
                })
                .addOnFailureListener(e -> {
                    // Manejar errores si la carga de la imagen falla
                    Toast.makeText(getActivity(), "Error al subir la imagen", Toast.LENGTH_SHORT).show();
                });
    }


    private void guardarEntradaConImagen(Uri imagenUri) {
        String entradaId = databaseReference.child("Entrada").push().getKey();
        Map<String, Object> entradaValues = new HashMap<>();
        int numero = Integer.parseInt(Numero.getText().toString());
        String placas = Placas.getText().toString();
        String calle = Calle.getText().toString();

        Entrada nuevaEntrada = new Entrada();
        nuevaEntrada.setNumeroCasa(numero);
        nuevaEntrada.setPlaca(placas);
        nuevaEntrada.setCalle(calle);
        nuevaEntrada.setStatus("Registrada");


        nuevaEntrada.setImagenUrl(imagenUri.toString());

        // Subir la nueva entrada al nodo "Entrada" con el ID generado automáticamente
        databaseReference.child("Entrada").child(entradaId).setValue(nuevaEntrada);

        Numero.setText("");
        Placas.setText("");
        Calle.setText("");

        Toast.makeText(getActivity(), "Entrada guardada correctamente", Toast.LENGTH_SHORT).show();
    }








}