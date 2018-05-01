package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 50;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 140;
    private static final int LOWEST_OPENING = 120;

    private Texture topTube;
    private Texture bottomTube;

    private Vector2 positionTopTube;
    private Vector2 positionBottomTube;
    private Rectangle boundsTop;
    private Rectangle boundsBottom;
    private Random random;

    public Tube(float x) {
        topTube = new Texture("green-tube-inverse.png");
        bottomTube = new Texture("green-tube.png");
        random = new Random();

        positionTopTube = new Vector2(x,random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x,positionTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(positionTopTube.x, positionTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(positionBottomTube.x, positionBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }

    public void reposition(float x){
        positionTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP+LOWEST_OPENING);
        positionBottomTube.set(x, positionTopTube.y - TUBE_GAP-bottomTube.getHeight());
        boundsTop.setPosition(positionTopTube.x, positionBottomTube.y);
        boundsBottom.setPosition(positionBottomTube.x, positionBottomTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
