package br.edu.ifsp.scl.pdm.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import br.edu.ifsp.scl.pdm.intent2.R;
import br.edu.ifsp.scl.pdm.intent2.databinding.ActivityOutraBinding;

import static br.edu.ifsp.scl.pdm.intent.MainActivity.*;

public class OutraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        br.edu.ifsp.scl.pdm.intent2.databinding.ActivityOutraBinding activityOutraBinding = ActivityOutraBinding.inflate(getLayoutInflater());
        setContentView(activityOutraBinding.getRoot());

        //Recebendo parametros pela forma 1
        Bundle parametrosBundle = getIntent().getExtras();
        if (parametrosBundle !=null) {
            String parametro = parametrosBundle.getString(PARAMETRO, "");
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
        finish(); //Chama na sequência onPause, onStop, onDestroy
    }
}