package io.github.matheusfsantos.adventuregame.entity;

import io.github.matheusfsantos.adventuregame.main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler, Integer X, Integer Y, Integer speed, PlayerDirection direction) {
        super(X, Y, speed, direction);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            super.setUp1(ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png")));
            super.setUp2(ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png")));
            super.setDown1(ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png")));
            super.setDown2(ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png")));
            super.setLeft1(ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png")));
            super.setLeft2(ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png")));
            super.setRight1(ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png")));
            super.setRight2(ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png")));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public void update() {
        if(this.keyHandler.RIGHT_PRESSED || this.keyHandler.LEFT_PRESSED || this.keyHandler.UP_PRESSED || this.keyHandler.DOWN_PRESSED) {
            if(this.keyHandler.UP_PRESSED) {
                if(this.getPositionY() - this.getSpeed() != 0) {
                    super.setDirection(PlayerDirection.UP);
                    super.setPositionY(super.getPositionY() - super.getSpeed());
                }
            } else if(this.keyHandler.DOWN_PRESSED) {
                if(super.getPositionY() + super.getSpeed() != this.gamePanel.SCREEN_HEIGHT - this.gamePanel.TILE_SIZE) {
                    setDirection(PlayerDirection.DOWN);
                    super.setPositionY(super.getPositionY() + super.getSpeed());
                }
            } else if(this.keyHandler.LEFT_PRESSED) {
                if(super.getPositionX() - super.getSpeed() != 0) {
                    super.setDirection(PlayerDirection.LEFT);
                    super.setPositionX(super.getPositionX() - this.getSpeed());
                }
            } else {
                if(super.getPositionX() + super.getSpeed() != this.gamePanel.SCREEN_WIDTH - this.gamePanel.TILE_SIZE) {
                    super.setDirection(PlayerDirection.RIGHT);
                    super.setPositionX(super.getPositionX() + this.getSpeed());
                }
            }

            super.setSpriteCounter(super.getSpriteCounter() + 1);
            if (super.getSpriteCounter() > 10) {
                if (super.getSpriteNumber() == 1)
                    super.setSpriteNumber(super.getSpriteNumber() + 1); /* 2 */
                else if (super.getSpriteNumber() == 2)
                    super.setSpriteNumber(super.getSpriteNumber() - 1); /* 1 */

                super.setSpriteCounter(0);
            }
        }
    }

    public void draw(Graphics2D graphic2D) {
        BufferedImage playerImage = null;

        switch (super.getDirection()) {
            case PlayerDirection.UP:
                if(super.getSpriteNumber() == 1)
                    playerImage = super.getUp1();
                if(super.getSpriteNumber() == 2)
                    playerImage = super.getUp2();
                break;
            case PlayerDirection.DOWN:
                if(super.getSpriteNumber() == 1)
                    playerImage = super.getDown1();
                if(super.getSpriteNumber() == 2)
                    playerImage = super.getDown2();
                break;
            case PlayerDirection.LEFT:
                if(super.getSpriteNumber() == 1)
                    playerImage = super.getLeft1();
                if(super.getSpriteNumber() == 2)
                    playerImage = super.getLeft2();
                break;
            case PlayerDirection.RIGHT:
                if(super.getSpriteNumber() == 1)
                    playerImage = super.getRight1();
                if(super.getSpriteNumber() == 2)
                    playerImage = super.getRight2();
                break;
        };

        graphic2D.drawImage(playerImage, super.getPositionX(), super.getPositionY(), this.gamePanel.TILE_SIZE, this.gamePanel.TILE_SIZE, null);
    }
}
