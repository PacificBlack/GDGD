package com.pacificblack.ganardinero.ui.vendiendo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.vendiendo.clase.Vender;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VenderAdaptador extends RecyclerView.Adapter<VenderAdaptador.VenderHolder> implements View.OnClickListener {
    private final List<Vender> listaVender;
    private View.OnClickListener listener;

    public VenderAdaptador(List<Vender> listaVender) {
        this.listaVender = listaVender;
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
    public VenderAdaptador.VenderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vender, null, false);
        view.setOnClickListener(this);
        return new VenderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenderAdaptador.VenderHolder holder, int position) {
        if (listaVender.get(position).getImagenes() != null) {

            Picasso.get().load(listaVender.get(position).getImagenes()[0])
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaVender.get(position).getTitulo());
        holder.descripcion.setText(listaVender.get(position).getDes1());
        holder.fecha.setText(listaVender.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaVender.size();
    }

    public class VenderHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public VenderHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo_row_vender);
            descripcion = itemView.findViewById(R.id.descripcion_row_vender);
            fecha = itemView.findViewById(R.id.fecha_row_vender);
            imagen = itemView.findViewById(R.id.imagen_row_vender);
        }
    }
}
