package com.example.exemplomatricula.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.exemplomatricula.R;
import com.example.exemplomatricula.controllers.ManipularAproveitamentoEscolar;
import com.example.exemplomatricula.controllers.ManipularEstudante;
import com.example.exemplomatricula.models.SessaoUsuario;

public class HomeFragment extends Fragment {

    private TextView textUsuario;
    private ManipularAproveitamentoEscolar db_AE;
    private ManipularEstudante db_Estudante;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitando o menu para este fragmento
        setHasOptionsMenu(true);
        db_Estudante = new ManipularEstudante(getContext());
        db_AE = new ManipularAproveitamentoEscolar(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflando o layout do fragmento Home
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Configurando a toolbar para que exista apenas neste fragmento
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        // Referenciando o textView para exibir nome e aproveitamento escolar
        textUsuario = rootView.findViewById(R.id.textViewUsuario);

        // Buscando as informações do Id do estudante
        int idEstudante = SessaoUsuario.getIdEstudante();

        String nomeEstudante = db_Estudante.obterNomeEstudante(idEstudante);
        float indiceAproveitamento = db_AE.obterAproveitamentoEscolar(idEstudante);

        // Exibindo as informações no TextView
        String mensagem = "Olá " + nomeEstudante + ", seu índice de aproveitamento escolar é: "
                + indiceAproveitamento;
        textUsuario.setText(mensagem);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Mostrar a Toolbar quando o Fragmento Home for exibido
        ((MainActivity) getActivity()).toolbarVisibilidade(true);
    }

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
            Toast.makeText(getActivity(), "Listando Disciplinas", Toast.LENGTH_SHORT).show();
            // Direcionando para o fragmento de Listar Disciplinas
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new ListarDisciplinasFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.inserir_disciplina) {
            Toast.makeText(getActivity(), "Inserindo Disciplina", Toast.LENGTH_SHORT).show();
            // Direcionando para o fragmento Inserir Disciplina
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new InserirDisciplinaFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            Toast.makeText(getActivity(), "Desconectando", Toast.LENGTH_SHORT).show();
            // Direcionando para o fragmento de Tela Login
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, new LoginFragment())
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}

