package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	/////////////////////////////////////////////////
    //  ESTADOS
    private Controlador vgControlador;
	///////////////////////////////////////////////////
    //      CONSTRUCTOR
    @Override
	public void create () {
		/*batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");*/
        vgControlador = new Controlador();
	}
    ///////////////////////////////////////////////////
    //      RESTO DE COMPORTAMIENTOS
	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
        vgControlador.render();
	}
	
	@Override
	public void dispose () {
		/*batch.dispose();
		img.dispose();*/
		vgControlador.dispose();
	}
}
