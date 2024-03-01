package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class UIComponent {
    protected int x, y;
    protected int width, height;
    protected int marginTop, marginLeft;
    protected static Image sprite;
    protected boolean isVisible;

    protected UIScript script;
    public UIComponent() {
        x = 10;
        y = 10;
        width = 100;
        height = 100;
        sprite = getDefaultSprite();
        isVisible = true;
    }

    public void update() {
        if (script != null) script.run();
    }

    public void render(Graphics graphics) {
        if (!isVisible) return;
        graphics.drawImage(sprite.getScaledInstance(width,height,Image.SCALE_REPLICATE), x, y, width, height, null);
    }

    public void addUpdateScript(UIScript script) {
        this.script = script;
    }

    private void calculateSprite() {
        sprite = sprite.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }

    private Image getDefaultSprite() {
        if (sprite != null) return sprite;
        try {
            return ImageIO.read(new File("resource/sprite/frame.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsPoint(int x, int y) {
        if (x < this.x || y < this.y || x > this.x + width || y > this.y + height) return false;
        return true;
    }
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        calculateSprite();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        calculateSprite();
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
        calculateSprite();
    }
}
