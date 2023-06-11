package com.ifsc.myapplication.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NutriTableDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taco_4___edicao";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "taco_4___edicao";
    private static final String ALIMENTO_COLUMN = "Alimento";
    private Context mContext;

    public NutriTableDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            InputStream inputStream = mContext.getAssets().open("taco_4___edicao.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String[] comandos = stringBuilder.toString().split(";");
            for(String comando : comandos){
                db.execSQL(comando);
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<String> getAllFoodNames() {
        List<String> foodList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Alimento FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String food = cursor.getString(cursor.getColumnIndex("Alimento"));
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return foodList;
    }

    @SuppressLint("Range")
    public String getFoodAttributes(String foodName) {
        StringBuilder attributesBuilder = new StringBuilder();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Alimento = '" + foodName + "'", null);

        if (cursor.moveToFirst()) {
            do {
                attributesBuilder.append("Umidade: ").append(cursor.getString(cursor.getColumnIndex("Umidade"))).append("\n");
                attributesBuilder.append("Energia (kcal): ").append(cursor.getString(cursor.getColumnIndex("Energiakcal"))).append("\n");
                attributesBuilder.append("Energia (kJ): ").append(cursor.getString(cursor.getColumnIndex("kJ"))).append("\n");
                // poderia add mais atributos
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return attributesBuilder.toString();
    }

    public List<String> getAllFood() {
        List<String> foodList = new ArrayList<>();

        // Abra o banco de dados
        SQLiteDatabase db = getReadableDatabase();

        // Execute a consulta para recuperar todos os alimentos
        Cursor cursor = db.rawQuery("SELECT Alimento FROM taco_4___edicao", null);

        // Percorra o cursor e adicione os alimentos à lista
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String food = cursor.getString(cursor.getColumnIndex("Alimento"));
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // Feche o cursor e o banco de dados
        cursor.close();
        db.close();

        return foodList;
    }

}