package com.example.exemplomatricula.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;  // Importando RecyclerView

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.models.Disciplina;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewHolder> { // Mudando para RecyclerView.Adapter

    private List<Disciplina> disciplinas;

    public MeuAdapter(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflando o layout para cada item da lista
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_layout_disciplina, parent, false);

        // Criando e retornando o ViewHolder
        return new MeuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeuViewHolder holder, int position) {
        // Definindo os dados para cada item da lista
        Disciplina disciplina = disciplinas.get(position);

        // Definindo o nome da disciplina
        holder.nomeDisciplina.setText("Disciplina: " + disciplina.getNome());

        // Definindo o peso da disciplina
        holder.pesoDisciplina.setText(String.valueOf("Peso: " + disciplina.getPeso()));
    }

    @Override
    public int getItemCount() {
        // Retornando a quantidade de itens na lista
        return disciplinas.size();
    }

    // Definindo o ViewHolder
    public static class MeuViewHolder extends RecyclerView.ViewHolder {
        TextView nomeDisciplina;
        TextView pesoDisciplina;

        public MeuViewHolder(View itemView) {
            super(itemView);

            // Inicializando as views do item
            nomeDisciplina = itemView.findViewById(R.id.textViewNomeDisciplina);
            pesoDisciplina = itemView.findViewById(R.id.textViewPesoDisciplina);
        }
    }
}


