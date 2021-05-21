package com.pacificblack.ganardinero.ui.criptomining;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.criptomining.clase.Mineria;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MineriaAdaptador extends RecyclerView.Adapter<MineriaAdaptador.MineriaHolder> implements View.OnClickListener{

    private final List<Mineria> listaMineria;
    private View.OnClickListener listener;

    public MineriaAdaptador(List<Mineria> listaMineria) {
        this.listaMineria = listaMineria;
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
    public MineriaAdaptador.MineriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mineria, null, false);
        view.setOnClickListener(this);
        return new MineriaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MineriaAdaptador.MineriaHolder holder, int position) {
        if (listaMineria.get(position).getImagenes() != null) {

            Picasso.get().load(listaMineria.get(position).getImagenes()[0])
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaMineria.get(position).getTitulo());
        holder.descripcion.setText(listaMineria.get(position).getDes1());
        holder.fecha.setText(listaMineria.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaMineria.size();
    }

    public class MineriaHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public MineriaHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo_row_mineria);
            descripcion = itemView.findViewById(R.id.descripcion_row_mineria);
            fecha = itemView.findViewById(R.id.fecha_row_mineria);
            imagen = itemView.findViewById(R.id.imagen_row_mineria);

        }
    }
}
