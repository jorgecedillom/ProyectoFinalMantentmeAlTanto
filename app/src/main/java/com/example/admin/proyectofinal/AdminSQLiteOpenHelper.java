package com.example.admin.proyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Clase para implementar base de datos SQL


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table materias (idMateria integer primary key autoincrement, nombreM text, calificacionM integer, cantRubros integer)");
        db.execSQL("create table rubros (idRubro integer primary key autoincrement , nombreR text, valor integer, calificacion integer, idMateria integer references materias(idMateria)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists materias");
        db.execSQL("drop table if exists rubros");
        onCreate(db);
    }

}
