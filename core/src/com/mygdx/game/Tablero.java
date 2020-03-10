package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Tablero {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //      ESTADOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
    //CONSTANTES
    private final short LADO=32;
    private final String IMAGENAGUA="agua.png";
    private final String IMAGENAGUA1="agua1.png";
    private final String IMAGENAGUA2="agua2.png";

    private final short TASACAMBIO=3;


    //RESTO DE ESTADOS
    private short filas;
    private  short columnas;
    private int posX;
    private int posY;

    private ArrayList<Celda> listaCeldas;
    private ArrayList<String> listaStrImg; //lista de string para animaciones de la celda

    short contador=0;
    short contadorCambio=0;
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //      CONSTRUCTOR
    //
    /////////////////////////////////////////////////////////////////////////////////////
    public Tablero(short miFilas, short miColumnas) {
        int i=0;
        int j=0;
        short contadorImg=0;

        Celda miCelda;

        filas= miFilas;
        columnas=miColumnas;
        listaCeldas = new ArrayList();
        listaStrImg=new ArrayList<>();

        // carga la lista de imágenes para animar las celdas
        listaStrImg.add(IMAGENAGUA);
        listaStrImg.add(IMAGENAGUA1);
        listaStrImg.add(IMAGENAGUA2);


        for (i=0;i<filas;i++){
            for (j=0;j<columnas;j++){
                miCelda= new Celda(this,miFilas,miColumnas,listaStrImg.get(contadorImg));
                listaCeldas.add(miCelda);
                contadorImg++;
                if (contadorImg>3) {contadorImg=0;}
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //      COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
    // Pintamos el tablero
    public void pintarse(SpriteBatch miSB){

        for (Celda miCelda:listaCeldas){
            miCelda.pintarse(miCelda.getPosX(),miCelda.getPosY(),miSB,listaStrImg.get(contador));
        }
        if (contadorCambio>TASACAMBIO) {
            contadorCambio=0;
            contador++;
            if (contador == 3) {
                contador = 0;
            }
        } else {
            contadorCambio++;
        }
    }
    // comprobamos si se ha pulsado sobre una celda del tablero
    public boolean comprobar(int miX, int miY){
        boolean resultado=false;
        int contador=0;

        while (!resultado && contador < listaCeldas.size()){
            if (listaCeldas.get(contador).comprobar(miX,miY)){
                //se ha pulsado en una celda del tablero.
               resultado = listaCeldas.get(contador).comprobar(miX,miY);
            }
        }
        return resultado;
    }

    // liberamos tablero de memoria
    public void dispose(){
        this.dispose();
    }

    /////////////////////////////////////////////////
    // Getters

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
