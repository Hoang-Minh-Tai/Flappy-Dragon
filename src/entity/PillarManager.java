package entity;

import main.Game;
import util.Constant;

import java.util.ArrayList;
import java.util.List;

public class PillarManager {
    private int updateCount = 0;
    private static double timeBetweenPillar = 2;
    private List<Pillar> pillarList;


    public PillarManager() {
        this.pillarList = new ArrayList<>();
        Pillar.loadSprite();
    }

    public void update() {
        updateCount++;
        if (updateCount == timeBetweenPillar * Constant.UPS) {
            addPillars();

            updateCount = 0;
        }

        boolean pass = pillarList.removeIf(pillar -> pillar.x <= 0);
        if (pass) Game.increaseScore();


        pillarList.stream().forEach(Pillar::update);
    }

    private void addPillars() {
        int topPillarHeight = (int) (Constant.PILLAR_MIN_HEIGHT + Math.random() * Constant.PILLAR_RANGE);
        pillarList.add(new Pillar(500, topPillarHeight, true));

        int botPillarHeight = Constant.WINDOW_HEIGHT - topPillarHeight - Constant.PILLAR_HOLE_HEIGHT;
        pillarList.add(new Pillar(500, botPillarHeight, false));
    }

    public void clear() {
        pillarList.clear();
    }
    public List<Pillar> getPillarList() {
        return pillarList;
    }
}
