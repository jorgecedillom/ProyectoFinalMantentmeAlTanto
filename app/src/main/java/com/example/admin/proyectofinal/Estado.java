package com.example.admin.proyectofinal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

//Clase para ver el estado de las materias
public class Estado extends AppCompatActivity {
    private Spinner materias;
    private ArrayList<String> materiasA;
    private ArrayList<String> estadoMat;
    private TextView resultado;
    private ListView resLis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estado);
        estadoMat= new ArrayList();
        materiasA = new ArrayList();
        resultado = (TextView) findViewById(R.id.tvPrueba);
        resLis = (ListView)  findViewById(R.id.lvRes);
        materias= (Spinner) findViewById(R.id.spinnerMaterias);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor fila = db.rawQuery("SELECT nombreM FROM materias",null);
        while(fila.moveToNext()){
            materiasA.add(fila.getString(0).toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,materiasA);
        materias.setAdapter(adapter);
    }

    //Metodo para regresar a la pantalla principal
    public void regresar(View v){
        Intent intent = new Intent(Estado.this,Principal.class);//El intent es la sesion
        startActivity(intent);

    }

    //Metodo para generar el estado y mostrarlo en la pantalla
    public void generar (View v){
        estadoMat.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,estadoMat);
        resLis.setAdapter(adapter);
        int id=0;
        String nomMaterias = (String) materias.getSelectedItem().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor fila2 = db.rawQuery("select idMateria from materias where nombreM like('"+nomMaterias+"')" ,null);
        if(fila2.moveToFirst())
            id = fila2.getInt(0);
        Cursor fila = db.rawQuery("select nombreR,valor,calificacion from rubros where rubros.idMateria =" +id,null);
        while(fila.moveToNext()){
            estadoMat.add("Rubro: "+fila.getString(0).toString());
            estadoMat.add("Valor: "+fila.getString(1).toString() +"%");
            estadoMat.add("Calificacion: "+fila.getString(2).toString()+"/100");
            estadoMat.add("Obtenido: " +(fila.getDouble(1)*fila.getDouble(2)/100)+"%");
        }
        resLis.setAdapter(adapter);
        db.close();
    }
}
