package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3) )");

            //Inserir dados
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Priscilla', 27)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Lydice', 21) ");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Norman Powell', 29) ");
            //atualizando dado de uma pessoa tabela
            //bancoDados.execSQL("UPDATE pessoas SET idade = 19 WHERE nome = 'Pavon'");

            //apagar tabela
            //bancoDados.execSQL("DROP TABLE pessoas");

            //recuperar pessoas
            String consulta =
                    "SELECT id, nome, idade" +
                    " FROM pessoas" +
                            " WHERE 1=1";
            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //inndices tabela
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            cursor.moveToFirst();
            while (cursor != null) {
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                String id = cursor.getString(indiceId);

                Log.i("RESULTADO - id ", id + "/ nome: " + nome + "/ idade " + idade );

                cursor.moveToNext();

            }
        } catch (Exception e) {
            e.printStackTrace();


        }
    }
}