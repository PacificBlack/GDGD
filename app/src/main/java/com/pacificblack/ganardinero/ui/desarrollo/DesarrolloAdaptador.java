package com.pacificblack.ganardinero.ui.desarrollo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.desarrollo.clase.Desarrollo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DesarrolloAdaptador extends RecyclerView.Adapter<DesarrolloAdaptador.DesarrolloHolder> implements View.OnClickListener {
    private final List<Desarrollo> listaDesarrollo;
    private View.OnClickListener listener;

    public DesarrolloAdaptador(List<Desarrollo> listaDesarrollo) {
        this.listaDesarrollo = listaDesarrollo;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public DesarrolloAdaptador.DesarrolloHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_desarrollo, null, false);
        view.setOnClickListener(this);
        return new DesarrolloHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DesarrolloAdaptador.DesarrolloHolder holder, int position) {
        if (listaDesarrollo.get(position).getImagenes() != null) {

            Picasso.get().load(listaDesarrollo.get(position).getImagenes()[0])
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaDesarrollo.get(position).getTitulo());
        holder.descripcion.setText(listaDesarrollo.get(position).getDes1());
        holder.fecha.setText(listaDesarrollo.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return listaDesarrollo.size();
    }

    public class DesarrolloHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public DesarrolloHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_row_desarrollo);
            descripcion = itemView.findViewById(R.id.descripcion_row_desarrollo);
            fecha = itemView.findViewById(R.id.fecha_row_desarrollo);
            imagen = itemView.findViewById(R.id.imagen_row_desarrollo);
        }
    }
}
