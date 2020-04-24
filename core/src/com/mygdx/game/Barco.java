package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Barco {

    ////////////////////////
    ///ESTADO
    ///////////////////////

    private boolean direccion;
    private int columna;
    private int size;
    private int fila;
    private ArrayList<Celda> listaCeldas;
    private static final String FICHERO_BARCO = "barco.png";
    private boolean EstaHundido;

    //////////////////////
    ///COMPORTAMIENTO
    //////////////////////

    public Barco(boolean miDireccion, int miColumna, int miSize, int miFila, int posXTablero, int posYTablero, int lado){

        int i;
        Celda miCelda;

        direccion = miDireccion;
        columna = miColumna;
        size = miSize;
        fila = miFila;
        listaCeldas = new ArrayList();



        for (i=0;i<size;i++) {
            if (direccion = true) {
                miCelda = new Celda(posXTablero + (i + columna - 1) * lado, posYTablero + (fila - 1) * lado, FICHERO_BARCO);
                listaCeldas.add(miCelda);
            } else {
                miCelda = new Celda(posXTablero + (columna - 1) * lado, posYTablero + (i + fila - 1) * lado, FICHERO_BARCO);
                listaCeldas.add(miCelda);
            }
        }
    }//Fin de constructor

    public void pintarse(int posX,int posY,SpriteBatch miSB) {
        miSB.begin();
        for (Celda miCelda : listaCeldas) {
            miCelda.pintarse(posX,posY,miSB);
        }
        miSB.end();
    }

    public boolean comprobarse() {

        return false;
    }




//Getters

    public boolean isDireccion() {

        return direccion;
    }

    public int getSize() {

        return size;
    }

    public int getColumna() {

        return columna;
    }

    public int getFila() {

        return fila;
    }

    public boolean isEstaHundido() {

        return EstaHundido;
    }

    public void dispose() {

        int i=0;
        // tengo que liberar recursos de los barcos con un bucle
        for (i = 0; i < listaCeldas.size(); i++)
            listaCeldas.get(i).dispose();
    }


}
