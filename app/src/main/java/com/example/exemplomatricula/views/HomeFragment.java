package com.example.exemplomatricula.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.exemplomatricula.R;
import com.example.exemplomatricula.controllers.ManipularAproveitamentoEscolar;
import com.example.exemplomatricula.controllers.ManipularDisciplinas;
import com.example.exemplomatricula.controllers.ManipularEstudante;
import com.example.exemplomatricula.models.SessaoUsuario;

public class HomeFragment extends Fragment {

    private TextView textUsuario;
    //private ManipularAproveitamentoEscolar db_AE;
    //private ManipularEstudante db_Estudante;

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflando o layout do fragmento
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Habilitando o menu para este fragmento
        setHasOptionsMenu(true);
        //db_Estudante = new ManipularEstudante(getContext());
        //db_AE = new ManipularAproveitamentoEscolar(getContext());

        // Configurando a toolbar para que exista apenas neste fragmento
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        // Referenciando o textView para exibir nome e aproveitamento escolar
        textUsuario = view.findViewById(R.id.textViewUsuario);

        // Buscando as informações do estudante logado
        int idEstudante = SessaoUsuario.getIdEstudante();
        String nomeEstudante = SessaoUsuario.getNomeEstudante();
        //double indiceAproveitamento = db_AE.obterAproveitamentoEscolar(idEstudante);

        // Exibindo as informações no TextView
        // COLOCANDO UM ÍNDICE DE APROVEITAMENTO GENÉRICO
        double indiceAproveitamento = 0.0;
        String mensagem = "Olá " + nomeEstudante + ", seu índice de aproveitamento escolar é: "
                + indiceAproveitamento;
        textUsuario.setText(mensagem);

    }

    // Método do ciclo de vida do fragmento (quando iniciar o fragmento)
    @Override
    public void onStart() {
        super.onStart();
        // Mostrar a Toolbar quando o Fragmento Home for exibido
        ((MainActivity) getActivity()).toolbarVisibilidade(true);
    }

    // Método do ciclod e vida do fragmento (quando finalizar o fragmento)
    @Override
    public void onStop() {
        super.onStop();
        // Escondendo a Toolbar quando for outros fragmentos
        ((MainActivity) getActivity()).toolbarVisibilidade(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflando o menu para adicionar as opções na Toolbar
        inflater.inflate(R.menu.menu_home, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Verificando qual item foi selecionado
        if (item.getItemId() == R.id.listar_disciplinas) {
            Toast.makeText(getActivity(), "Listar Disciplinas", Toast.LENGTH_SHORT).show();
            // Direcionando para o fragmento de Listar Disciplinas
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new ListarDisciplinasFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.inserir_disciplina) {
            Toast.makeText(getActivity(), "Inserindo Nova Disciplina!", Toast.LENGTH_SHORT).show();
            // Direcionando para o fragmento Inserir Disciplina
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new InserirDisciplinaFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            Toast.makeText(getActivity(), "Desconectando!", Toast.LENGTH_SHORT).show();
            // Direcionando para o fragmento de Tela Login
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new LoginFragment())
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.inserir_peso) {
            // Ação ao clicar no novo item "Peso"
            Toast.makeText(getActivity(), "Você clicou em Inserir Peso!", Toast.LENGTH_SHORT).show();
            // Direcionando para o fragmento de Tela Login
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new InserirPesoDisciplinaFragment())
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

