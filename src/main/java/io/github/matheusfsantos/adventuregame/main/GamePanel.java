package io.github.matheusfsantos.adventuregame.main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final Integer GAME_FPS = 60;
    private final Integer SCALE = 3;
    private final Integer ORIGINAL_TILE_SIZE = 16; /* 16 x 16 */

    private final Integer TILE_SIZE = this.ORIGINAL_TILE_SIZE * this.SCALE; /* 48 x 48 (16 x 3) */
    private final Integer MAX_SCREEN_COL = 16;
    private final Integer MAX_SCREEN_ROW = 12;
    /* maxScreenCol(16):maxScreenRow(12) == 4:3 (simplified by 2) */

    private final Integer SCREEN_WIDTH = this.TILE_SIZE * this.MAX_SCREEN_COL; /* 768px */
    private final Integer SCREEN_HEIGHT = this.TILE_SIZE * this.MAX_SCREEN_ROW; /* 576px */

    private final KeyHandler KEY_HANDLER = new KeyHandler();

    private Integer playerX = this.SCREEN_WIDTH /2;
    private Integer playerY = this.SCREEN_HEIGHT /2;
    private final Integer playerSpeed = 4;

    private Thread GAME_THREAD;

    public GamePanel() {
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(this.SCREEN_WIDTH, this.SCREEN_HEIGHT));

        this.setFocusable(true); /* This unlocks the GamePanel to receive keyboard inputs */
        this.addKeyListener(this.KEY_HANDLER);
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

        if(this.KEY_HANDLER.UP_PRESSED) {
            if(this.playerY - this.playerSpeed != 0)
                this.playerY -= this.playerSpeed;
        } else if(this.KEY_HANDLER.DOWN_PRESSED) {
            if(this.playerY + this.playerSpeed != this.SCREEN_HEIGHT - this.TILE_SIZE)
                this.playerY += this.playerSpeed;
        } else if(this.KEY_HANDLER.LEFT_PRESSED) {
            if(this.playerX - this.playerSpeed != 0)
               this.playerX -= this.playerSpeed;
        } else if(this.KEY_HANDLER.RIGHT_PRESSED) {
            if(this.playerX + this.playerSpeed != this.SCREEN_WIDTH - this.TILE_SIZE)
                this.playerX += this.playerSpeed;
        }
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D graphic2D = (Graphics2D) graphic;

        graphic2D.setColor(Color.white);
        graphic2D.fillRect(this.playerX, this.playerY, this.TILE_SIZE, this.TILE_SIZE);
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

        long timer = 0;
        int drawCount = 0;

        while(this.GAME_THREAD != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) /drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                this.update();
                repaint(); /* Call paintComponent() */
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
