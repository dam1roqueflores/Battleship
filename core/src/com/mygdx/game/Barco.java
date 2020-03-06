package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Barco {

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADOS
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTANTES

    //Resto de estados
    private int fila;
    private int columna;
    private int tamano; // número de celdas del barco de 3 celda a 6 celdas.
    private boolean direccion;
    String rutaImagen;

    ArrayList <Celda> listaCeldasBarco;



    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
    // constructor
    ////////////////////////////////////////////////////////////////////////////////////
    public Barco(int miFila, int miColumna, int miTamaño, boolean direccion, String miStrimg) {
        int i;
        Celda miCelda;

        fila=miFila;
        columna=miColumna;
        tamano=miTamaño;
        rutaImagen=miStrimg;

        // creamos la lista de celdas que será nuestro barco
        for (i=0;i<tamano;i++){
            miCelda = new Celda(fila,columna,rutaImagen);
            listaCeldasBarco.add(miCelda);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
    // resto de comportamientos
    public void pintarse(SpriteBatch miSB, String miString){
        for (Celda miCelda:listaCeldasBarco){
            miCelda.pintarse(miCelda.getPosX(), miCelda.getPosY(),miSB,miString);
        }
    }
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getTamano() {
        return tamano;
    }

    public boolean Direccion() {
        return direccion;
    }
}
