package com.pacificblack.ganardinero.ui.workhome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.workhome.WorkHome;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WorkHomeAdaptador extends RecyclerView.Adapter<WorkHomeAdaptador.WorkAdaptador> implements View.OnClickListener, Filterable {
    List<WorkHome> listaWorkHome;
    List<WorkHome> listaWorkHomeFull;

    public Filter listaWorkHomeFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<WorkHome> filtroListaAplicacion = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtroListaAplicacion.addAll(listaWorkHomeFull);

            } else {
                String filtroparametro = constraint.toString().toLowerCase().trim();
                for (WorkHome itemAplicacion : listaWorkHomeFull) {
                    if (itemAplicacion.getTitulo().toLowerCase().contains(filtroparametro) || itemAplicacion.getDes1().toLowerCase().contains(filtroparametro)) {
                        filtroListaAplicacion.add(itemAplicacion);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtroListaAplicacion;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaWorkHome.clear();
            listaWorkHome.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    private View.OnClickListener listener;

    public WorkHomeAdaptador(List<WorkHome> listaWorkHome) {
        this.listaWorkHome = listaWorkHome;
        listaWorkHomeFull = new ArrayList<>(listaWorkHome);
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

    @Override
    public Filter getFilter() {
        return listaWorkHomeFiltro;
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
        if (listaWorkHome.get(position).getImagen1() != null) {

            Picasso.get().load(listaWorkHome.get(position).getImagen1())
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
