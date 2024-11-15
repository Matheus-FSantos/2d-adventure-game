package io.github.matheusfsantos.adventuregame.main;

import io.github.matheusfsantos.adventuregame.entity.Player;
import io.github.matheusfsantos.adventuregame.entity.PlayerDirection;
import io.github.matheusfsantos.adventuregame.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final Integer GAME_FPS = 60 ;
    private final Integer SCALE = 3;
    private final Integer ORIGINAL_TILE_SIZE = 16; /* 16 x 16 */

    public final Integer TILE_SIZE = this.ORIGINAL_TILE_SIZE * this.SCALE; /* 48 x 48 (16 x 3) */
    public final Integer MAX_SCREEN_COL = 16;
    public final Integer MAX_SCREEN_ROW = 12;
    /* maxScreenCol(16):maxScreenRow(12) == 4:3 (simplified by 2) */

    public final Integer SCREEN_WIDTH = this.TILE_SIZE * this.MAX_SCREEN_COL; /* 768px */
    public final Integer SCREEN_HEIGHT = this.TILE_SIZE * this.MAX_SCREEN_ROW; /* 576px */

    private TileManager TILE_MANAGER = new TileManager(this);;
    private Thread GAME_THREAD;
    private final Player PLAYER;

    public GamePanel() {
        KeyHandler KEY_HANDLER = new KeyHandler();

        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(this.SCREEN_WIDTH, this.SCREEN_HEIGHT));

        this.setFocusable(true); /* This unlocks the GamePanel to receive keyboard inputs */
        this.addKeyListener(KEY_HANDLER);

        this.PLAYER = new Player(this, KEY_HANDLER, this.SCREEN_WIDTH /2, this.SCREEN_HEIGHT /2, 4, PlayerDirection.DOWN);
    }

    public void startGameThread() {
        this.GAME_THREAD = new Thread(this);
        this.GAME_THREAD.start();
    }

    public void update() {
        /*
        * X values increases to the right
        * Y values increases as they go down
        * */

        this.PLAYER.update();
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D graphic2D = (Graphics2D) graphic;

        this.TILE_MANAGER.draw(graphic2D);
        this.PLAYER.draw(graphic2D);
        graphic2D.dispose(); /* To save memory by discarding useless graphic2d variable */
    }

    /*
     * This method make two things:
     *
     * 1 - Update: Update information such as character position;
     * 2 - Draw: draw the screen with the updated information.
     *
     * */
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /this.GAME_FPS; /* 1000000000 = 1sec in nanoseconds */
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(this.GAME_THREAD != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) /drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                this.update();
                repaint(); /* Call paintComponent() */
                delta--;
            }
        }
    }
}
