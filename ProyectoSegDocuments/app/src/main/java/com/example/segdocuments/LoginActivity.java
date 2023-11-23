package com.example.segdocuments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.segdocuments.ui.Entrada;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText passwordEditText;
    private Button loginButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        name = findViewById(R.id.editTextName);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = name.getText().toString();
                String password = passwordEditText.getText().toString();
                signInWithEmailPassword(email, password);
            }
        });
    }

    private void signInWithEmailPassword(String email, String password) {
        DatabaseReference personasRef = FirebaseDatabase.getInstance().getReference().child("SegDocuments").child("Personas");

        personasRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Lista para almacenar todos los elementos del nodo
                List<Persona> personasList = new ArrayList<>();

                // Itera sobre todos los hijos del nodo
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Persona persona = snapshot.getValue(Persona.class);
                    personasList.add(persona);
                }

                // Realiza la comparación de nombre y contraseña
                for (Persona persona : personasList) {
                    if (email.equals(persona.getNombre()) && password.equals(persona.getContraseña())) {
                        // Inicio de sesión exitoso
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        return;
                    }
                }

                // No se encontró un usuario con el nombre y la contraseña proporcionados
                Toast.makeText(LoginActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de la base de datos si es necesario
            }
        });
    }
}
