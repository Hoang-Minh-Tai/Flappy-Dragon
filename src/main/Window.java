package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Window extends Frame {
    private Canvas canvas;
    private Input input;
    private Game game;
    private int fps, ups;
    private long nextStatTime;
    public Window() throws HeadlessException {
        init();
        run();
    }

    private void init() {
        canvas = new Canvas();
        input = new Input();
        canvas.setSize(500,500);
        game = new Game(canvas, input );

        setBounds(450,100,500,500);
        setResizable(false);
        setTitle("Flappy Dragon");
        try {
            setIconImage(ImageIO.read(new File("resource/sprite/dragon1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        canvas.requestFocus();
        addKeyListener(input);
        canvas.addKeyListener(input);
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        add(canvas);
        pack();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);

    }

    private void run() {
        double updateRate = 1.0d / 60;

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double accumulator = 0;


        while(true) {
            endTime = System.currentTimeMillis();
            accumulator += (endTime - startTime) / 1000d;
            startTime = endTime;

            while (accumulator > updateRate) {
                game.update();
                game.render();
                ups++;
                fps++;
                accumulator -= updateRate;
            }
        }
    }


    private void printStat() {
        if (System.currentTimeMillis() > nextStatTime) {
            System.out.printf("FPS: %d -- UPS: %d%n", fps, ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }
}
