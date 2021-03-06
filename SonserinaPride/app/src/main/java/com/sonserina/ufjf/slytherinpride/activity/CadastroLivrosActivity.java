package com.sonserina.ufjf.slytherinpride.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sonserina.ufjf.slytherinpride.R;
import com.sonserina.ufjf.slytherinpride.adapter.LivrosAdapter;
import com.sonserina.ufjf.slytherinpride.helper.LivrosHelper;
import com.sonserina.ufjf.slytherinpride.models.Livro;

import java.util.Calendar;

/**
 * Created by thassya on 21/10/17.
 */

public class CadastroLivrosActivity extends AppCompatActivity {
    private Button btnSalvarLivro;
    private Button btnVoltarLivro;
    private EditText txtTitulo;
    private EditText txtEditora;
    private EditText txtAno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_livro);

        btnSalvarLivro = (Button) findViewById(R.id.btnSalvarLivro);
        btnVoltarLivro = (Button) findViewById(R.id.btnVoltarLivro);
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtEditora = (EditText) findViewById(R.id.txtEditora);
        txtAno = (EditText) findViewById(R.id.txtAno);

        btnSalvarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = txtTitulo.getText().toString();
                String editora = txtEditora.getText().toString();
                String ano = txtAno.getText().toString();

                if (titulo.isEmpty()) {
                    txtTitulo.setError(getResources().getString(R.string.tituloObrigatorio));
                    txtTitulo.requestFocus();
                }
                if (editora.isEmpty()) {
                    txtEditora.setError(getResources().getString(R.string.editoraObrigatorio));
                    txtEditora.requestFocus();
                }
                if (ano.isEmpty()) {
                    txtAno.setError(getResources().getString(R.string.anoObrigatorio));
                    txtAno.requestFocus();
                }
                else if(!isParsable(ano)){
                    txtAno.setError(getResources().getString(R.string.anoInteiro));
                    txtAno.requestFocus();
                }
                else if (Integer.parseInt(ano) > Calendar.getInstance().get(Calendar.YEAR)) {
                    txtAno.setError(getResources().getString(R.string.anoValido));
                    txtAno.requestFocus();
                }

                else {
                    Livro livro = new Livro(titulo, editora, Integer.parseInt(ano));
                    LivrosHelper.getInstance().addLivro(livro);
                    txtTitulo.setText("");
                    txtEditora.setText("");
                    txtAno.setText("");
                    txtTitulo.requestFocus();

                    Toast.makeText(CadastroLivrosActivity.this, "Livro cadastrado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static boolean isParsable(String input){
        boolean parsable = true;
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;
    }
}
