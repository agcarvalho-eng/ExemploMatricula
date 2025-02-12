package com.example.exemplomatricula.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.exemplomatricula.R;
import com.example.exemplomatricula.models.Estudante;
import com.example.exemplomatricula.controllers.ManipularEstudante;
import com.google.android.material.button.MaterialButton;

public class CadastroFragment extends Fragment {

    private EditText editTextNome1, editTextEmail1, editTextSenha1;
    private MaterialButton botaoCadastrar;

    public CadastroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflando o layout do fragmento
        View rootView = inflater.inflate(R.layout.fragment_cadastro, container, false);

        // Inicializando os componentes da interface
        editTextNome1 = rootView.findViewById(R.id.editNome1);
        editTextEmail1 = rootView.findViewById(R.id.editEmail1);
        editTextSenha1 = rootView.findViewById(R.id.editSenha1);
        botaoCadastrar = rootView.findViewById(R.id.botaoCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validando os campos
                String nome = editTextNome1.getText().toString();
                String email = editTextEmail1.getText().toString();
                String senha = editTextSenha1.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(getContext(), "Precisa preencher todos os campos!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Criando um novo estudante e salvando no banco de dados
                    Estudante estudante = new Estudante(nome, email, senha);
                    ManipularEstudante manipularEstudante = new ManipularEstudante(getContext());
                    manipularEstudante.inserirEstudante(estudante);

                    // Exibindo a mensagem de sucesso
                    Toast.makeText(getContext(), "Cadastro realizado com sucesso!",
                            Toast.LENGTH_SHORT).show();

                    // Limpando os campos
                    editTextNome1.setText("");
                    editTextEmail1.setText("");
                    editTextSenha1.setText("");

                    // Voltando para o fragmento de login
                    getActivity().getSupportFragmentManager().beginTransaction()
                            // Substituindo o fragmento atual pelo LoginFragment
                            .replace(R.id.frameLayout,new LoginFragment())
                            // Permitindo voltar para a tela anterior
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return rootView;
    }
}

