package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static util.Constant.*;

public class Dragon {
    private BufferedImage sprite1;
    private BufferedImage sprite2;
    private BufferedImage currentSprite;
    private double x,y;

    private double forceY;

    public Dragon() {
        loadSprite();
        reset();
    }

    public void update() {
        forceY += GRAVITY;
        y += forceY;
        if (y >= 450) {
            y = 450;
            forceY = 0;
        }
        if (y <= 10) {
            y = 10;
            forceY = 0;
        }
        currentSprite = forceY <= 0 ? sprite2 : sprite1;
    }

    public void up() {
        forceY = -FORCE_Y;
    }

    public void reset() {
        y = 250;
        x = 30;
        forceY = -5;
    }

    public Rectangle getBound() {
        return new Rectangle((int) x, (int) y, DRAGON_WIDTH, DRAGON_HEIGHT);
    }
    private void loadSprite() {
        try {
            sprite1 = ImageIO.read(new File("resource/sprite/dragon1.png"));
            sprite2 = ImageIO.read(new File("resource/sprite/dragon2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIntY() {
        return (int)y;
    }

    public double getY() {
        return y;
    }

    public BufferedImage getSprite() {
        return currentSprite;
    }
}
