package com.example.tiago.petapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class RegistarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        getWindow().setBackgroundDrawableResource(R.drawable.background);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Verificar se email já se encontra na base dados.
        //Se sim envia mensagem, se nao aceita e fica registado

    }
}
