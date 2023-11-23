package com.example.segdocuments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segdocuments.ui.Entrada;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<TableItem> tableItems;
    private List<Registro> registrosBD;
    private Context context;





    public TableAdapter(List<TableItem> tableItems, Context context) {
        this.tableItems = tableItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TableItem tableItem = tableItems.get(position);



        // Configurar TextViews con datos de TableItem
        holder.textViewNumCasa.setText(tableItem.getNumCasa());
        holder.textViewCalle.setText(tableItem.getCalle());
        holder.textViewPlacas.setText(tableItem.getPlacas());
        holder.textViewHora.setText(tableItem.getHora());
        holder.textViewFecha.setText(tableItem.getFecha());

        // Configurar el clic del elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tableItem.getPlacas()!=null ){
                    Entrada E = new Entrada();
                    E.setStatus("Terminado");
                    E.setCalle(tableItem.getCalle());
                    E.setPlaca(tableItem.getPlacas());
                    E.setNumeroCasa(Integer.parseInt(tableItem.getNumCasa()));
                    String Dia,Mes,Anio;
                    Dia = String.valueOf(new Date().getDay());
                    Mes = String.valueOf(new Date().getMonth());
                    Anio = String.valueOf(new Date().getYear());
                    E.setFechaSalida(Dia + "/" + Mes +"/"+ Anio);
                    mostrarDialogo(E);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableItems.size();
    }

    private List<Registro> getRegistros(){
        DatabaseReference personasRef = FirebaseDatabase.getInstance().getReference().child("SegDocuments").child("Entrada");
        List<Registro> RegistrosInicial = null;
        personasRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Registro reg = snapshot.getValue(Registro.class);
                    RegistrosInicial.add(reg);
                }
                for (Registro registro : RegistrosInicial) {


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de la base de datos si es necesario
            }
        });
        return RegistrosInicial;
    }


    private void mostrarDialogo(Entrada entrada) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Configuración del AlertDialog
        builder.setMessage("¿Marcar como Salida?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        marcarComoSalida(entrada);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void marcarComoSalida(Entrada entrada) {
        // Obtén la referencia a la ubicación "entradas" en tu base de datos
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("SegDocuments").child("Salidas");
        String nuevaEntradaId = databaseReference.push().getKey();
        databaseReference.child(nuevaEntradaId).setValue(entrada)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mostrarToast("Entrada marcada como salida exitosamente");
                        eliminarUsuarioPorNombre(entrada);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejo de errores
                        mostrarToast("Error al marcar entrada como salida");
                    }
                });
    }


    private void mostrarToast(String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
    private void eliminarUsuarioPorNombre(Entrada entrada) {
        DatabaseReference personasRef = FirebaseDatabase.getInstance().getReference().child("SegDocuments").child("Entrada");

        // Realiza una consulta para encontrar el usuario con el nombre específico
        Query query = personasRef.orderByChild("placa").equalTo(entrada.getPlaca());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejo de errores
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNumCasa;
        public TextView textViewCalle;
        public TextView textViewPlacas;
        public TextView textViewHora;
        public TextView textViewFecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumCasa = itemView.findViewById(R.id.tvNumCasa);
            textViewCalle = itemView.findViewById(R.id.textViewCalle);
            textViewPlacas = itemView.findViewById(R.id.textViewPlacas);
            textViewHora = itemView.findViewById(R.id.textViewHora);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
        }
    }
}
