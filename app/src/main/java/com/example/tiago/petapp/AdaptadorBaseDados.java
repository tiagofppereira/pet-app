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
    public int deleteNome(String oNome) {
        String whereClause = "nome = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = oNome;
        return database.delete("spinner", whereClause, whereArgs);
    }
    public int deleteTodosNomes() {
        return database.delete("spinner", null, null);
    }
    public int updateNome(Integer oId, String oNumero, String oNome, String aMorada) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("nome", oNome);
        values.put("numero", oNumero);
        values.put("morada", aMorada);
        return database.update("spinner", values, whereClause, whereArgs);
    }
}