package com.example.exemplomatricula.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.controllers.ManipularDisciplinas;
import com.example.exemplomatricula.models.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class ListarDisciplinasFragment extends Fragment {

    private RecyclerView recyclerView;
    private DisciplinaAdapter disciplinaAdapter;
    private ManipularDisciplinas db_Disciplinas;
    private List<Disciplina> listaDisciplinas;

    public ListarDisciplinasFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflando o layout do fragmento
        return inflater.inflate(R.layout.fragment_inserir_disciplina, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Inicializando o recyclerView
        recyclerView = view.findViewById(R.id.recyclerViewDisciplinas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // Iniciando a manipulação das disciplinas
        db_Disciplinas = new ManipularDisciplinas(getContext());
        listaDisciplinas = new ArrayList<>();

        // Buscando todas as disciplinas no BD
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Obtendo a lista de disciplinas do BD
                listaDisciplinas = db_Disciplinas.listarTodasDisciplinas();

                // Atualizando a UI no thread principal após a busca no BD
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Criando e configurando o Adapter
                        disciplinaAdapter = new DisciplinaAdapter(listaDisciplinas);
                        recyclerView.setAdapter(disciplinaAdapter);
                    }
                });
            }
        }).start();

    }
}

