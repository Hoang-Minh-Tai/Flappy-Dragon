package ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIContainer extends UIComponent{
    private List<UIComponent> children;

    public UIContainer() {
        children = new ArrayList<>();
    }

    public void add(UIComponent component) {
        children.add(component);
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        children.stream().forEach(child -> child.render(graphics));
    }

    @Override
    public void update() {
        super.update();
        children.stream().forEach(child -> child.update());
    }
}
