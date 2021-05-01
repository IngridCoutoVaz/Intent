package br.edu.ifsp.scl.pdm.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.edu.ifsp.scl.pdm.Intent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //Instancia da classede view binding
    private ActivityMainBinding activityMainBinding;

    //constante para passgaem de parãmetro e retorno
    public static final String PARAMETRO = "PARAMETRO";

    //Request code para a MainActivity
    private final int OUTRA_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        getSupportActionBar().setTitle("Tratando Intents");
        getSupportActionBar().setSubtitle("Tem subtítulo também");

        setContentView(activityMainBinding.getRoot());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.outraActivityMi:
                //Abrir a OutraActivity
                //Intent outraActivityIntent = new Intent(this, OutraActivity.class);
                Intent outraActivityIntent = new Intent("RECEBER_E_RETORNAR_ACTION");

                //Forma 1 de passagem de parãmetros
                //Bundle parametrosBundle = new Bundle();
                //parametrosBundle.putString(PARAMETRO, activityMainBinding.parametroEt.getText().toString());
                //outraActivityIntent.putExtras(parametrosBundle);

                //Forma 2 de passgaem de parâmetros
                outraActivityIntent.putExtra(PARAMETRO, activityMainBinding.parametroEt.getText().toString());

                startActivityForResult(outraActivityIntent, OUTRA_ACTIVITY_REQUEST_CODE);

                return true;
            case R.id.viewMi:
                //Abrir navegador
                Intent abrirNavegadorIntent = new Intent(Intent.ACTION_VIEW);
                abrirNavegadorIntent.setData(Uri.parse(activityMainBinding.parametroEt.getText().toString()));
                startActivity(abrirNavegadorIntent);
            case R.id.callMi:
                //Fazer uma ligação
                verifyCallPhonePermission();
                return true;
        }
        return false;
    }

    private void verifyCallPhonePermission() {
        Intent ligarIntent = new Intent(Intent.ACTION_CALL);
        ligarIntent.setData(Uri.parse("tel: " + activityMainBinding.parametroEt.getText().toString()));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                //Usuário já concedeu a permissão
                startActivity(ligarIntent);
            } else {
                //Solicitar permissão para o usuário em tempo de de execução
            }
        }else{
            //A permissão foi solicitada no Manifest
            startActivity(ligarIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OUTRA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            //Recebendo retorno da OutraActivity
            Toast.makeText(this, "Voltou para MainActivity", Toast.LENGTH_SHORT).show();

            String retorno = data.getStringExtra(OutraActivity.RETORNO);
            if(retorno != null){
                activityMainBinding.retornoTV.setText(retorno);
            }
        }
    }
}