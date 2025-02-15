package com.example.exemplomatricula.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.models.Disciplina;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private List<Disciplina> disciplinas;

    public DisciplinaAdapter(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @NonNull
    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout para cada item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disciplina, parent, false);
        return new DisciplinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        // Atribui os valores aos elementos de cada item
        Disciplina disciplina = disciplinas.get(position);
        holder.nomeDisciplina.setText(disciplina.getNome());
        holder.pesoDisciplina.setText(String.valueOf(disciplina.getPeso()));
    }

    @Override
    public int getItemCount() {
        return disciplinas.size();
    }

    // ViewHolder para cada item do RecyclerView
    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextView nomeDisciplina;
        TextView pesoDisciplina;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeDisciplina = itemView.findViewById(R.id.textViewNomeDisciplina);
            pesoDisciplina = itemView.findViewById(R.id.textViewPesoDisciplina);
        }
    }
}

