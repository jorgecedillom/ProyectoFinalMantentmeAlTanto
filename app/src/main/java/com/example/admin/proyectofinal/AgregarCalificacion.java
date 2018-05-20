package com.example.admin.proyectofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
//Clase para agregar una calificacion
public class AgregarCalificacion extends AppCompatActivity {
    private ArrayList<String> materiasA;
    private ArrayList<String> rubrosA;
    private Spinner materias;
    private Spinner rubros;
    private EditText calificacion;
    private int calf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_calificacion);
        materiasA = new ArrayList();
        rubrosA = new ArrayList();
        materias= (Spinner) findViewById(R.id.spinnerMat);
        rubros= (Spinner) findViewById(R.id.spinnerRubros);
        calificacion= (EditText) findViewById(R.id.et1);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor fila = db.rawQuery("SELECT nombreM FROM materias",null);

        while(fila.moveToNext()){
            materiasA.add(fila.getString(0).toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,materiasA);
        materias.setAdapter(adapter);
        db.close();
    }
    //Metodo para seleccionar una materia de la base de datos
    public void aceptarMateria(View v){

        rubrosA.clear();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,rubrosA);
        rubros.setAdapter(adapter1);
        String nomMaterias = (String) materias.getSelectedItem().toString();
        int id=0;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor fila2 = db.rawQuery("select idMateria from materias where nombreM like('"+nomMaterias+"')" ,null);
        if(fila2.moveToFirst())
            id = fila2.getInt(0);
        Cursor fila3 = db.rawQuery("select nombreR from rubros where rubros.idMateria =" +id,null);

        while(fila3.moveToNext()){
            rubrosA.add(fila3.getString(0).toString());
        }
        rubros.setAdapter(adapter1);
        db.close();
    }

    //Metodo para regresar a la pantalla principal
    public void regresar(View v){
        Intent intent = new Intent(AgregarCalificacion.this,Principal.class);//El intent es la sesion
        startActivity(intent);
    }

    //Metodo para aceptar calificacion y darla de alta en la base de datos
    public void aceptarCalificacion(View v){
       calf = Integer.parseInt(calificacion.getText().toString());
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String materiaN = materias.getSelectedItem().toString();
        int id=0;
        String nrubro = rubros.getSelectedItem().toString();
        Cursor fila2 = db.rawQuery("select idMateria from materias where nombreM like('"+materiaN+"')" ,null);
        if(fila2.moveToFirst())
            id = fila2.getInt(0);
        ContentValues conten = new ContentValues();
        conten.put("calificacion",""+calf+"");
        db.update("rubros",conten,"idMateria = "+id+ " and nombreR like('"+nrubro+"')",null);
        Toast.makeText(this,"Se actualizo la calificacion del rubro: " +nrubro, Toast.LENGTH_SHORT).show();
        db.close();
    }

}
