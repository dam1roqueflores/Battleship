package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Celda {
    /////ESTAD0/////////////////////////////////////////////////////////////////////////

    protected int posX;
    protected int posY;
    protected int lado;
    protected Texture miImagen;
    protected String nombreSpriteTocada;
    protected String nombreesprite;
    protected boolean tocada;


    /////CONSTRUCTORES/////////////////////////////////////////////////////////////////

    public Celda(int nposX,int nposY,String minombreesprite){
        posX=nposX;
        posY=nposY;
        nombreesprite = minombreesprite;

        //una vez que me dan el fichero lo cargo
        miImagen = new Texture(nombreesprite);
        //Ahora que tengo la imagen
        lado = miImagen.getWidth();
        tocada=false;

    }
    public void pintarse(int posX,int posY, SpriteBatch miSB){
        miSB.begin();
        miSB.draw(miImagen,posX,posY);
        miSB.end();
    }

    public void morirse() {
        tocada=true;

    }


    public  void dispose() {
        miImagen.dispose();
    }

}
