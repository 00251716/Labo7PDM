package com.example.kevin.labo7pdm.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.kevin.labo7pdm.MainActivity;

public class DBHelper extends SQLiteOpenHelper {

    private static Context context;
    public static final DBHelper ourInstance = new DBHelper(MainActivity.getContext());
    SQLiteDatabase db;

    //--------------- Constantes para la estructura de la tabla ----------------
    public static final String DB_NAME="bd_notas";
    public static final String TABLA_REGISTROS = "registro";
    public static final String CAMPO_CARNET="carnet";
    public static final String CAMPO_NOTA= "nota";
    public static final String CAMPO_MATERIA = "materia";
    public static final String CAMPO_CATEDRATICO="docente";
    public static final String CREAR_TABLA_REGISTRO="CREATE TABLE " +TABLA_REGISTROS
            + "("+ CAMPO_CARNET +" TEXT,"+ CAMPO_NOTA +" TEXT, "+ CAMPO_MATERIA +" TEXT, "+ CAMPO_CATEDRATICO +" TEXT )";
    //--------------- Constantes para la estructura de la tabla ----------------

    public static DBHelper getInstance(){
        return ourInstance;
    }

    private DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase(); //Crea o abre una base de datos que se usa para leer y escribir
    }

    //Este método genera las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_REGISTRO);
    }

    public void test(){
        Toast.makeText(context, "Lo logramos", Toast.LENGTH_SHORT).show();
    }

    //Este método verifica si existen versiones anteriores
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_CARNET);
        onCreate(db);
    }

    //Este método sirve para añadir registros a la tabla
    public boolean add(Registro r){
        ContentValues values = new ContentValues();

        values.put(CAMPO_CARNET, r.getCarnet());
        values.put(CAMPO_NOTA, r.getNota());
        values.put(CAMPO_MATERIA, r.getMateria());
        values.put(CAMPO_CATEDRATICO, r.getDocente());

        db.insert(TABLA_REGISTROS, null, values);

        Toast.makeText(context, "Insertado con éxito", Toast.LENGTH_SHORT).show();

        return true;
    }

}
