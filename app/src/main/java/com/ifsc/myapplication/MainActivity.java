package com.ifsc.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteDatabase = openOrCreateDatabase("testBd",MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS nota(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo VARCHAR," +
                "txt VARCHAR)");
        insertNota();
    }

    public void insertNota(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", "titulo1");
        contentValues.put("txt", "txt1");

        sqLiteDatabase.insert("nota", null, contentValues);
    }

    public void atualizaNotas(){

    }
   @SuppressLint("Range")
    public ArrayList<String> listNotas(){
        ArrayList<String> resultado = new ArrayList<>();
       Cursor c = sqLiteDatabase.rawQuery("Select * from nota",null);
       c.moveToFirst();
       while (!c.isAfterLast()){
           resultado.add(c.getString(c.getColumnIndex("titulo")));
           c.moveToNext();
       }
       return resultado;
    }

    @Override
    protected void onStart(){
        super.onStart();
        ListView listView = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listNotas()
        );
        listView.setAdapter(adapter);
    }
}