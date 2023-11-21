package com.example.segdocuments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<TableItem> tableItems;

    public TableAdapter(List<TableItem> tableItems) {
        this.tableItems = tableItems;
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
                // Manejar el clic del elemento aquí
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableItems.size();
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
