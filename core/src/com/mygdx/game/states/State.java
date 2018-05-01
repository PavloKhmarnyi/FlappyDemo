package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    protected State(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();

    /**
     * delta time is the duration time between last frame render and next frame render
     */
    public abstract void update(float delta);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
