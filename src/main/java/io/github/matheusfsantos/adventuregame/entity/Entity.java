package io.github.matheusfsantos.adventuregame.entity;

import java.awt.image.BufferedImage;


public class Entity {
    private Integer positionX;
    private Integer positionY;
    private Integer speed;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private PlayerDirection direction;

    private Integer spriteNumber;
    private Integer spriteCounter;

    public Entity() {}

    public Entity(Integer positionX, Integer positionY, Integer speed, PlayerDirection direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.direction = direction;

        this.spriteNumber = 1;
        this.spriteCounter = 0;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public PlayerDirection getDirection() {
        return direction;
    }

    public void setDirection(PlayerDirection direction) {
        this.direction = direction;
    }

    public Integer getSpriteNumber() {
        return spriteNumber;
    }

    public void setSpriteNumber(Integer spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public Integer getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(Integer spriteCounter) {
        this.spriteCounter = spriteCounter;
    }
}
