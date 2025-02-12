package com.example.exemplomatricula.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.exemplomatricula.R;
import com.example.exemplomatricula.controllers.ManipularEstudante;
import com.example.exemplomatricula.models.Estudante;
import com.example.exemplomatricula.models.SessaoUsuario;
import com.google.android.material.button.MaterialButton;

public class LoginFragment extends Fragment {

    private EditText editTextEmail, editTextSenha;
    private MaterialButton botaoLogin, botaoCadastrar;
    private ManipularEstudante manipularEstudante;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflando o layput do fragmento
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        // Referências aos componentes do layout
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        editTextSenha = rootView.findViewById(R.id.editTextSenha);
        botaoLogin = rootView.findViewById(R.id.botaoLogin);
        botaoCadastrar = rootView.findViewById(R.id.botaoCadastrar);

        manipularEstudante = new ManipularEstudante(getContext());

        // Configurando o botão login
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLogin();
            }
        });

        // Configurando o botão de cadastro
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new CadastroFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    private void validarLogin() {
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        // Verificando se email e senha estão vazios
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(getActivity(), "Precisa preencher todos os campos!",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Criando o objeto Estudante
            Estudante estudante = new Estudante(email, senha);

            // Validando login na thread
            manipularEstudante.validarLogin(estudante, new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    // Obtendo o resultado retornado pela validação
                    Estudante estudanteResultado = (Estudante) msg.obj;

                    // Verificando se foi encontrado o estudante
                    if (estudanteResultado != null) {
                        SessaoUsuario.setIdEstudante(estudanteResultado.getId());
                        SessaoUsuario.setNomeEstudante(estudanteResultado.getNome());
                        SessaoUsuario.setEmailEstudante(estudanteResultado.getEmail());

                        Toast.makeText(getActivity(), "Login bem-sucedido!",
                                Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new HomeFragment())
                                .commit();
                    } else {
                        Toast.makeText(getActivity(), "Usuário não cadastrado!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}


