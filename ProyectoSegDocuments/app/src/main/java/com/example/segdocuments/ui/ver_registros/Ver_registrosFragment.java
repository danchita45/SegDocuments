package com.example.segdocuments.ui.ver_registros;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.segdocuments.R;
import com.example.segdocuments.Registro;
import com.example.segdocuments.RegistroAdapter;
import com.example.segdocuments.TableAdapter;
import com.example.segdocuments.TableItem;
import com.example.segdocuments.ui.Entrada;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ver_registrosFragment extends Fragment {

    private VerRegistrosViewModel mViewModel;

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private TableAdapter tableAdapter;

    public static Ver_registrosFragment newInstance() {
        return new Ver_registrosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg_salida, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Crear una lista de elementos
        List<TableItem> tableItems = new ArrayList<>();

        // Inicializar el adaptador con la lista de elementos
        tableAdapter = new TableAdapter(tableItems,getContext());
        recyclerView.setAdapter(tableAdapter);


        DatabaseReference entradaRef = FirebaseDatabase.getInstance().getReference().child("SegDocuments").child("Registros");

        // Agregar un listener para obtener los datos
        entradaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Limpiar la lista antes de agregar nuevos elementos
                tableItems.clear();

                // Iterar sobre los hijos del nodo "Entrada" y agregarlos a la lista
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Registro e = snapshot.getValue(Registro.class);
                    int num = e.getIdRegistro();
                    String Fecha = String.valueOf(e.getFecha().toString());
                    TableItem tableItem;

                            tableItem=  new TableItem(String.valueOf(e.getIdRegistro()),String.valueOf(e.getFecha()),e.getDescription(),"","");
                        if (tableItem != null) {
                            tableItems.add(tableItem);
                        }
                }

                // Notificar al adaptador que los datos han cambiado
                tableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores si es necesario
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VerRegistrosViewModel.class);
        // TODO: Use the ViewModel
    }


}
