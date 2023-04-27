package com.ifsc.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.ViewHolderFruta> {
    Context mContext;
    int mResource;
    Fruta[] mDataSet;

    public FrutaAdapter(Context mContext, int mResource, Fruta[] mDataSet) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mDataSet = mDataSet;
    }


    @NonNull
    @Override
    public FrutaAdapter.ViewHolderFruta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View convertView = layoutInflater.inflate(mResource, parent, false);

        return new ViewHolderFruta(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull FrutaAdapter.ViewHolderFruta holder, int position) {
        Fruta fruta = mDataSet[position];

        holder.tvCodigo.setText(Integer.toString(fruta.getCodigo()));
        holder.tvNome.setText(fruta.getNome());
        holder.tvPreco.setText(NumberFormat.getCurrencyInstance().format(fruta.getPreco()));
        holder.tvPrecoVenda.setText(NumberFormat.getCurrencyInstance().format(fruta.getPreco_venda()));
        holder.imageView.setImageResource(fruta.getImagem());
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    public class ViewHolderFruta extends RecyclerView.ViewHolder {
        TextView tvCodigo;
        TextView tvNome;
        TextView tvPreco;
        TextView tvPrecoVenda;
        ImageView imageView;
        public ViewHolderFruta(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById((R.id.tvCodigo));
            tvNome = itemView.findViewById((R.id.tvNome));
            tvPreco = itemView.findViewById((R.id.tvPreco));
            tvPrecoVenda = itemView.findViewById((R.id.tvPrecoVenda));
            imageView = itemView.findViewById((R.id.imageView));
        }
    }
}