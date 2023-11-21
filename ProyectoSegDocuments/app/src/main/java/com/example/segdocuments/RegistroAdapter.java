package com.example.segdocuments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder> {

    private List<Registro> registros;

    public RegistroAdapter(List<Registro> registros) {
        this.registros = registros;
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro, parent, false);
        return new RegistroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        Registro registro = registros.get(position);

        // Configurar TextViews con datos de Registro
        holder.textViewDescription.setText(registro.getDescription());
        holder.textViewFecha.setText(String.valueOf(registro.getFecha()));
        // Agrega más configuraciones según sea necesario para otros campos de Registro

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
        return registros.size();
    }

    public static class RegistroViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDescription;
        public TextView textViewFecha;
        // Agrega más TextView según sea necesario para otros campos de Registro

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.descriptionTextView);
            textViewFecha = itemView.findViewById(R.id.fechaTextView);
            // Inicializa más TextView según sea necesario para otros campos de Registro
        }
    }
}
