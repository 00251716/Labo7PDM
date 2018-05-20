package com.example.kevin.labo7pdm.DBUtils;

//Cada instancia de esta clase representa una tupla de la tabla
public class Registro {

    private String mCarnet;
    private String mNota;
    private String mMateria;
    private String mDocente;

    public Registro(){

    }

    public Registro(String carnet, String nota, String materia, String docente) {
        mCarnet = carnet;
        mNota = nota;
        mMateria = materia;
        mDocente = docente;
    }

    public String getCarnet() {
        return mCarnet;
    }

    public String getNota() {
        return mNota;
    }

    public String getMateria() {
        return mMateria;
    }

    public String getDocente() {
        return mDocente;
    }

    public void setCarnet(String carnet) {
        mCarnet = carnet;
    }

    public void setNota(String nota) {
        mNota = nota;
    }

    public void setMateria(String materia) {
        mMateria = materia;
    }

    public void setDocente(String docente) {
        mDocente = docente;
    }
}
