package main;

import entity.Dragon;
import entity.Pillar;
import entity.PillarManager;
import ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Canvas canvas;
    private Renderer renderer;
    private Input input;
    private AudioManager audioManager;

    private Dragon dragon;
    private PillarManager pillarManager;
    private List<UIComponent> uiComponentList;

    private static int score;
    private static int highScore;
    private static boolean isGameOver;

    public Game(Canvas canvas, Input input) {
        this.canvas = canvas;
        this.input = input;
        dragon = new Dragon();
        uiComponentList = new ArrayList<>();
        pillarManager = new PillarManager();
        initializeUI();
        renderer = new Renderer(this);

        audioManager = new AudioManager();
        audioManager.playBackgroundMusic();
    }

    private void initializeUI() {
        UIText scoreUI = new UIText();
        scoreUI.setPosition(240,10);
        scoreUI.setContent("0");
        uiComponentList.add(scoreUI);
        scoreUI.addUpdateScript(() -> {
            scoreUI.setVisible(!isGameOver);
            scoreUI.setContent(String.valueOf(score));
        });

        UIText gameOver = new UIText();
        gameOver.setPosition(150,50);
        gameOver.setContent("GAME OVER");
        gameOver.setColor(Color.GREEN);
        gameOver.setShadow(true);
        gameOver.setShadowColor(Color.lightGray);
        gameOver.setFontSize(30);
        gameOver.setVisible(true);
        gameOver.addUpdateScript(() -> gameOver.setVisible(isGameOver));
        uiComponentList.add(gameOver);

        UIContainer scoreDisplay = new UIContainer();
        scoreDisplay.setPosition(100, 150);
        scoreDisplay.setSize(300,200);
        scoreDisplay.addUpdateScript(() -> scoreDisplay.setVisible(isGameOver));
        uiComponentList.add(scoreDisplay);

        UIText showScore = new UIText();
        showScore.setFontSize(20);
        showScore.setPosition(180,200);
        showScore.setColor(Color.ORANGE);
        showScore.addUpdateScript(() -> {
            showScore.setVisible(isGameOver);
            showScore.setContent("Your Score: " + score);
        });
        scoreDisplay.add(showScore);

        UIText showHighScore = new UIText();
        showHighScore.setFontSize(20);
        showHighScore.setPosition(180,250);
        showHighScore.setColor(Color.ORANGE);
        showHighScore.addUpdateScript(() -> {
            showHighScore.setVisible(isGameOver);
            showHighScore.setContent("High Score: " + highScore);
        });
        scoreDisplay.add(showHighScore);

        UIButton newGameBtn = new UIButton();
        newGameBtn.setPosition(100,400);
        newGameBtn.setSize(100,50);
        newGameBtn.setContent("NEW GAME");
        newGameBtn.addUpdateScript(() -> {
            newGameBtn.setVisible(isGameOver);
            newGameBtn.setHover(newGameBtn.containsPoint(input.getMouseX(), input.getMouseY()));
            newGameBtn.setPressed(newGameBtn.isHover() && input.isMousePressed());
            newGameBtn.setReleased(newGameBtn.isHover() && input.isMouseReleased());
        });
        newGameBtn.addOnClickScript(() -> {
            newGame();
        });
        uiComponentList.add(newGameBtn);

        UIButton quit = new UIButton();
        quit.setPosition(300,400);
        quit.setSize(100,50);
        quit.setContent("QUIT");
        quit.addUpdateScript(() -> {
            quit.setVisible(isGameOver);
            quit.setHover(quit.containsPoint(input.getMouseX(), input.getMouseY()));
            quit.setPressed(quit.isHover() && input.isMousePressed());
            quit.setReleased(quit.isHover() && input.isMouseReleased());
        });
        quit.addOnClickScript(() -> {
            System.exit(0);
        });
        uiComponentList.add(quit);

    }

    public void update() {
        handleInput();
        if (!isGameOver) {
            pillarManager.update();
            handleCollision();
            dragon.update();
        }
        uiComponentList.stream().forEach(UIComponent::update);
        input.clear();
    }

    public void newGame() {
        isGameOver = false;
        resetScore();
        pillarManager.clear();
        audioManager.playBackgroundMusic();
        dragon.reset();
    }

    public void endGame() {
        isGameOver = true;
        if (score > highScore) highScore = score;
        audioManager.pauseBackgroundMusic();
    }

    public static void increaseScore() {
        score++;
    }

    public static void resetScore() {
        score = 0;
    }

    private void handleCollision() {
        if (pillarManager.getPillarList().stream().anyMatch(pillar -> pillar.collideWith(dragon))) {
            audioManager.playSound("hit");
            endGame();
        }
    }

    private void handleInput() {
        if (input == null) return;
        if (input.isKeyPressedOnce(KeyEvent.VK_SPACE)) {
            dragon.up();
        }

        if (input.isKeyPressedOnce(KeyEvent.VK_F)) {
            isGameOver = false; 
        }
    }

    public void render() {
        renderer.render();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public List<Pillar> getPillarList() {
        return pillarManager.getPillarList();
    }
    public List<UIComponent> getUiComponentList() {
        return uiComponentList;
    }
}
