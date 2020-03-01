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
    int posX;
    int posY;
    int numCeldas; // número de celdas del barco de 1 celda a 4 celdas.
    String rutaImagen;

    ArrayList <Celda> listaCeldasBarco;
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
    // constructor
    ////////////////////////////////////////////////////////////////////////////////////
    public Barco(int miX, int miY, short miCeldas, String miStrimg) {
        int i;
        Celda miCelda;

        posX=miX;
        posY=miY;
        numCeldas=miCeldas;
        rutaImagen=miStrimg;

        // creamos la lista de celdas que será nuestro barco
        for (i=0;i<numCeldas;i++){
            miCelda = new Celda(posX,posY,rutaImagen);
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

}
