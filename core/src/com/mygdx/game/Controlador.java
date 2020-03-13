package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Controlador {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADOS
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTANTES

    private final int FILAS =9;
    private final int COLUMNAS =9;

    private final boolean TIPOFLOTA=true; // true es una flota aliada, false es una flota enemiga

    static private final int PANTALLA_INICIO = 0;
    static private final int PANTALLA_JUEGO = 1;
    //RESTO DE ESTADOS
    // posición en pantalla de los tableros
    private  int posxTableroA;
    private  int posyTableroA;

    private  int posXTableroE;
    private  int posYTableroE;
    //tableros de juego
    private Tablero tableroAmigo;
    private Tablero tableroEnemigo;
    //flotas
    private Flota flotaAmiga;
    private Flota flotaEnemiga;

    //Tendremos un SpriteBatch para dibujar en la pantalla
    private SpriteBatch batch;

    //Variable para saber el estado en el que estamos:
    // 0 . Pantalla inicio
    // 1. Jugando
    private int estadoJuego;


    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////

      // constructor

    public Controlador() {

        inicializarObjetos();
        estadoJuego = PANTALLA_INICIO;

    }
    //////////////////////////////////////////////////////////////////////
    // Resto de comportamientos
    /////////////////////////////////////////////////////////////////////


    // el controlador sabe que hacer para pintarse
    public void render(){
        this.controlEstado();
        if (estadoJuego==1) {
            // estamos jugando
        } else {
            dibujarPantallaInicial();
        }
    }

    //El controlador tendrá que saber como finalizar y cerrar recursos
    public void dispose() {
        tableroAmigo.dispose();
        tableroEnemigo.dispose();
        flotaAmiga.dispose();
        flotaEnemiga.dispose();
        batch.dispose();
    }

    //Método de control del estado del juego
    public void controlEstado(){
        if (estadoJuego == 0) {
            controlEstadoPantallaInicio();
        } else {
            controlEstadoJugando();
        }
    }

    //Método de control del estado cuando jugamos.
    private void controlEstadoJugando() {
        //Actualizo el teclado
        boolean recienTocado;

        recienTocado = Gdx.input.justTouched();
        if (recienTocado) {
            //llamamos a comprobar si ha pulsado en un tablero
            tableroAmigo.comprobar(Gdx.input.getX(), Gdx.input.getY());
        }
    }

    //Método de control del inicio de la pantalla de inicio juego
    private void controlEstadoPantallaInicio() {
        //Actualizo el teclado
        boolean recienTocado;

        recienTocado = Gdx.input.justTouched();
        if (recienTocado) {
           // comienza el juego, pintamos los tableros y generamos la flota
            estadoJuego=1;
            flotaAmiga= new Flota(TIPOFLOTA,FILAS,COLUMNAS);
            flotaEnemiga= new Flota(!TIPOFLOTA,FILAS,COLUMNAS);
        }
    }

    // Método donde inicializamos los objetos
    public void inicializarObjetos(){
        posxTableroA=Gdx.graphics.getWidth()/(COLUMNAS*2)+3;
        posyTableroA=Gdx.graphics.getHeight()/(FILAS);

        posXTableroE=posxTableroA*COLUMNAS;
        posYTableroE=posyTableroA;

        tableroAmigo = new Tablero(FILAS,COLUMNAS );
        tableroEnemigo = new Tablero(FILAS,COLUMNAS);
        flotaAmiga = new Flota(TIPOFLOTA,FILAS,COLUMNAS);
        flotaEnemiga = new Flota(!TIPOFLOTA,FILAS,COLUMNAS);
        batch=new SpriteBatch();
    }

    // pintamos la pantalla inicial
    private void dibujarPantallaInicial() {
        tableroEnemigo.pintarse(batch);
        tableroAmigo.pintarse(batch);
        //flotaEnemiga.generarFlota(batch);
        //flotaAmiga.generarFlota(batch);
    }

    public short getFILAS() {
        return FILAS;
    }

    public short getCOLUMNAS() {
        return COLUMNAS;
    }
}
