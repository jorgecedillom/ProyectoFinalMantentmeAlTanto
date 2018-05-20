package com.example.admin.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


//Clase para entrar a la pagina de grace itam
public class DarDeBaja extends AppCompatActivity {

    private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_de_baja);
        wb =(WebView) findViewById(R.id.webBaja);
        wb.loadUrl("http://grace.itam.mx/EDSUP/twbkwbis.P_WWWLogin");
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient( new WebViewClient());

    }

    //Metodo para regresar a la pantalla principal
    public void regresarDarDebaja(View v){
        Intent intent = new Intent(this,Principal.class);//El intent es la sesion
        startActivity(intent);
    }
}
