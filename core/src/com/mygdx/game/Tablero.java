package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Tablero {
    ////////////////////////////////////////////////////////////////////////////
    ////////////////Estado/////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    protected final static int tamtablero=10;
    protected final static float margen=0.1f;


    protected int posX; //pos x del tablero
    protected int posY; //pos y del tablero
    protected int tam_cel_x;
    protected int tam_cel_y;
    protected  int alto;
    protected int ancho;
    protected Texture imagen;
    protected Texture imagenpulsada;
    //protected int contpintar;



    protected Flota miflota; //flota aliada
    //protected Celda micelda; //celda con agua

    protected boolean[][] mapa;
    //ArrayList<ArrayList<Boolean>> mapa;


    //private static final short FILAS=10; //nº de filas
    //private static final short COLUMNAS=10; //nº de columnas
    //private static final short LADO=10;
    private static final short TASACAMBIO=20;
    private static final String FILEAGUA1="agua1.png"; //imagen para celda
    //private static final String FILEAGUA2="agua2.png";
    private static final String FILEAGUAPULSADA1="aguapulsada1.png";
    //private static final String FILEAGUAPULSADA2="aguapulsada2.png";



    //comportamiento
    //contructor
    public Tablero(int posXtablero,int posYtablero,int altoDisponible,int anchoDisponible){//,boolean Flotatype){

        //contpintar=0;

        imagen=new Texture(FILEAGUA1);
        imagenpulsada= new Texture(FILEAGUAPULSADA1);

        posX=(int)(posXtablero*margen);
        posY=(int)(posYtablero*margen);

        alto=altoDisponible;
        ancho=anchoDisponible;

        tam_cel_x=(int)(anchoDisponible * (1.0f-margen*2.0f)/tamtablero);
        tam_cel_x=(int)(altoDisponible * (1.0f-margen*2.0f)/tamtablero);

        //for (int k=1;k<tamtablero;k++){
        //  mapa.add(new ArrayList());
        //}

        //llenamos la array list multidimesional

        //for (int cont1=0;cont1<tamtablero;cont1++){

        //  for (int cont2=0;cont2<tamtablero;cont2++){
        //    mapa.get(cont1).add(true);
        //}
        //}
        mapa= new  boolean[tamtablero][tamtablero];

        //instanciamos la flota
        //miflota= new Flota(tamtablero,tamtablero,posXtablero,posYtablero,tam_cel_x);

        //inicializamos imagen


        //iniciamos la celda

        //micelda= new Celda(posX,posY,FILEAGUA1);

        reset();

    }


    //resto de comportamientos
    //public boolean comprobarDisparo(int posxdisp,int posydisp){
    //  boolean miboolean;
    // int filas=traducirfila(posydisp);
    //int columnas=traducircolumna(posxdisp);



    //if (mapa.get(filas).get(columnas)==true){// || flota.ispulsado()==true){ //or flota ispulsado==true
    //      miboolean=true;
    //} else {
    //  miboolean=false;
    // }
    //return miboolean;
    //}


    public void pintarse(SpriteBatch miSB){

        //pìntamos el agua
        pintar_escenario(miSB);

        //pintamos la flota
        miflota.pintarse(miSB);


    }

    private void pintar_escenario(SpriteBatch miSB){
        for (int cont1=0;cont1<tamtablero;cont1++){
            for (int cont2=0;cont2<tamtablero;cont2++){
                miSB.begin();
                if (mapa[cont1][cont2]=true){
                    miSB.draw(imagen,posX+tam_cel_x*cont1,posY+tam_cel_y*cont2);
                } else {
                    miSB.draw(imagenpulsada,posX+tam_cel_x*cont1,posY+tam_cel_y*cont2);
                }
            }
        }
    }

    public void reset(){
        for (int o=0;o<tamtablero;o++){
            for (int f=0;f<tamtablero;f++){
                mapa[o][f]= true;
            }
        }
        miflota= new Flota(tamtablero,tamtablero,posX,posY,tam_cel_x);
    }




    public boolean comprobarDisparo(int pulposx,int pulposy){
        int casillax,casillay;
        boolean resultado=true;

        casillax= ((pulposx - posX)/tam_cel_x);
        casillay= ((pulposy - posY)/tam_cel_y);

        if (mapa[casillax][casillay]){
            resultado=false;
        } else {
            mapa[casillax][casillay]=false;
            //resultado=miflota.disparo[casillax][casillay];
            resultado=false;

        }

        return resultado;
    }

    public void dispose(){
        imagen.dispose();
        imagenpulsada.dispose();
        miflota.dispose();
    }

}
