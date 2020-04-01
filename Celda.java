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
	    protected String nombreesprite;



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
	    public void pintarse(int miposY,int miposX,SpriteBatch miSB){
	        posX=miposX;
	        posY=miposY;
	        
	    	miSB.begin();
	        miSB.draw(miImagen,posX,posY);
	        miSB.end();
	    }
	}

}
