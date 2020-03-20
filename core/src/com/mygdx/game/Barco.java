package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.omg.CORBA.WStringSeqHelper;

import java.util.ArrayList;

public class Barco {


    ////////////////////////
    ///ESTADO
    ///////////////////////

    private boolean direccion;
    private int columna;
    private int size;
    private int fila;
    private String imagenBarco;
    protected ArrayList<Celda> listacelda;
    Celda barco;
    //////////////////////
    ///COMPORTAMIENTOS
    //////////////////////
    // constructor
    public Barco(boolean direccion, int nuevacolumna, int nuevafila, int nuevosize,int posxTablero, int posyTablero,int lado){

        direccion = false;
        columna = nuevacolumna;
        size = nuevosize;
        fila = nuevafila;
        listacelda = new ArrayList();

        for (size=0;size<4;size++) {
            barco = new Celda((size+1)*fila,columna,imagenBarco);
            listacelda.add(barco);
        }

    }//Fin de constructor

    public void pintarse(int posX, int posY,boolean miDireccion,int miSize, int miPosxTablero, int miPosyTablero,int miLado) {
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

    public boolean comprobar(Barco miBarco){


    }

    public boolean estaHundido(){

    }
    /////////////////////////////////////////////////////////
    //  Getters
    ///////////////////////////////////////////////////////
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
}
