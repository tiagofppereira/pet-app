package com.example.tiago.petapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class AjudaUsoBaseDados extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pets";
    private static final int VERSION = 1;
    public AjudaUsoBaseDados(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String e = "CREATE TABLE pet(_id integer primary key autoincrement, nome varchar(40), idade varchar(40), " +
                "especie varchar(40), raca varchar(40))";
        String c = "CREATE TABLE consultas (_id integer primary key autoincrement, razao varchar(40), " +
                "data varchar(40), " +
                "hora varchar(40), " +
                "id_animal integer, FOREIGN KEY (id_animal) REFERENCES pet(_id))";

        String[] statements = new String[]{e, c};

        for(String sql : statements){
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Spinner");
        onCreate(db);
    }
}