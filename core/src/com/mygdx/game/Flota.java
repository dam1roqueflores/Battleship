package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Flota {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
    //CONSTANTES

    //Barcos que contiene la flota
    private final int NUMBARCOS=4; // NÚMERO DE BARCOS PARA EL ARRAY
    private final int NCELDAS=4;  // NÚMERO DE CELDAS PARA EL ARRAY

    private final int NCELDAS1 = 1; // LOS BARCOS DE TIPO1 TIENEN 3 celdas
    private final int NCELDAS2 = 2; // LOS BARCOS DE TIPO2 TIENEN 4 celdas
    private final int NCELDAS3 = 3; // LOS BARCOS DE TIPO3 TIENEN 5 celdas
    private final int NCELDAS4 = 4; // LOS BARCOS DE TIPO1 TIENEN 6 celdas

    private final int NBARCO1 = 1; // Habrá 4 barcos de TIPO1
    private final int NBARCO2 = 1; // Habrá 3 barcos de TIPO2
    private final int NBARCO3 = 1; // Habrá 2 barcos de TIPO3
    private final int NBARCO4 = 1; // Habrá 1 barco de TIPO4



    // Resto de estados
    private boolean tipoflota; // si es flota amiga será verdadero y false si es enemiga
    private int numFilas; 
    private int numColumnas;
    private int[][] listaTipoBarco; // creamos una lista para cargar los tipos de barcos
    private ArrayList<Barco> listaBarcos;
    private int posxTablero;
    private int posyTablero;
    private int lado;
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////

    // constructor
    ////////////////////////////////////////////////////////////////////////////////////
    public Flota(int misFilas, int misColumnas, int mixTablero, int miytablero, int milado) {



        numFilas=misFilas;
        numColumnas=misColumnas;
        posxTablero=mixTablero;
        posyTablero=miytablero;
        lado=milado;
        
        // inicializamos el array
        listaTipoBarco = new int[NUMBARCOS][NCELDAS];
        listaTipoBarco[0][0]=NCELDAS1;
        listaTipoBarco[0][1]=NBARCO1;
        listaTipoBarco[1][0]=NCELDAS2;
        listaTipoBarco[1][1]=NBARCO2;
        listaTipoBarco[2][0]=NCELDAS3;
        listaTipoBarco[2][1]=NBARCO3;
        listaTipoBarco[3][0]=NCELDAS4;
        listaTipoBarco[3][1]=NBARCO4;

        listaBarcos = new ArrayList<>();
        generarFlotaEnemiga();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // resto de comportamientos
    ////////////////////////////////////////////////////////////////////////////////////
    // creamos la flota amiga
    private void generarFlotaAmiga(){
        // aqui generaremos la flota amiga, de momento es igual que la enemiga
        generarFlotaEnemiga();
    }
    // creamos la flota enemiga
    private void generarFlotaEnemiga(){
        // datos del barco
        int miColumna;
        int miFila;
        int size;
        boolean miDirección;
        // objeto barco para ir añadiendo a la lista de barcos
        Barco miBarco;
        // Semáforo para while
        boolean comprobar;
        // contadores
        int i;

        //recorremos los barcos, en i va la cantidad de barcos a generar
        for (i=0;i<NUMBARCOS;i++) {
            // generamos los barcos comprobando los limites
            comprobar=false;
            while (!comprobar){
                if (Math.random() > 0.5) {
                    miDirección = true;
                } else {
                    miDirección = false;
                }
                miColumna = (int) (Math.random() * numFilas);
                miFila = (int) (Math.random() * numColumnas);
                size=listaTipoBarco[i][1];
                miBarco = new Barco(miDirección,miColumna,miFila,size,posxTablero,posyTablero,lado);
                comprobar=compruebaNuevoBarco(miBarco);
                if (comprobar) {
                    listaBarcos.add(miBarco);
                }
            }
        }
    }
    // comprueba si el barco choca con los límites del tablero
    public boolean compruebaTableroExt(Barco miBarco){
         boolean resultado=true;
         // comprobamos límites del tablero
         if (miBarco.isDireccion()) {
             // la dirección aleatorio es horizontal
             if ((miBarco.getSize()+miBarco.getColumna())>numFilas){
                 // si las filas mas el tamaño del barco es superior a las filas nos salimos
                 resultado=false;
             }
         } else {
             // la dirección aleatoria es vertical
             if ((miBarco.getSize()+ miBarco.getFila())>numColumnas){
                 // si las columnas mas el tamaño del barco es superior a las columnas nos salimos
                 resultado=false;
             }
         }
         return resultado;
    }
// comprueba si el barco choca con algún barco ya creado en la flota, devuelve true si choca y false si no choca.
    public boolean compruebaBarcos(Barco miBarco){
        boolean resultado=true;
        int Cbarcos; //Contador de barcos en la flota
        int Cfilas;
        int Ccolumnas;
        int miFila;
        int miColumna;
         // recorremos los barcos ya almacenados en la flota para compararlos con el barco que nos mandan a comprobar
       Cbarcos=0;
        Cfilas=0;
        Ccolumnas=0;
        miFila=0;
        miColumna=0;
        while (resultado && Cbarcos<=listaBarcos.size()) {
            while (Cfilas<=miBarco.getFila()+miBarco.getSize() && resultado){
                while (Ccolumnas<=miBarco.getColumna()+miBarco.getSize() && resultado){
                    if (listaBarcos.size()>0){
                        if (listaBarcos.get(Cbarcos).getFila()==miBarco.getFila()+miFila && listaBarcos.get(Cbarcos).getColumna()==miBarco.getColumna()+miColumna){
                            resultado=false;
                        }
                    }
                    Ccolumnas++;
                }
                Cfilas++;
            }

            Cbarcos++;
        }
        return resultado;
    }
// compueba barco y tablero
    public boolean compruebaNuevoBarco(Barco miBarco){
        if (listaBarcos.size()>0)
            return compruebaTableroExt(miBarco) && compruebaBarcos(miBarco);
        else{
            return compruebaTableroExt(miBarco);
        }
    }
    // pinta la flota
    public void pintarse(SpriteBatch miSB){

        for (Barco mibarco:listaBarcos) {
            mibarco.pintarse(miSB);
        }
    }
    // comprueba que están todos los barcos hundidos devuelve true si quedan barcos por hundir y false si están todos hundidos
    public boolean quedanBarcos(){
        boolean resultado=false;
        int contador=0;
        Barco miBarco;

        while (contador<listaBarcos.size() && !resultado) {
            if (listaBarcos.get(contador).isEstaHundido()){
                resultado=false;
            } else{
                resultado=true;
            }
        }
        return resultado;
    }
    // liberamos recursos
    public void dispose(){
//        this.dispose();
    }
}
