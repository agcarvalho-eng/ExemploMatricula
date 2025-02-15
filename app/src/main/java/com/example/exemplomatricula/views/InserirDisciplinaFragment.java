package com.example.exemplomatricula.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.controllers.ManipularDisciplinas;
import com.example.exemplomatricula.models.Disciplina;

public class InserirDisciplinaFragment extends Fragment {
    private EditText editTextNomeDisciplina;
    private EditText editTextPesoDisciplina;
    private Button botaoInserirDisciplina, botaoVoltarHome;
    private ManipularDisciplinas db_Disciplinas;

    public InserirDisciplinaFragment() {
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
        // Inicializando a instância de ManipularDisciplinas
        db_Disciplinas = new ManipularDisciplinas(getContext());

        // Referenciando os campos de input
        editTextNomeDisciplina = view.findViewById(R.id.editTextInserirNovaDisciplina);
        editTextPesoDisciplina = view.findViewById(R.id.editTextPesoNovaDisciplina);
        botaoInserirDisciplina = view.findViewById(R.id.buttonInserirDisciplina);
        botaoVoltarHome = view.findViewById(R.id.buttonVoltarHome);

        // Configurando o botão de inserção
        botaoInserirDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendo os valores dos campos de input
                String nomeDisciplina = editTextNomeDisciplina.getText().toString().trim();
                String pesoDisciplinaString = editTextPesoDisciplina.getText().toString().trim();

                // Validação dos campos
                if (nomeDisciplina.isEmpty() || pesoDisciplinaString.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double pesoDisciplina;
                try {
                    pesoDisciplina = Double.parseDouble(pesoDisciplinaString);
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Peso inválido, insira um número válido.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificando se a disciplina já existe no banco de dados
                if (db_Disciplinas.existeDisciplina(nomeDisciplina)) {
                    Toast.makeText(getActivity(), "Disciplina já cadastrada.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // Criando o objeto Disciplina e inserindo no banco
                    Disciplina disciplina = new Disciplina(nomeDisciplina, pesoDisciplina);
                    db_Disciplinas.inserirDisciplina(disciplina);

                    // Exibindo uma mensagem de sucesso
                    Toast.makeText(getActivity(), "Disciplina inserida com sucesso.", Toast.LENGTH_SHORT).show();
                }

                // Limpando os campos
                editTextNomeDisciplina.setText("");
                editTextPesoDisciplina.setText("");
            }
        });

        // Configurando o botão de voltar para o HomeFragment
        botaoVoltarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retornando para o HomeFragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new HomeFragment())
                        .commit();
            }
        });
    }

}
