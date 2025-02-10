package com.example.exemplomatricula.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import com.example.exemplomatricula.R;
import com.example.exemplomatricula.controllers.MyDialog;

import android.view.View.OnClickListener;
import android.content.DialogInterface;

public class TelaInicialFragment extends Fragment {

    public TelaInicialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout do fragmento
        View rootView = inflater.inflate(R.layout.fragment_tela_inicial, container, false);

        // Configurar o título e a descrição do curso
        TextView titulo = rootView.findViewById(R.id.titulo);
        titulo.setText(getString(R.string.app_name));

        TextView descricaoCurso = rootView.findViewById(R.id.descricaoCurso);
        descricaoCurso.setText(getString(R.string.descricao_curso));

        // Configurar o botão "Ir para Login"
        Button botaoLogin = rootView.findViewById(R.id.botaoLogin);
        botaoLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para o fragmento de login
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new LoginFragment())
                        // Possibilita voltar ao fragmento anterior
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Configurar o botão de "Explicação do funcionamento"
        Button botaoExplicacao = rootView.findViewById(R.id.botaoExplicacao);
        botaoExplicacao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               mostrarDialog();
            }
        });

        return rootView;
    }

    // Carregando o AlertDialog
    private void mostrarDialog() {
        MyDialog mdf = new MyDialog();
        mdf.show(requireActivity().getSupportFragmentManager(), "dialogo");
    }
}

