package com.example.admin.proyectofinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//Clase donde se dan de alta los rubros de cada materia
public class Rubros extends AppCompatActivity {
    private EditText nombreR,valor;
    private TextView tvNomRubro;
    private String nombreRB;
    private int valorB;
    private int calificacion ;
    private int idMateria;
    private int cantRubros;
    private  int vecesRep;
    private int suma;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubros);
        nombreR =  (EditText)findViewById(R.id.nomRubro);
        valor =  (EditText)findViewById(R.id.pond);
        tvNomRubro = (TextView) findViewById(R.id.tvNomRubro);
        Bundle bundle = this.getIntent().getExtras();
        cantRubros = bundle.getInt("numRubros");
        idMateria = bundle.getInt("idMa");
        vecesRep=0;
    }


    //Metodo para agregar el rubro a la materia y se repite el numero de veces que se selecciono previamente
    public void aceptarAgregar(View v) {
     if(vecesRep<cantRubros){
         nombreRB = nombreR.getText().toString();
         valorB = Integer.parseInt(valor.getText().toString());
         calificacion=-1;
         AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Administrador",null,1);
         SQLiteDatabase db = admin.getWritableDatabase();
         ContentValues registro = new ContentValues();
         registro.put("nombreR",nombreRB);
         registro.put("valor",valorB);
         registro.put("calificacion",calificacion);
         registro.put("idMateria",idMateria);
         db.insert("rubros",null,registro);
         nombreR.setText("");
         valor.setText("");
         vecesRep++;
         tvNomRubro.setText("Nombre del Rubro "+(vecesRep+1));
         if(vecesRep==cantRubros){
             suma=0;
             Cursor fila2 = db.rawQuery("select valor from rubros where idMateria= "+idMateria ,null);
             while(fila2.moveToNext())
                suma = suma + fila2.getInt(0);
             Toast.makeText(this,"La suma es " +suma, Toast.LENGTH_SHORT).show();
             db.close();
             Intent intent = new Intent(Rubros.this,Principal.class);
             startActivity(intent);
         }
     }
    }

}
















