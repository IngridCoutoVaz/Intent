package br.edu.ifsp.scl.pdm.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import br.edu.ifsp.scl.pdm.Intent.databinding.ActivityOutraBinding;

import static br.edu.ifsp.scl.pdm.Intent.MainActivity.PARAMETRO;

public class OutraActivity extends AppCompatActivity {
    //Instância da classe ViewBuilding
    private ActivityOutraBinding activityOutraBinding;

    //Constantwe para retorno para a MainActivity
    public static final String RETORNO = "RETORNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOutraBinding = ActivityOutraBinding.inflate(getLayoutInflater());
        setContentView(activityOutraBinding.getRoot());

        //Recebendo parametros pela forma 1
        //Bundle parametrosBundle = getIntent().getExtras();
        //if (parametrosBundle !=null) {
            //String parametro = parametrosBundle.getString(PARAMETRO, "");
            //activityOutraBinding.recebidoTV.setText(parametro);
        //}

        //Recebendo parâmetros pela forma 2
        String parametro = getIntent().getStringExtra(MainActivity.PARAMETRO);
        if(parametro != null){
            activityOutraBinding.recebidoTV.setText(parametro);
        }
        Log.v(getString(R.string.app_name) + "/" + getLocalClassName(), "OnCreate: Iniciando ciclo completo");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(getString(R.string.app_name) + "/" + getLocalClassName(), "OnStart: Iniciando ciclo visível");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(getString(R.string.app_name) + "/" + getLocalClassName(), "OnResume: Iniciando ciclo foreground");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(getString(R.string.app_name) + "/" + getLocalClassName(), "OnPause: Finalizando ciclo foreground");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(getString(R.string.app_name) + "/" + getLocalClassName(), "OnStop: Finalizando ciclo visível");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(getString(R.string.app_name) + "/" + getLocalClassName(), "OnDestroy: Finalizando ciclo completo");
    }

    public void onClick(View view) {
        //Retornando par a MainActivity
        Intent retornoIntent = new Intent();
        retornoIntent.putExtra(RETORNO, activityOutraBinding.retornoEt.getText().toString());
        setResult(RESULT_OK, retornoIntent);

        finish(); //Chama na sequência onPause, onStop, onDestroy
    }
}