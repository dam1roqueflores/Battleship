package com.iespablopicasso.battleship;

public class Celda {
    /////ESTAD0/////////////////////////////////////////////////////////////////////////

    protected int posX;
    protected int posY;
    protected int lado;
    protected Texture miImagen;
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


    }
    public void pintarse(int posY,int posX, SpriteBatch miSB){
        miSB.begin();
        miSB.draw(miImagen,posX,posY);
        miSB.end();
    }

    /*public void morirse(){
    if (tocada) {
    .dispose();
    }else
*/
    }

    public static void dispose() {
        Celda.dispose();
    }

}




