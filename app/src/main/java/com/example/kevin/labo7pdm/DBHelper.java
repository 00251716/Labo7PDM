package com.example.kevin.labo7pdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    //--------------- Constantes para la estructura de la tabla ----------------
    public static final String DB_NAME="bd_notas";
    public static final String TABLA_REGISTROS = "registro";
    public static final String CAMPO_CARNET="carnet";
    public static final double CAMPO_NOTA=0.0;
    public static final String CAMPO_MATERIA="materia";
    public static final String CAMPO_CATEDRATICO="docente";
    public static final String CREAR_TABLA_REGISTRO="CREATE TABLE "+TABLA_REGISTROS + "("+CAMPO_CARNET+" TEXT,"+ CAMPO_NOTA +" TEXT)";
    //--------------- Constantes para la estructura de la tabla ----------------

    public static DBHelper getInstance(Context ctx){
        if(myDB == null){
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase(); //Crea o abre una base de datos que se usa para leer y escribir
    }

    //Este método genera las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_REGISTRO);
    }

    //Este método verifica si existen versiones anteriores
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_CARNET);
        onCreate(db);
    }


}
