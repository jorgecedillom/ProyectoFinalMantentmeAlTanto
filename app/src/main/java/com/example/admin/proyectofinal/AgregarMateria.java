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

//Clase para dar de alta una materia en la base de datos
public class AgregarMateria extends AppCompatActivity {
private Spinner spNumRubros;
private EditText nombre;

private int idMateria;
private String nombreM;
    private int calificacion;
    private int cantRubros;
    String[] numeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_materia);
        spNumRubros = (Spinner)findViewById(R.id.spNumRubros);
        nombre =(EditText) findViewById(R.id.et1);
        numeros = getResources().getStringArray(R.array.numeros);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,numeros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumRubros.setAdapter(adapter);
    }

    //Metodo para regresar a la pagina principal
    public void regresar(View v){
        Intent intent = new Intent(AgregarMateria.this,Principal.class);//El intent es la sesion
        startActivity(intent);

    }

    //Metodo para dar de alta la materia y que abrir la pagina de Rubros
    public void aceptarAbrirRubros(View v) {
        calificacion  = -1;
        nombreM = nombre.getText().toString();
        cantRubros = Integer.parseInt(spNumRubros.getSelectedItem().toString());
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        Cursor fila = db.rawQuery("select MAX(idMateria)from materias",null);
        fila.moveToFirst();
        idMateria=fila.getInt(0);
        registro.put("nombreM",nombreM);
        registro.put("calificacionM",calificacion);
        registro.put("cantRubros",cantRubros);
        db.insert("materias",null,registro);
        db.close();
        Toast.makeText(this,"Se agrego la materia: " +nombreM, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AgregarMateria.this,Rubros.class);
        Bundle b = new Bundle();
        b.putInt("idMa",idMateria+1);
        b.putInt("numRubros",cantRubros);
        intent.putExtras(b);
        startActivity(intent);
    }
}
