package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Pensador {
    //ESTADO
    //Le doy un tablero
    protected Tablero tableroEnemigo;

    //PosX y PosY de la celda propuesta para pulsar.
    //Serán coordenadas absolutas, para simular un clik
    //y no tener dos versiones en la clase tablero
    protected int nextX;
    protected int nextY;
    //tengo que tener para ver como voy
    int comoVoy;

    //Aquí tengo que poner un montón de constantes para que almacene como va en el juego
    //tiene que pensar en el azar
    static private final int AL_AZAR = 0;
    //Le ha dado
    static private final int HE_ACERTADO = 1;
    static private final int VOY_MISMA_DIR = 2;
    static private final int VOY_DIR_CONTRARIA = 3;
    static private final int VOY_PROBAR_OTRA = 4;
    static private final int HE_TERMINADO = 5;

    //LE doy el lado para que haga los calculos una vez acierte
    private static final int LADO = 10;
    //CONSTRUCTOR
    public Pensador(Tablero miTablero){

        tableroEnemigo = miTablero;
        comoVoy = AL_AZAR;
    }

    //COMPORTAMIENTOS
    public void piensa(){
        switch (comoVoy){
            case AL_AZAR:
                piensa_inicial();
                break;
            case HE_ACERTADO:
                piensa_acertado();
                break;
            case VOY_MISMA_DIR:
                piensa_mismaDireccion();
                break;
            case VOY_DIR_CONTRARIA:
                piensa_voyDireccionContraria();
                break;
            case VOY_PROBAR_OTRA:
                piensa_probarOtraDireccion();
                break;
            case HE_TERMINADO:
                piensa_inicial();
                break;

        }
    }



    private void piensa_inicial() {
        //Aquí le digo que me calcule la posX y la posY al azar entre el tablero que le damos
        nextX = (int)Math.random() * (tableroEnemigo.getPosFinalX() - tableroEnemigo.getPosX())+tableroEnemigo.getPosX();
        nextY = (int)Math.random() * (tableroEnemigo.getPosFinalY() - tableroEnemigo.getPosY())+tableroEnemigo.getPosY();

    }
    private void piensa_acertado() {
        //Si he acertado tengo que decirle que vaya a izquierda derecha arriba o abajo respecto a la posición
        //nextX =+ LADO;
    }
    private void piensa_probarOtraDireccion() {
    }

    private void piensa_voyDireccionContraria() {
    }

    private void piensa_mismaDireccion() {
    }


    public void jugadaResultado(boolean resultado){

        switch (comoVoy){
            case AL_AZAR:
                if (resultado){
                    //Aquí voy a poner al comoVoy al azar pero deberia ir HE_ACERTADO
                    //Lo cambio para que no de error el juego, una vez ya tocado
                    //habria que cambiarlo
                    comoVoy = AL_AZAR;
                }
                break;
            case HE_ACERTADO:
                if (resultado){
                    comoVoy =VOY_MISMA_DIR ;
                }
                else {
                    comoVoy = VOY_PROBAR_OTRA;
                }
                break;
            case VOY_MISMA_DIR:
                if (!resultado){
                    comoVoy = VOY_DIR_CONTRARIA;
                }
                break;
        }
    }
    public int getNextX() {

        return getNextX();
    }

    public int getNextY() {

        return getNextY();
    }
}
