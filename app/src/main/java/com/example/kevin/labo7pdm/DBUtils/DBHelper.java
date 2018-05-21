package com.example.kevin.labo7pdm.DBUtils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.kevin.labo7pdm.MainActivity;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static Context context;
    public static final DBHelper ourInstance = new DBHelper(MainActivity.getContext());
    private static SQLiteDatabase db;
    private static ArrayList<Registro> mCurrentList; //Lista actual de registros de tuplas en la tabla

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

    public ArrayList<Registro> getCurrentList() {
        return  mCurrentList;
    }

    public static void cargarRegistros(){
        mCurrentList = new ArrayList<Registro>();

        try {

            Cursor cursor = db.rawQuery("SELECT * FROM registro", null);

            try {

                if (cursor.moveToFirst()) {

                    do {
                        String carnet = cursor.getString(0);
                        String nota = cursor.getString(1);
                        String materia = cursor.getString(2);
                        String docente = cursor.getString(3);
                        Registro dummyRegistro = new Registro(carnet, nota, materia, docente);
                        mCurrentList.add(dummyRegistro);
                    } while (cursor.moveToNext());

                }
            } finally {
                try {
                    cursor.close();
                } catch (Exception ignore) {

                }
            }

        } catch (Exception ignore) {

        }

    }

    private DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase(); //Crea o abre una base de datos que se usa para leer y escribir

        //----------------------- Dummy data ----------------------------------------------------------
        add(new Registro("00251717", "8.6", "Análisis numérico", "Sosa"));
        add(new Registro("00023416", "6", "PDM", "Néstor Aldana"));
        add(new Registro("00451717", "9", "Probabilidad", "Navarro"));
        add(new Registro("00123416", "7", "Redes", "Varela"));
        add(new  Registro("00012318", "10", "Análisis de sistemas", "Silviax"));
        //----------------------- Dummy data ----------------------------------------------------------
        cargarRegistros();
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

    //Este método sirve para añadir registros a la tabla
    public boolean add(Registro r){
        ContentValues values = new ContentValues();

        values.put(CAMPO_CARNET, r.getCarnet());
        values.put(CAMPO_NOTA, r.getNota());
        values.put(CAMPO_MATERIA, r.getMateria());
        values.put(CAMPO_CATEDRATICO, r.getDocente());

        db.insert(TABLA_REGISTROS, null, values);

        return true;
    }

    //Método para buscar un registro
    public Registro buscarRegistro(String carnet){
        Registro r;
        String [] parametros = {carnet };
        String [] campos = {CAMPO_CARNET, CAMPO_NOTA, CAMPO_MATERIA, CAMPO_CATEDRATICO};

        try{
            Cursor cursor = db.query(TABLA_REGISTROS, campos, CAMPO_CARNET+"=?", parametros, null, null, null );
            cursor.moveToFirst();
            r = new Registro(carnet, cursor.getString(1), cursor.getString(2), cursor.getString(3) );
        } catch (Exception e) {
            r = null;
        }

        return r;
    }

    //Método para editar un registro
    public boolean editarRegistro(String carnet, String nota){
        String [] parametros = {carnet};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOTA, nota);
        int rowsAffected = db.update(TABLA_REGISTROS, values, CAMPO_CARNET+"=?", parametros);

        if(rowsAffected == 0){
            Toast.makeText(context, "No se encontraron registros. Pruebe con otro carnet.", Toast.LENGTH_SHORT).show();
        } else if(rowsAffected > 0){
            Toast.makeText(context, "Se actualizaron con éxito "+rowsAffected+" registros.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

}
