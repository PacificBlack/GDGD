package com.pacificblack.ganardinero.ui.aplicaciones.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.aplicaciones.clases.Aplicaciones;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AplicacionesAdaptador extends RecyclerView.Adapter<AplicacionesAdaptador.AplicacionHolder> implements View.OnClickListener {

    private final List<Aplicaciones> listaAplicaciones;

    private View.OnClickListener listener;


    public AplicacionesAdaptador(ArrayList<Aplicaciones> listaAplicaciones) {
        this.listaAplicaciones = listaAplicaciones;
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
    public AplicacionesAdaptador.AplicacionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_aplicaciones, null, false);
        view.setOnClickListener(this);
        return new AplicacionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AplicacionesAdaptador.AplicacionHolder holder, int position) {
        if (listaAplicaciones.get(position).getImagenes() != null) {

            Picasso.get().load(listaAplicaciones.get(position).getImagenes()[0])
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaAplicaciones.get(position).getTitulo());
        holder.descripcion.setText(listaAplicaciones.get(position).getDes1());
        holder.fecha.setText(listaAplicaciones.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return listaAplicaciones.size();
    }

    public class AplicacionHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public AplicacionHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_row_aplicaciones);
            descripcion = itemView.findViewById(R.id.descripcion_row_aplicaciones);
            fecha = itemView.findViewById(R.id.fecha_row_aplicaciones);
            imagen = itemView.findViewById(R.id.imagen_row_aplicaciones);
        }
    }
}
