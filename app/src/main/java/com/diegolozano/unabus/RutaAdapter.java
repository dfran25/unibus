package com.diegolozano.unabus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RutaAdapter extends RecyclerView.Adapter<RutaAdapter.ViewHolder>{

    private String[] localDataSet;

    public RutaAdapter(String[] localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public RutaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ruta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.enlazar(localDataSet[position]);

    }


    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView etRuta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etRuta = itemView.findViewById(R.id.txt_imte_ruta);
        }

        public void enlazar(String s) {
            etRuta.setText(s);
        }
    }
}
