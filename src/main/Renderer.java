package main;

import entity.Dragon;
import entity.Pillar;
import ui.UIComponent;
import util.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Renderer {
    private Game game;
    private Dragon dragon;
    private List<Pillar> pillarList;
    private List<UIComponent> componentList;

    private Image background;

    public Renderer(Game game) {
        this.game = game;
        dragon = game.getDragon();
        pillarList = game.getPillarList();
        componentList = game.getUiComponentList();

        loadBackgroundImage();
    }

    private void loadBackgroundImage() {
        try {
            background = ImageIO.read(new File("resource/sprite/volcano.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void render() {
        Canvas canvas = game.getCanvas();
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        if (bufferStrategy == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        BufferedImage offScreenBuffer = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics offScreenGraphics = offScreenBuffer.getGraphics();

        drawBackground(offScreenGraphics);
        drawDragon(offScreenGraphics);
        drawPillars(offScreenGraphics);
        drawUI(offScreenGraphics);

        graphics.drawImage(offScreenBuffer, 0, 0, null);

        // Dispose of graphics objects
        offScreenGraphics.dispose();
        graphics.dispose();

        bufferStrategy.show();
    }

    private void drawUI(Graphics graphics) {
        componentList.stream().forEach(component -> component.render(graphics));
    }

    private void drawPillars(Graphics graphics) {
        pillarList.stream().forEach(pillar -> {pillar.render(graphics);
        });
    }

    public void drawBackground(Graphics graphics) {
        graphics.drawImage(background,0,0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT, null);
    }

    private void drawDragon(Graphics graphics) {
        graphics.drawImage(dragon.getSprite(), 30, dragon.getIntY(),null);
    }

}
