package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Patrick Pachernig on 18.04.2016.
 */
public class GamingScreen  implements Screen,InputProcessor {

    private Game game;
    private Texture img;
    private SpriteBatch batch;
    private TiledMap tm;
    private TiledMapRenderer tmr;
    private OrthographicCamera camera;
    private float w=0;
    private float h=0;
    private Vector2 lastTouch=new Vector2();

    SpriteBatch sbb;
    SpriteBatch sbr;
    SpriteBatch sbg;
    SpriteBatch sbgl;
    Texture texture;
    Sprite sprite;

    public GamingScreen(Game g){
        game=g;
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        tm = new TmxMapLoader().load("UI/LeiternUndSchlangen.tmx");
        camera.setToOrtho(false, w, h);
        camera.update();
        tmr = new OrthogonalTiledMapRenderer(tm);
        Gdx.input.setInputProcessor((InputProcessor) this);

        sbb = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("UI/kegel_blau.png"));
        sprite = new Sprite(texture);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tmr.setView(camera);
        tmr.render();
        sbb.setProjectionMatrix(camera.combined);
        sbb.begin();
        sprite.draw(sbb);
        sbb.end();
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
        lastTouch.set(screenX,screenY);
        Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
        Vector3 position = camera.unproject(clickCoordinates);
        sprite.setPosition(position.x, position.y);
        return true;}



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        Vector2 delta = newTouch.cpy().sub(lastTouch);



        camera.translate(-delta.x,-delta.y);
        lastTouch = newTouch;
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
