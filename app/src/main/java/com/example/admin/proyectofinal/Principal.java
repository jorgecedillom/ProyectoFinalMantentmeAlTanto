package com.example.admin.proyectofinal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//Clase principal de el programa
public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
    }

    //Metodo que abre la pantalla de Agregar Materia
    public void openAgregarMateria(View v){
        Intent intent = new Intent(Principal.this,AgregarMateria.class);//El intent es la sesion
        startActivity(intent);
    }

    //Metodo que abre la pantalla de Agregar Calificacion
    public void openAgregarCalificacion(View v){
        Intent intent = new Intent(Principal.this,AgregarCalificacion.class);//El intent es la sesion
        startActivity(intent);
    }

    //Metodo que abre la pantalla de Estado
    public void openEstado(View v){
        Intent intent = new Intent(Principal.this,Estado.class);//El intent es la sesion
        startActivity(intent);
    }

    //Metodo que abre la pantalla de Dar de baja
    public void openDarDebaja(View v){
        Intent intent = new Intent(Principal.this,DarDeBaja.class);//El intent es la sesion
        startActivity(intent);
    }

    //Metodo que restaura la base de datos
    public void restaurar (View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        db.delete("materias",null,null);
        db.delete("rubros",null,null);
        db.close();
        Toast.makeText(this,"Se restauraron todos los datos", Toast.LENGTH_SHORT).show();

    }

}
