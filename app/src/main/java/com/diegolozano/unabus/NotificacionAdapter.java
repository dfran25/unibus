package com.diegolozano.unabus;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificacionAdapter extends RecyclerView.Adapter<NotificacionAdapter.ViewHolder>{

    private String[] localDataSet;

    public NotificacionAdapter(String[] localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public NotificacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notificaciones, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificacionAdapter.ViewHolder holder, int position) {
        holder.enlazar(localDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView etNotificacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etNotificacion = itemView.findViewById(R.id.txt_imte_notificacion);
        }

        public void enlazar(String s) {
            etNotificacion.setText(s);
        }
    }
}
