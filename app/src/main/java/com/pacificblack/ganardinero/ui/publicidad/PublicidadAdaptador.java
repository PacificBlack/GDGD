package com.pacificblack.ganardinero.ui.publicidad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.publicidad.clase.Publicidad;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PublicidadAdaptador extends RecyclerView.Adapter<PublicidadAdaptador.PublicidadHolder> implements View.OnClickListener {
    private final List<Publicidad> listaPublicidad;
    private View.OnClickListener listener;

    public PublicidadAdaptador(List<Publicidad> listaPublicidad) {
        this.listaPublicidad = listaPublicidad;
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
    public PublicidadAdaptador.PublicidadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_publicidad, null, false);
        view.setOnClickListener(this);
        return new PublicidadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicidadAdaptador.PublicidadHolder holder, int position) {
        if (listaPublicidad.get(position).getImagenes() != null) {

            Picasso.get().load(listaPublicidad.get(position).getImagenes()[0])
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaPublicidad.get(position).getTitulo());
        holder.descripcion.setText(listaPublicidad.get(position).getDes1());
        holder.fecha.setText(listaPublicidad.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return listaPublicidad.size();
    }

    public class PublicidadHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public PublicidadHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_row_publicidad);
            descripcion = itemView.findViewById(R.id.descripcion_row_publicidad);
            fecha = itemView.findViewById(R.id.fecha_row_publicidad);
            imagen = itemView.findViewById(R.id.imagen_row_publicidad);
        }
    }
}
