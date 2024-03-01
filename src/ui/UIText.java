package ui;

import java.awt.*;

public class UIText extends UIComponent{
    private String content;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color color;

    private boolean hasShadow;
    private int shadowOffset;
    private Color shadowColor;

    private Font font;

    public UIText() {
        this.fontSize = 15;
        this.fontStyle = Font.BOLD;
        this.fontFamily = Font.SANS_SERIF;
        this.color = Color.ORANGE;
        this.content = "empty";

        this.hasShadow = false;
        this.shadowOffset = 1;
        this.shadowColor = Color.LIGHT_GRAY;
    }

    public void setContent(String content) {
        this.content = content;
        calculateFont();
    }

    private void calculateFont() {
        font = new Font(fontFamily, fontStyle, fontSize);
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        this.width = metrics.stringWidth(content);
        this.height = metrics.getHeight();

    }

    @Override
    public void render(Graphics graphics) {
        if (!isVisible)return;
        graphics.setFont(font);
        if (hasShadow) {
            graphics.setColor(shadowColor);
            graphics.drawString(content, x + shadowOffset + marginLeft, y + fontSize + shadowOffset + marginTop);
        }

        graphics.setColor(color);
        graphics.drawString(content, x + marginLeft, y + fontSize + marginTop);
    }

    public void setShadow(boolean hasShadow) {
        this.hasShadow = hasShadow;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        calculateFont();
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
        calculateFont();
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        calculateFont();
    }

    public void setHasShadow(boolean hasShadow) {
        this.hasShadow = hasShadow;
    }

    public void setShadowOffset(int shadowOffset) {
        this.shadowOffset = shadowOffset;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    public String getContent() {
        return content;
    }
}
