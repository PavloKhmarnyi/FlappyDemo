package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Witch {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;

    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    private Vector2 position;
    private Vector2 velocity;
    private Texture witch;

    private Rectangle bounds;

    public Witch(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        witch = new Texture("witch.png");
        bounds = new Rectangle(x, y,WIDTH - 38,HEIGHT - 18);
    }

    public void update(float delta) {
        if (position.y > 0){
            velocity.add(0, GRAVITY);
        }
        velocity.scl(delta);
        position.add(MOVEMENT * delta, velocity.y);

        if (position.y < 0){
            position.y = 0;
        }
        velocity.scl(1 / delta);
        bounds.setPosition(position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getWitch() {
        return witch;
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        witch.dispose();
    }
}
