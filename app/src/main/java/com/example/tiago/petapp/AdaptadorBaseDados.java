package com.example.tiago.petapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
public class AdaptadorBaseDados {
    private AjudaUsoBaseDados dbHelper;
    private SQLiteDatabase database;
    public AdaptadorBaseDados(Context context) {
        dbHelper = new AjudaUsoBaseDados(context.getApplicationContext());
    }
    public AdaptadorBaseDados open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    private Cursor obterTodosAnimais() {
        String[] colunas = new String[5];
        colunas[0] = "_id";
        colunas[1] = "nome";
        colunas[2] = "idade";
        colunas[3] = "especie";
        colunas[4] = "raca";
        return database.query("pet", colunas, null, null, null, null, "_id");
    }

    private Cursor obterTodasConsultas() {
        String[] colunas = new String[5];
        colunas[0] = "_id";
        colunas[1] = "razao";
        colunas[2] = "data";
        colunas[3] = "hora";
        colunas[4] = "id_animal";
        return database.query("consultas", colunas, null, null, null, null, "data DESC");
    }
    /*public List<String> obterTodosNumeros() {
        ArrayList<String> numeros = new ArrayList<String>();
        Cursor cursor = obterTodosRegistos();
        if (cursor.moveToFirst()) {
            do {
                numeros.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return numeros;
    }*/

    public String[] obterDetalhesRegisto(String id) {
        Cursor db = obterTodosAnimais();
        String[] registo = new String[5];
        if (db.moveToFirst()) {
            do {
                if(db.getString(0).equals(id)) {
                    registo[0] = db.getString(0);
                    registo[1] = db.getString(1);
                    registo[2] = db.getString(2);
                    registo[3] = db.getString(3);
                    registo[4] = db.getString(4);
                }
            } while (db.moveToNext());
        }
        db.close();
        return registo;
    }

    public String[] obterDetalhesConsulta(String id) {
        Cursor db = obterTodasConsultas();
        String[] registo = new String[5];
        if (db.moveToFirst()) {
            do {
                if(db.getString(0).equals(id)) {
                    registo[0] = db.getString(0);
                    registo[1] = db.getString(1);
                    registo[2] = db.getString(2);
                    registo[3] = db.getString(3);
                    registo[4] = db.getString(4);
                }
            } while (db.moveToNext());
        }
        db.close();
        return registo;
    }

    public String[] obterDetalhesConsultaData(String data) {
        Cursor db = obterTodasConsultas();
        String[] registo = new String[5];
        if (db.moveToFirst()) {
            do {
                if(db.getString(2).equals(data)) {
                    registo[0] = db.getString(0);
                    registo[1] = db.getString(1);
                    registo[2] = db.getString(2);
                    registo[3] = db.getString(3);
                    registo[4] = db.getString(4);
                }
            } while (db.moveToNext());
        }
        db.close();
        return registo;
    }

    public List<String> obterConsultasAnimal(String id_animal) {
        ArrayList<String> consultas = new ArrayList<String>();
        Cursor cursor = obterTodasConsultas();

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(4).equals(id_animal)) {
                    consultas.add(cursor.getString(2));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return consultas;
    }



    public List<String> obterAnimais() {
        ArrayList<String> pets = new ArrayList<String>();
        Cursor cursor = obterTodosAnimais();

        if (cursor.moveToFirst()) {
            do {
                pets.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pets;
    }

    public List<String> obterConsultas() {
        ArrayList<String> consultas = new ArrayList<String>();
        Cursor cursor = obterTodasConsultas();

        if (cursor.moveToFirst()) {
            do {
                consultas.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return consultas;
    }


    public List<String> obterTodosIds() {
        ArrayList<String> ids = new ArrayList<String>();
        Cursor cursor = obterTodosAnimais();
        if (cursor.moveToFirst()) {
            do {
                ids.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ids;
    }

    public int obterTodosCampos(List<Integer> osIds, List<String> osNumeros, List<String> osNomes) {
        String[] colunas = new String[23];
        colunas[0] = "_id";
        colunas[1] = "numero";
        colunas[2] = "nome";
        Cursor c = database.query("spinner", colunas, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                osIds.add(c.getInt(0));
                osNumeros.add(c.getString(1));
                osNomes.add(c.getString(2));
            } while (c.moveToNext());
        }
        c.close();
        return osIds.size();
    }

    public boolean existe(String umNome) {
        Cursor cursor = database.rawQuery(
                "select nome from spinner where nome=?", new String[] { umNome });
        boolean b = cursor.getCount() >= 1;
        cursor.close();
        return b;
    }
    public long inserirPet(String nome, String idade, String especie, String raca) {
        ContentValues values = new ContentValues() ;
        values.put("nome", nome);
        values.put("idade", idade);
        values.put("especie", especie);
        values.put("raca", raca);
        return database.insert("pet", null, values);
    }

    public long inserirConsulta(String razao, String data, String hora, Integer id_animal) {
        ContentValues values = new ContentValues() ;
        values.put("razao", razao);
        values.put("data", data);
        values.put("hora", hora);
        values.put("id_animal", id_animal);
        return database.insert("consultas", null, values);
    }

        public int deletePet(String id) {
        String whereClause = "_id = ?";
        String whereAnimal = "id_animal = ?"; //Para consultas
        String[] whereArgs = new String[1];
        whereArgs[0] = id;
        database.delete("consultas", whereAnimal, whereArgs);
        return database.delete("pet", whereClause, whereArgs);
    }

    public int deleteConsulta(String id) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = id;
        return database.delete("consultas", whereClause, whereArgs);
    }

    public int deleteTodosNomes() {
        return database.delete("spinner", null, null);
    }
    public int updatePet(Integer oId, String oNome, String especie, String raca, String idade) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("nome", oNome);
        values.put("especie", especie);
        values.put("raca", raca);
        values.put("idade", idade);
        return database.update("pet", values, whereClause, whereArgs);
    }

    public int updateConsulta(Integer oId, String razao, String data, String hora) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("razao", razao);
        values.put("data", data);
        values.put("hora", hora);
        return database.update("pet", values, whereClause, whereArgs);
    }
}