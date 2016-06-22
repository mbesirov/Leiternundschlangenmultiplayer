package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Patrick Pachernig on 18.04.2016.
 */
public class MainScreen implements Screen, InputProcessor {
    private Game game;
    private Stage stage;
    private TextureAtlas ta;
    private Skin skin;
    private Table table;
    private TextButton tb;
    private BitmapFont bf;
    private Label header;
    public MainScreen(Game g){
        game=g;
    }

    @Override
    public void show() {

        stage = new Stage();
        bf = new BitmapFont(Gdx.files.internal("UI/white.fnt"),false);

        Gdx.input.setInputProcessor(stage);
        ta = new TextureAtlas("UI/Button.pack");
        skin = new Skin(ta);
        table = new Table(skin);

        TextButton.TextButtonStyle tbs=new TextButton.TextButtonStyle();
        tbs.up=skin.getDrawable("Button");
        tbs.down=skin.getDrawable("Button");
        tbs.pressedOffsetX = 1;
        tbs.pressedOffsetY = -1;
        tbs.font = bf;

        Label.LabelStyle ls = new Label.LabelStyle(bf, Color.WHITE);
        header = new Label("Leitern und Schlangen",ls);
        tb=new TextButton("Spiel starten",tbs);
        tb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GamingScreen(game));
            }
        });

        table.setFillParent(true);
        table.add(header);
        table.row();
        table.add(tb);
        stage.addActor(table);
        table.debug();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
