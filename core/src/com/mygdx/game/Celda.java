package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Celda {
    /////////////////////////////////////////////////////////////////////////////////////
//
//      ESTADOS
//
/////////////////////////////////////////////////////////////////////////////////////
    //CONSTANTES
    short LADO=32;
    // Resto de estados

    private int posX;
    private int posY;


    private Texture imgCelda;
    private ArrayList <Texture> listaImgCelda;


    /////////////////////////////////////////////////////////////////////////////////////
    //
    //      CONSTRUCTOR
    //
    /////////////////////////////////////////////////////////////////////////////////////
    public Celda(int miX, int miY, String strImg) {
        int i;


        posX=miX;

    }
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //      COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
    // pintamos la celda
    public void pintarse(int miX, int miY, SpriteBatch miSB,String miString){
       Texture imgtmp;


        imgCelda=new Texture(miString);
        miSB.begin();
        miSB.draw(imgCelda, posX, posY,LADO,LADO);
        miSB.end();

    }
    // comprobamos si se ha pulsado sobre una celda
    public boolean comprobar(int miX, int miY){
        boolean resultado=false;
        resultado=(posX+LADO<miX && posX>miX) && (posY+LADO<miY && posY > miY);
        return resultado;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
