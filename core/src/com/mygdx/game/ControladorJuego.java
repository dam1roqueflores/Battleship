package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;

public class ControladorJuego {
    /**

     * Clase que implementa el controlador de nuestro videojuego. Realizará la gestión de la entrada del teclado,

     * la gestión de la inicialización, del control del estado del videojuego

     */

    /////////////////////////////////////////////////////////////////////////////////////

    //

    //ESTADO

    //

    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTANTES

    static private final int PANTALLA_INICIO = 0;

    static private final int PANTALLA_JUEGO = 1;


    static private final String FICHERO_HR = "portada_hlf.png";

    static private final String FICHERO_MR = "portada_hlf_medium.png";

    static private final String FICHERO_LR = "portada_hlf_low.png";


    static private final int TURNO_ALIADO = 0;

    static private final int TURNO_ENEMIGO = 1;


    static private final int NUMERO_TABLEROS = 2;

    //RESTO DEL ESTADO

    //Variable para saber el estado en el que estamos:

    // 0 . Pantalla inicio

    // 1. Jugando

    protected int estadoJuego;


    //Variable para saber el turno en el que está

    //0 . Turno aliado

    //1 . Turno enemigo

    protected int turno;


    //Fondo del principio de la partida

    protected Texture fondoInicio;


    //Controlador de inteligencia artificial

    protected Pensador controladorMaquina;


    //Los tableros sera una array list de tableros

    protected Tablero tableroJugador;
    protected Tablero tableroIA;

    //Tendremos un SpriteBatch para dibujar en la pantalla

    protected SpriteBatch batch;


    protected int pasos;

    /////////////////////////////////////////////////////////////////////////////////////

    //

    //COMPORTAMIENTO

    //

    /////////////////////////////////////////////////////////////////////////////////////

    //El constructor creará a su vez: personajes iniciales y fondo

    public ControladorJuego(SpriteBatch nuevoSB) {


        //Creamos el objeto batch para dibujar

        batch = nuevoSB;


        inicializarObjetos();


        if (Gdx.graphics.getWidth() > 1000) {

            fondoInicio = new Texture(FICHERO_HR);

        } else if (Gdx.graphics.getWidth() < 1000 && Gdx.graphics.getWidth() > 600) {

            fondoInicio = new Texture(FICHERO_MR);

        } else {

            fondoInicio = new Texture(FICHERO_LR);

        }

        estadoJuego = PANTALLA_INICIO;

        pasos = 0;

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    //Resto de comportamientos

    //El controlador tendrá que saber que pasa cuando hay que pintarse

    public void render() {


        //Primero realizo el control de estado.

        this.render_controlEstado();


        //Borramos para eliminar imágenes previas

        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (estadoJuego == 1) {

            render_dibujarPantallaJuego();

        } else {

            //Pantalla inicial

            render_dibujarPantallaInicial();

        }

    }


    //Método de control del estado. Es interno. Para ayudar al método render

    private void render_controlEstado() {

        if (estadoJuego == 0) {

            render_controlEstadoPantallaInicio();

        } else {

            render_controlEstadoJugando();

        }

    }


    private void render_controlEstadoPantallaInicio () {

        //Actualizo el teclado

        boolean recienTocado;


        recienTocado = Gdx.input.justTouched();

        if (recienTocado) {

            estadoJuego = PANTALLA_JUEGO;

            this.dispose();

            inicializarObjetos();

        }

    }


    //Método de control del estado cuando jugamos.

    private void render_controlEstadoJugando() {


        //Iniciamos el booleanos recientocado en falso

        boolean recienTocado = false;

        //Si estamos en turno aliado, que detecte donde hemos puesto el puntero

        if (turno == TURNO_ALIADO) {

            recienTocado = Gdx.input.justTouched();

            //Si me han tocado la pantalla que me de la x y la y

            if (recienTocado) {//Si ha tocado en la pantalla

                if (!tableroJugador.comprobarDisparo(Gdx.input.getX(), Gdx.input.getY())) {

                    //pasamos turno

                    turno = TURNO_ENEMIGO;

                }

            }

        } else {  //No ha acertado el humano a la hora de tocar un barco. Ha sido agua

            if (pasos == 120) {

                render_controlEstado_turnoIA();

                pasos = 0;

            } else {

                pasos++;

            }

        }

    }


    private void render_controlEstado_turnoIA(){

        int nextX;

        int nextY;

        controladorMaquina.piensa();


        nextX = controladorMaquina.getNextX();

        nextY = controladorMaquina.getNextY();


        if (tableroIA.comprobarDisparo(nextX,nextY)){ //Hemos acertado

            controladorMaquina.jugadaResultado(true);

        } else {

            controladorMaquina.jugadaResultado(false);

        }

    }


    private void render_dibujarPantallaJuego () {


        //Pintar el escenario

        //////////Creo que aquí hay que poner algo de fondo.render o algo así

        //IMPORTANTE

        //PONER UNA IMAGEN DE FONDO CON LOS TITULOS Y DONDE SE PONEN LOS TABLEROS

        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //Pintar los tableros
        tableroIA.pintarse(batch);
        tableroJugador.pintarse(batch);

    }

    private void render_dibujarPantallaInicial () {

        //Pintar el escenario

        //////////

        batch.begin();

        //MIRAR ALGUNA ORDEN POR SI NO ESTÁ BIEN ESCALADO EL FONDO

        batch.draw(fondoInicio, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        batch.end();

    }


    //Al principio, y cada vez que recomencemos la partida, se reinician los objetos

    private void inicializarObjetos () {

        int anchoT = Gdx.graphics.getWidth()/2;

        int altoT = Gdx.graphics.getHeight()/2;


        //Creamos y añadimos los tableros

        tableroJugador = new Tablero(0,0,anchoT,altoT);
        tableroIA = new Tablero(anchoT,altoT,anchoT,altoT);

        //Creamos el turno
        turno = TURNO_ALIADO;

        //Creamos la maquina de IA

        controladorMaquina = new Pensador(tableroIA);

    }


    //El controlador tendrá que saber como finalizar y cerrar recursos

    public void dispose() {

        //aquí tengo que liberar los objetos que integren texturas y también al batch de dibujo

        //batch de dibujo

        if (batch != null) {

            batch.dispose();

        }

        //Tableros

        tableroJugador.dispose();
        tableroIA.dispose();

    }
}