package com.example.tiago.petapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.background);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Vai ver se o email existe na base de dados
            //Se existir verifica passe

            //Se nao existir - envia mensagem a dizer que email nao registado
    }
}

