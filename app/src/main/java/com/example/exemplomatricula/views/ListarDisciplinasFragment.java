package com.example.exemplomatricula.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
    private MeuAdapter meuAdapter;
    private ManipularDisciplinas db_Disciplinas;
    private List<Disciplina> listaDisciplinas;
    private Button botaoVoltarHome;

    public ListarDisciplinasFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflando o layout do fragmento
        return inflater.inflate(R.layout.fragment_listar_disciplinas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Inicializando o recyclerView e o button
        recyclerView = view.findViewById(R.id.recyclerViewDisciplinas);
        botaoVoltarHome = view.findViewById(R.id.botaoVoltarHome);

        // Verificando se o recyclerView não está vazio
        if(recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            Toast.makeText(getContext(), "RecyclerView não encontrado!",
                    Toast.LENGTH_SHORT).show();
        }

        // Iniciando a manipulação das disciplinas
        db_Disciplinas = new ManipularDisciplinas(getContext());
        listaDisciplinas = new ArrayList<>();

        // Buscando todas as disciplinas no BD
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Obtendo a lista de disciplinas do BD
                listaDisciplinas = db_Disciplinas.listarTodasDisciplinas();

                // Log para verificar se as disciplinas foram carregadas no BD
                Log.d("ListarDisciplinasFragment", "Lista de disciplinas carregada. Tamanho: " + listaDisciplinas.size());

                // Atualizando a UI no thread principal após a busca no BD
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Criando e configurando o Adapter
                        // Log de confirmação de configuração do Adapter
                        Log.d("ListarDisciplinasFragment", "Criando o Adapter com " + listaDisciplinas.size() + " disciplinas.");
                        meuAdapter = new MeuAdapter(listaDisciplinas);
                        recyclerView.setAdapter(meuAdapter);
                    }
                });
            }
        }).start();

        // Configurando o botão voltar para a Home
        botaoVoltarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retornando para o HomeFragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new HomeFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}

