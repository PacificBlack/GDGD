package com.pacificblack.ganardinero.ui.workhome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.workhome.clase.WorkHome;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WorkHomeAdaptador extends RecyclerView.Adapter<WorkHomeAdaptador.WorkAdaptador> implements View.OnClickListener {
    private final List<WorkHome> listaWorkHome;
    private View.OnClickListener listener;

    public WorkHomeAdaptador(List<WorkHome> listaWorkHome) {
        this.listaWorkHome = listaWorkHome;
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
    public WorkHomeAdaptador.WorkAdaptador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_workhome, null, false);
        view.setOnClickListener(this);
        return new WorkAdaptador(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkHomeAdaptador.WorkAdaptador holder, int position) {
        if (listaWorkHome.get(position).getImagenes() != null) {

            Picasso.get().load(listaWorkHome.get(position).getImagenes()[0])
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaWorkHome.get(position).getTitulo());
        holder.descripcion.setText(listaWorkHome.get(position).getDes1());
        holder.fecha.setText(listaWorkHome.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaWorkHome.size();
    }

    public class WorkAdaptador extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public WorkAdaptador(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_row_workhome);
            descripcion = itemView.findViewById(R.id.descripcion_row_workhome);
            fecha = itemView.findViewById(R.id.fecha_row_workhome);
            imagen = itemView.findViewById(R.id.imagen_row_workhome);
        }
    }
}
