package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.sprites.Tube;
import com.mygdx.game.sprites.Witch;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Witch witch;
    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;
    private Array<Tube> tubes;

    protected PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        witch = new Witch(0, 250);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.jpg");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x - camera.viewportWidth , GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((camera.position.x - camera.viewportWidth) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube((i) * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            witch.jump();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
        witch.update(delta);
        updateGround();
        camera.position.x = witch.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(witch.getBounds())) {
                gameStateManager.set(new PlayState(gameStateManager));
            }
        }

        if (witch.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gameStateManager.set(new PlayState(gameStateManager));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(witch.getWitch(), witch.getPosition().x, witch.getPosition().y, Witch.WIDTH, Witch.HEIGHT);

        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }
        spriteBatch.draw(ground, groundPosition1.x, groundPosition1.y);
        spriteBatch.draw(ground, groundPosition2.x, groundPosition2.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        witch.dispose();
        background.dispose();
        ground.dispose();

        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("PlayState disposed");
    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition1.x + ground.getWidth()) {
            groundPosition1.add(ground.getWidth() * 2, 0);
        }

        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition2.x + ground.getWidth()) {
            groundPosition2.add(ground.getWidth() * 2, 0);
        }
    }
}
