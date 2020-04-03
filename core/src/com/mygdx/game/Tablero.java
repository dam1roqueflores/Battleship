package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Tablero {
    ////////////////////////////////////////////////////////////////////////////
    ////////////////Estado/////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    protected int posX; //pos x del tablero
    protected int posY; //pos y del tablero
    protected Texture imagen;
    protected int contpintar;
    protected Flota miflota; //flota aliada
    protected Celda micelda; //celda con agua


    ArrayList<ArrayList<Boolean>> mapabooleano;


    private static final short FILAS=10; //nº de filas
    private static final short COLUMNAS=10; //nº de columnas
    private static final short LADO=10;
    private static final short TASACAMBIO=20;
    private static final String FILEAGUA1="agua1.png"; //imagen para celda
    private static final String FILEAGUA2="agua2.png";
    private static final String FILEAGUAPULSADA1="aguapulsada1.png";
    private static final String FILEAGUAPULSADA2="aguapulsada2.png";



    //comportamiento
    //contructor
    public Tablero(int posXtablero,int posYtablero,boolean Flotatype){
        posX=posXtablero;
        posY=posYtablero;


        contpintar=0;

        //creamos array list con toda array list

        for (int k=1;k<FILAS;k++){
            mapabooleano.add(new ArrayList());
        }

        //llenamos la array list multidimesional

        for (int cont1=0;cont1<FILAS;cont1++){

            for (int cont2=0;cont2<COLUMNAS;cont2++){
                mapabooleano.get(cont1).add(true);
            }
        }

        //instanciamos la flota
        miflota= new Flota(Flotatype,FILAS,COLUMNAS,posXtablero,posYtablero,LADO);

        //inicializamos imagen

        imagen= new Texture(FILEAGUA1);

        //iniciamos la celda

        micelda= new Celda(posX,posY,FILEAGUA1);



    }


    //resto de comportamientos
    public boolean comprobarDisparo(int posxdisp,int posydisp){
        boolean miboolean;
        int filas=traducirfila(posydisp);
        int columnas=traducircolumna(posxdisp);

        if (mapabooleano.get(filas).get(columnas)==true){// || flota.ispulsado()==true){ //or flota ispulsado==true
            miboolean=true;
        } else {
            miboolean=false;
        }
        return miboolean;
    }


    public void pintarse(SpriteBatch miSB,int posxcelda,int posycelda){


        String texturapintar=FILEAGUA1;


        //necesitro recorer la array list para pintarlo
        //y entonces pintar cada "casilla" segun sea true o false
        //bucle para pintar array multidimensional


        for (int cont1=0;cont1<FILAS;cont1++){

            for (int cont2=0;cont2<COLUMNAS;cont2++){
                if (mapabooleano.get(cont1).get(cont2)==true){
                    //SI ES TRUE

                    if (contpintar<TASACAMBIO){
                        texturapintar=FILEAGUA1;
                        contpintar++;
                    } else if (contpintar>(TASACAMBIO*2)){
                        texturapintar=FILEAGUA2;
                        contpintar++;
                    } else {
                        contpintar=0;
                    }

                } else {
                    //SI ES FALSE
                    if (contpintar<TASACAMBIO){
                        texturapintar=FILEAGUAPULSADA1;
                        contpintar++;
                    } else if (contpintar>(TASACAMBIO*2)){
                        texturapintar=FILEAGUAPULSADA2;
                        contpintar++;
                    } else {
                        contpintar=0;
                    }
                }
                // micelda.pintarse((posX+(cont1*LADO),posY+(cont2*LADO),texturapintar)); //tectura agua pulsada
            }

            //  micelda.pintarse(posX+(cont1*LADO),posy+(cont2*LADO)textura;
            //pinto celdas en base la array de true o false
            //celda.pintarse(posX+(cont1*LADO),posY+(cont2*LADO),imagen);
        }


        //luego se pint        a la flota
        // miflota.pintarse(miSB);

    }


    private int traducirfila(int pulposy){
        int fila;
        fila=posY+(pulposy/LADO); //yini(tablero)+(pulposy/lado)
        return fila;
    }

    private int traducircolumna(int pulposx){
        int columna;
        columna=posX+(pulposx/LADO); //yini(tablero)+(pulposy/lado)
        return columna;
    }
}
