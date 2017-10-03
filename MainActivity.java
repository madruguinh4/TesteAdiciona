package projetoteste.testeadiciona;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;


    Button adicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText fone = (EditText) findViewById(R.id.edFone);

        adicionar = (Button) findViewById(R.id.btnAdd);


        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Carregando .....");
                dialog.setCancelable(false);
                dialog.show();
                Pessoas pessoas = new Pessoas();
                pessoas.setNome(nome.getText().toString());
                pessoas.setFone(fone.getText().toString());

                CrudRest crudRest = CrudRest.retrofit.create(CrudRest.class);
                final retrofit2.Call<Void> call = crudRest.inserePessoa(pessoas);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Livro inserido com sucesso", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
