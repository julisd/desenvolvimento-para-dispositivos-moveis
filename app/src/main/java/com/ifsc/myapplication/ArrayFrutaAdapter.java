package com.ifsc.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ArrayFrutaAdapter extends ArrayAdapter<Fruta> {
    Context mContext;
    int mLayout;
    Fruta mFrutas[];

    public ArrayFrutaAdapter(@NonNull Context context, int resource, @NonNull Fruta[] objects) {
        super(context, resource, objects);
        mContext = context;
        mLayout = resource;
        mFrutas = objects;
    }

    @NonNull
    @Override
    public View getView(int positton, @NonNull View view, @NonNull ViewGroup parent){
        if(view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(mLayout, parent, false);
        }
        Fruta fruta = getItem(positton);
        TextView tvNome = view.findViewById(R.id.tvNome);
        TextView tvCodigo = view.findViewById(R.id.tvCodigo);
        TextView tvPreco = view.findViewById(R.id.tvPreco);
        TextView tvPrecoVenda = view.findViewById(R.id.tvPrecoVenda);
        ImageView imageView = view.findViewById(R.id.imageView);

        NumberFormat nf = new DecimalFormat("##,##.##");

        tvNome.setText(fruta.getNome());
        tvCodigo.setText(Integer.toString(fruta.getCodigo()));
        tvPreco.setText(nf.format(fruta.getPreco()));
        tvPrecoVenda.setText(nf.format(fruta.getPreco_venda()));
        imageView.setImageResource(fruta.getImagem());

        return view;
    }
}
