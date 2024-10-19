package io.github.matheusfsantos.adventuregame.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public Boolean UP_PRESSED = false, DOWN_PRESSED = false, LEFT_PRESSED = false, RIGHT_PRESSED = false;

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        Integer code = e.getKeyCode();

        if(code.equals(KeyEvent.VK_W))
            this.UP_PRESSED = true;

        if(code.equals(KeyEvent.VK_S))
            this.DOWN_PRESSED = true;

        if(code.equals(KeyEvent.VK_D))
            this.RIGHT_PRESSED = true;

        if(code.equals(KeyEvent.VK_A))
            this.LEFT_PRESSED = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Integer code = e.getKeyCode();

        if(code.equals(KeyEvent.VK_W))
            this.UP_PRESSED = false;

        if(code.equals(KeyEvent.VK_S))
            this.DOWN_PRESSED = false;

        if(code.equals(KeyEvent.VK_D))
            this.RIGHT_PRESSED = false;

        if(code.equals(KeyEvent.VK_A))
            this.LEFT_PRESSED = false;
    }
}
