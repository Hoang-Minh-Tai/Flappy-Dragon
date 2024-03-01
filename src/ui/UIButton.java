package ui;

import java.awt.*;

public class UIButton extends UIComponent {
    private UIScript onClickScript;
    private boolean isHover;
    private boolean isPressed;
    private boolean isReleased;
    private UIText text;

    public UIButton() {
        this.text = new UIText();
    }

    public void centerText() {
        text.setPosition(x + marginLeft + (width - text.width) / 2, y + marginTop + (height - text.height) / 2 );
    }

    public void setContent(String content) {
        text.setContent(content);
        centerText();
    }

    @Override
    public void update() {
        super.update();
        if (onClickScript != null && isReleased && isVisible) {
            onClickScript.run();
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        text.setVisible(visible);
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        text.render(graphics);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        centerText();
    }


    public void addOnClickScript(UIScript script) {
        this.onClickScript = script;
    }

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }

    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }
}
