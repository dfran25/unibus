package com.diegolozano.unabus;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ParadaAdapter extends RecyclerView.Adapter<ParadaAdapter.ViewHolder>{

    private String[] localDataSet;

    public ParadaAdapter(String[] localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ParadaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parada, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ParadaAdapter.ViewHolder holder, int position) {
        holder.enlazar(localDataSet[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView etParada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etParada = itemView.findViewById(R.id.txt_imte_parada);
        }

        public void enlazar(String s) {
            etParada.setText(s);
        }
    }
}