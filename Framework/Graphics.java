package com.esark.framework;

public interface Graphics {

    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Pixmap newPixmap(String fileName, PixmapFormat format);

    public void clear(int color);

    public void drawPixel(int x, int y, int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawBlackLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color);

    public void drawTestRect(int x, int y);

    public void drawFBRect(int x, int y);

    //  public void drawPixmap(Pixmap pixmap, int x, int y);

    //public void drawBlueJoystick(Pixmap pixmap, int x, int y);

    //public void drawJoystick(Pixmap pixmap, int x, int y);

    public void drawPortraitPixmap(Pixmap pixmap, int x, int y);

    public void drawJoystick(Pixmap pixmap, int x, int y);    //Draws a white sphere bitmap

 //   public void drawLandscapePixmap(Pixmap pixmap, int x, int y);

    public void drawText(String percent, int x, int y);

    public void drawSmallText(String percent, int x, int y);

    void drawCircle(int x, int y, int radius);

    public int getWidth();

    public int getHeight();
}
