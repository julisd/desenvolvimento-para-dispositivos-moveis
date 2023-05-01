package com.ifsc.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private List<Contato> contatos;

    public ContatoAdapter(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
        Contato contato = contatos.get(position);
        holder.bind(contato);
    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public static class ContatoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNome;
        private TextView txtTelefone;

        public ContatoViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txt_nome);
            txtTelefone = itemView.findViewById(R.id.txt_telefone);
        }

        public void bind(Contato contato) {
            txtNome.setText(contato.getNome());
            txtTelefone.setText(contato.getTelefone());
        }
    }
}
