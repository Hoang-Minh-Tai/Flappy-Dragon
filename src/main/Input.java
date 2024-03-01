package main;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private boolean[] keyPressed;
    private boolean[] keyReleased;
    private int mouseX, mouseY;
    private boolean mousePressed, mouseReleased;

    public Input() {
        keyPressed = new boolean[255];
        keyReleased = new boolean[255];
    }

    public boolean isKeyPressedOnce(int keycode) {
        return keyReleased[keycode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed[e.getKeyCode()] = false;
        keyReleased[e.getKeyCode()] = true;
    }

    public void clear() {
        keyReleased = new boolean[255];
        mouseReleased = false;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public boolean isMouseReleased() {
        return mouseReleased;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        mouseReleased = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
