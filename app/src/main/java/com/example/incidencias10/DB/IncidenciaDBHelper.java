package com.example.incidencias10.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.incidencias10.DB.IncidenciaContract.*;
import com.example.incidencias10.ui.list.DatosVO;

import java.util.ArrayList;

public class IncidenciaDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + IncidenciaEntry.TABLE_NAME + "(" + IncidenciaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IncidenciaEntry.ColumnTitle + " TEXT ," + IncidenciaEntry.ColumnInci + " TEXT ," + IncidenciaEntry.ColumnDesc + " TEXT ," + IncidenciaEntry.ColumnEstate + " TEXT ," + IncidenciaEntry.ColumnDate + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public IncidenciaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertIncidencia(SQLiteDatabase db, String getTitle, String getInci, String getDesc,String getEstate,String getDate){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the incidence getting all values
            values.put(IncidenciaEntry.ColumnTitle, getTitle);
            values.put(IncidenciaEntry.ColumnInci, getInci);
            values.put(IncidenciaEntry.ColumnDesc, getDesc);
            values.put(IncidenciaEntry.ColumnEstate, getEstate);
            //values.put(IncidenciaEntry.ColumnDate, "now");

            db.insert(IncidenciaEntry.TABLE_NAME, null, values);
        }else{
            Log.d("sql","Database is closed");
        }
     }

   public ArrayList<DatosVO> returnName() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(IncidenciaEntry.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

       ArrayList<DatosVO> listaDatos = new ArrayList<>();

       for (int i = 0; i < cursor.getCount(); i++) {
           listaDatos.add(new DatosVO(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
           cursor.moveToNext();
       }
       return listaDatos;
    }



    public void deleteRow(int value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + IncidenciaEntry.TABLE_NAME+ " WHERE id ='"+value+"'");
        db.close();
    }


}

