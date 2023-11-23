package com.example.segdocuments.ui.nuevo_registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segdocuments.R;
import com.example.segdocuments.TableAdapter;
import com.example.segdocuments.TableItem;
import com.example.segdocuments.databinding.FragmentSlideshowBinding;
import com.example.segdocuments.ui.Entrada;
import com.example.segdocuments.ui.salida.Reg_salidaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Nuevo_Registro_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private TableAdapter tableAdapter;


    public static Nuevo_Registro_Fragment newInstance() {
        return new Nuevo_Registro_Fragment();
    }
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg_salida, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Crear una lista de elementos
        List<TableItem> tableItems = new ArrayList<>();

        // Inicializar el adaptador con la lista de elementos
        tableAdapter = new TableAdapter(tableItems,getContext());
        recyclerView.setAdapter(tableAdapter);


        DatabaseReference entradaRef = FirebaseDatabase.getInstance().getReference().child("SegDocuments").child("Salidas");

        // Agregar un listener para obtener los datos
        entradaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Limpiar la lista antes de agregar nuevos elementos
                tableItems.clear();

                // Iterar sobre los hijos del nodo "Entrada" y agregarlos a la lista
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Entrada e = snapshot.getValue(Entrada.class);
                    int num = e.getNumeroCasa();
                    String Fecha = String.valueOf(e.getFechaEntrada());
                    String Hora = String.valueOf(e.getFechaEntrada());
                    TableItem tableItem;
                    if(e.getStatus()!="Terminado"){
                        if(Fecha!="null"){
                            tableItem=  new TableItem(Integer.toString(num),e.getCalle(),e.getPlaca(),e.getFechaEntrada().toString(),e.getFechaEntrada().toString());
                        }else{
                            tableItem =  new TableItem(Integer.toString(num),e.getCalle(),e.getPlaca(),"","");
                        }

                        if (tableItem != null) {
                            tableItems.add(tableItem);
                        }
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}