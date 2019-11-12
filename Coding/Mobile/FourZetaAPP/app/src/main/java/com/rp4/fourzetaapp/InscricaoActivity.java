package com.rp4.fourzetaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rp4.fourzetaapp.Connection.HttpService;
import com.rp4.fourzetaapp.model.Atleta;
import com.rp4.fourzetaapp.model.Categoria;
import com.rp4.fourzetaapp.model.CategoriaParcelable;
import com.rp4.fourzetaapp.model.Circuito;
import com.rp4.fourzetaapp.model.Dupla;
import com.rp4.fourzetaapp.model.Sexo;
import com.rp4.fourzetaapp.model.Torneio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.widget.AdapterView.*;

public class InscricaoActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Spinner spinnerCircuito;
    ArrayAdapter<Circuito> adapterCircuito;
    private Spinner spinnerTorneio;
    ArrayAdapter<Torneio> adapterTorneio;
    private Spinner spinnerCategoria;
    ArrayAdapter<Categoria> adapterCategoria;
    private Spinner spinnerSexo1;
    ArrayAdapter<Sexo> adapterSexo1;
    private Spinner spinnerSexo2;
    ArrayAdapter<Sexo> adapterSexo2;

    private EditText edtNomeAtleta1;
    private EditText edtCpfAtleta1;
    private EditText edtTelAtleta1;
    private EditText edtEmailAtleta1;
    private EditText edtNascimentoAtleta1;

    private EditText edtNomeAtleta2;
    private EditText edtCpfAtleta2;
    private EditText edtTelAtleta2;
    private EditText edtEmailAtleta2;
    private EditText edtNascimentoAtleta2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Inscrição"); //Titulo para ser exibido na sua Action Bar em frente à seta
        setContentView(R.layout.activity_inscricao);

        List<Circuito> circuitos = new ArrayList<>();
        final List<Torneio> torneios = new ArrayList<>();
        final List<Dupla> duplas = new ArrayList<>();

        try {
            List<Circuito> retorno = new HttpService().execute().get();
            circuitos = retorno;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Spinner Circuito
        spinnerCircuito = findViewById(R.id.spinnerCircuito2);
        adapterCircuito = new ArrayAdapter<Circuito>(this, android.R.layout.simple_spinner_item, circuitos);
        adapterCircuito.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerCircuito.setAdapter(adapterCircuito);
        spinnerCircuito.setOnItemSelectedListener(this);
        spinnerCircuito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Circuito circuito = (Circuito) parent.getSelectedItem();
                torneios.clear();
                torneios.addAll(circuito.getTorneios());
                adapterTorneio.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner Torneio
        spinnerTorneio = findViewById(R.id.spinnerTorneio2);
        adapterTorneio = new ArrayAdapter<Torneio>(this, android.R.layout.simple_spinner_item, torneios);
        adapterTorneio.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerTorneio.setAdapter(adapterTorneio);
        spinnerTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Torneio torneio = (Torneio) parent.getSelectedItem();
                for(int i = 0; i < torneio.getDuplas().size(); i++){
                    duplas.add(torneio.getDuplas().get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Spinner Categoria
        spinnerCategoria = findViewById(R.id.spinnerCategoria2);
        adapterCategoria = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, Categoria.values());
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerCategoria.setOnItemSelectedListener(this);


//        INICIO DADOS ATLETA 1
        edtNomeAtleta1 = (EditText) findViewById(R.id.edtNomeAtleta1);
        edtCpfAtleta1 = (EditText) findViewById(R.id.edtCpfAtleta1);
        edtTelAtleta1 = (EditText) findViewById(R.id.edtTelAtleta1);
        edtEmailAtleta1 = (EditText) findViewById(R.id.edtEmailAtleta1);
        edtNascimentoAtleta1 = (EditText) findViewById(R.id.edtNascimentoAtleta1);

        //Spinner Sexo 1
        spinnerSexo1 = findViewById(R.id.spinnerSexo1);
        adapterSexo1 = new ArrayAdapter<Sexo>(this, android.R.layout.simple_spinner_item, Sexo.values());
        adapterSexo1.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerSexo1.setAdapter(adapterSexo1);
        spinnerSexo1.setOnItemSelectedListener(this);
//        FIM DADOS ATLETA 1

//        INICIO DADOS ATLETA 2
        edtNomeAtleta2 = (EditText) findViewById(R.id.edtNomeAtleta2);
        edtCpfAtleta2 = (EditText) findViewById(R.id.edtCpfAtleta2);
        edtTelAtleta2 = (EditText) findViewById(R.id.edtTelAtleta2);
        edtEmailAtleta2 = (EditText) findViewById(R.id.edtEmailAtleta2);
        edtNascimentoAtleta2 = (EditText) findViewById(R.id.edtNascimentoAtleta2);

        //Spinner Sexo 2
        spinnerSexo2 = findViewById(R.id.spinnerSexo2);
        adapterSexo2 = new ArrayAdapter<Sexo>(this, android.R.layout.simple_spinner_item, Sexo.values());
        adapterSexo2.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinnerSexo2.setAdapter(adapterSexo2);
        spinnerSexo2.setOnItemSelectedListener(this);
//        FIM DADOS ATLETA 2


        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Dupla dupla = new Dupla();
                Atleta atleta1 = new Atleta();
                Atleta atleta2 = new Atleta();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                atleta1.setNome(edtNomeAtleta1.getText().toString());
                atleta1.setCpf(edtCpfAtleta1.getText().toString());
                atleta1.setTel(edtTelAtleta1.getText().toString());
                atleta1.setEmail(edtEmailAtleta1.getText().toString());
                atleta1.setSexo(spinnerSexo1.getSelectedItem().toString());
                System.out.println("SALVAR O ATLETA -> " + edtNomeAtleta1.getText().toString() + " Sexo -> " + spinnerSexo1.getSelectedItem().toString());
                try {
                    atleta1.setDataNascimento(sdf.parse(edtNascimentoAtleta1.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

//                System.out.println("SALVAR O ATLETA -> " + edtNomeAtleta1.getText().toString() + " Sexo -> " + spinnerSexo1.getSelectedItem().toString() + " " + atleta1.getDataNascimento().toString());

                atleta2.setNome(edtNomeAtleta2.getText().toString());
                atleta2.setCpf(edtCpfAtleta2.getText().toString());
                atleta2.setTel(edtTelAtleta2.getText().toString());
                atleta2.setEmail(edtEmailAtleta2.getText().toString());
                atleta2.setSexo(spinnerSexo2.getSelectedItem().toString());
                try {
                    atleta2.setDataNascimento(sdf.parse(edtNascimentoAtleta2.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                dupla.setImpedimento(this.setarImpedimento(tela.getComboImpedimento().getSelectedItem().toString()));
                Categoria categoria = (Categoria) spinnerCategoria.getSelectedItem();
                CategoriaParcelable cp = new CategoriaParcelable(categoria.toString());
                dupla.setCategoria(cp.toString());
                dupla.getAtletas().add(atleta1);
                dupla.getAtletas().add(atleta2);
                atleta1.getDuplas().add(dupla);
                atleta2.getDuplas().add(dupla);
                verificaCpf(dupla);

                if(registraDupla(dupla)){
                    //                BottomAlertDialog alertDialog = new BottomAlertDialog();
                    //                alertDialog.show(getSupportFragmentManager(), "SuccessAlertDialog");
                }else{
                    // Notificar erro no salvar
                }

            }
        });

    }

    public boolean registraDupla(Dupla dupla){
        Gson gson = new Gson();

        String duplaJson = gson.toJson(dupla, new TypeToken<Dupla>() {}.getType());

//        System.out.println("DUPLA JSON -> " + duplaJson);

        return true;
    }

    public boolean verificaCpf(Dupla dupla){
        return true;
    }

    public void getSelectCircuito(View v){
        Circuito circuito = (Circuito) spinnerCircuito.getSelectedItem();
    }

    public void displayCircuitoData(Circuito circuito){
        String nome = circuito.getNome();
        String descricao = circuito.getDescricao();

    }

    public void getSelectTorneio(View v){
        Torneio torneio = (Torneio) spinnerTorneio.getSelectedItem();
    }

    public void displayTorneioData(Torneio torneio){
        String nome = torneio.getNome();
        String descricao = torneio.getDescricao();
    }

    public void getSelectCategoria(View v){
        Categoria categoria = (Categoria) spinnerCategoria.getSelectedItem();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    @Override
    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
