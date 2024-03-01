package entity;

import entity.Dragon;
import util.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pillar extends Rectangle {
    private static BufferedImage sprite;

    public Pillar(int x, int height, boolean isTopPillar) {
        super(x, isTopPillar ? 0 : Constant.WINDOW_HEIGHT - height, Constant.PILLAR_WIDTH, height);
    }

    public static void loadSprite() {
        try {
            sprite = ImageIO.read(new File("resource/sprite/pillar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        this.setLocation(x - Constant.PILLAR_SPEED, y);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public boolean collideWith(Dragon dragon) {
        return intersects(dragon.getBound());
    }

    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getSubimage(0,0,width,height), x,y,null);
    }
}