package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Barco {


    ////////////////////////
    ///ESTADO
    ///////////////////////

    private boolean direccion;
    private int columna;
    private int size;
    private short fila;
    protected ArrayList<Celda> listacelda;
    Celda barco;
    //////////////////////
    ///COMPORTAMIENTO
    //////////////////////

    public Barco(int nuevacolumna, int nuevosize, short nuevafila){

        direccion = false;
        columna = nuevacolumna;
        size = nuevosize;
        fila = nuevafila;
        listacelda = new ArrayList();

        for (size=0;size<4;size++) {
            barco = new Celda((size+1)*fila,columna);
            listacelda.add(barco);
        }

    }//Fin de constructor

    public void pintarse(int posX, int posY) {
        //Comportamiento para pintar el barco.
        if (direccion = true) { //direccion=true significa horizontal, por lo que sería columna++
            for (Celda barco : listacelda){
                barco.pintarse(columna++);
            }
        }
        else { //Aquí sería vertical, o sea, fila++
            for (Celda barco : listacelda){
                barco.pintarse(fila++);
            }
        }

    }

    public void comprobarse(){


    }

}
