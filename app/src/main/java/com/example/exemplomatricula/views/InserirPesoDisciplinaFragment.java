package com.example.exemplomatricula.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.controllers.ManipularDisciplinas;
import com.example.exemplomatricula.models.Disciplina;
import com.example.exemplomatricula.models.MyDatabase;
import com.example.exemplomatricula.models.EstudanteDisciplina;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class InserirPesoDisciplinaFragment extends Fragment {

    private MaterialButton botaoSalvar;
    private LinearLayout linearLayoutDisciplinas;
    private List<Disciplina> listaDisciplinas;
    private ArrayList<Integer> disciplinasSelecionadas; // ArrayList para armazenar os IDs das disciplinas
    private ManipularDisciplinas manipularDisciplinas;
    private int idEstudante;  // ID do estudante para salvar as escolhas

    public InserirPesoDisciplinaFragment(int idEstudante) {
        // Recebe o ID do estudante como parâmetro
        this.idEstudante = idEstudante;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflando o layout do fragmento
        return inflater.inflate(R.layout.fragment_inserir_peso_disciplina, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Referenciando os componentes do layout
        botaoSalvar = view.findViewById(R.id.botaoSalvarDisciplinasInserirPeso);

        // Inicializando a variável para o LinearLayout onde os CheckBoxes serão adicionados
        linearLayoutDisciplinas = view.findViewById(R.id.linearLayoutDisciplinas);

        // Inicializando a lista de disciplinas selecionadas
        disciplinasSelecionadas = new ArrayList<>();

        // Inicializando a manipulação de disciplinas
        manipularDisciplinas = new ManipularDisciplinas(getContext());

        // Carregando a lista de disciplinas do banco de dados Room
        carregarDisciplinas();

        // Botão para salvar as disciplinas selecionadas e voltar a Home
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEscolhas();
                // Retornando para o HomeFragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new HomeFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    // Método para carregar as disciplinas a partir do banco de dados Room
    private void carregarDisciplinas() {
        // Recuperando as disciplinas do banco de dados
        List<Disciplina> disciplinas = manipularDisciplinas.listarTodasDisciplinas();

        // Adicionando as disciplinas na lista
        listaDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            listaDisciplinas.add(disciplina);

            // Criando um CheckBox para cada disciplina
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(disciplina.getNome());  // Nome da disciplina
            checkBox.setTag(disciplina);  // Armazenando a disciplina no CheckBox

            // Adicionando o CheckBox ao layout
            linearLayoutDisciplinas.addView(checkBox);

            // Configurando o evento de clique para atualizar a seleção
            checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Disciplina disciplinaSelecionada = (Disciplina) buttonView.getTag();
                    if (isChecked) {
                        // Adicionando a disciplina selecionada ao ArrayList
                        disciplinasSelecionadas.add(disciplinaSelecionada.getId());
                    } else {
                        // Removendo a disciplina do ArrayList
                        disciplinasSelecionadas.remove(Integer.valueOf(disciplinaSelecionada.getId()));
                    }
                }
            });
        }
    }

    // Método chamado quando o botão de salvar é clicado
    private void salvarEscolhas() {
        if (disciplinasSelecionadas.isEmpty()) {
            // Caso nenhuma disciplina tenha sido selecionada
            Toast.makeText(getContext(), "Nenhuma disciplina selecionada!", Toast.LENGTH_SHORT).show();
        } else {
            // Criando a lista de objetos EstudanteDisciplina para salvar no banco de dados
            List<EstudanteDisciplina> estudanteDisciplinas = new ArrayList<>();
            for (Integer idDisciplina : disciplinasSelecionadas) {
                EstudanteDisciplina estudanteDisciplina = new EstudanteDisciplina(idEstudante, idDisciplina);
                estudanteDisciplinas.add(estudanteDisciplina);
            }

            // Salvando as escolhas no banco de dados Room
            manipularDisciplinas.salvarEscolhaDisciplinas(estudanteDisciplinas);

            // Mostrando mensagem de sucesso
            Toast.makeText(getContext(), "Disciplinas selecionadas salvas!", Toast.LENGTH_SHORT).show();
        }

    }
}

