package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game_LeiternUndSchlagen extends Game {
	SpriteBatch batch;
	Texture img;
	private Game g;
	public Game_LeiternUndSchlagen(){
		g=this;
	}
	@Override
	public void create () {
	Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	g.setScreen(new GamingScreen(g));
	}

}
