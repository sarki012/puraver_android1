package com.esark.framework;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;


import com.esark.roboticarm.GetLruCache;

public class AndroidGraphics extends AndroidGame implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();
    public Bitmap resizedBitmap = null;
    public Bitmap joystickBitmap = null;
    public Bitmap blueJoystickBitmap = null;
 //   private Bitmap cacheBitmap = null;
    public static int staticCount = 0;
   // public LruCache cache;
  //  private LruCache<String, Bitmap> mMemoryCache;
    public GetLruCache mMemoryCache = null;

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        Config config = null;
        if (format == PixmapFormat.RGB565)
            config = Config.RGB_565;
        else if (format == PixmapFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;

        return new AndroidPixmap(bitmap, format);
    }

    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
        return;
    }

    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x, y, paint);
    }

    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(30);
        canvas.drawLine(x, y, x2, y2, paint);
        return;
    }

    public void drawBlackLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(80);
        canvas.drawLine(x, y, x2, y2, paint);
        return;
    }

    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
        return;
    }
    public void drawTestRect(int x, int y) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + 500, y + 500, paint);
        return;
    }
    public void drawFBRect(int x, int y) {
            paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + 500, y + 250, paint);
        return;
    }
/*
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
        return;
    }
*/


    public void drawPortraitPixmap(Pixmap pixmap, int x, int y) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(((AndroidPixmap) pixmap).bitmap, 3500, 5000, false);
        canvas.drawBitmap(resizedBitmap, x, y, null);
        return;
    }
    public void drawJoystick(Pixmap pixmap, int x, int y) {     //Draws a white sphere bitmap
        joystickBitmap = Bitmap.createScaledBitmap(((AndroidPixmap) pixmap).bitmap, 350, 350, false);   //Create a scaled red joystick bitmap
        canvas.drawBitmap(joystickBitmap, x, y, null);      //Draw the red joystick on the canvas
        return;
    }
/*
    public void drawLandscapePixmap(Pixmap pixmap, int x, int y) {     //Draws the background bitmap
        mMemoryCache = GetLruCache.get();       //Retrieve the cache. GetLruCache is a singleton so there is only one cache
        if (backgroundCount == 0) {       //First time. Load the cache
            resizedBitmap = Bitmap.createScaledBitmap(((AndroidPixmap) pixmap).bitmap, 5000, 3500, false);      //Create a scaled background bitmap
            canvas.drawBitmap(resizedBitmap, x, y, null);       //Draw the background on the canvas
            addBitmapToMemoryCache("Key", resizedBitmap);       //Add the bitmap to the cache
            backgroundCount = 1;       //Set the flag. We only load the cache once
        }
        canvas.drawBitmap(getBitmapFromMemCache("Key"), x, y, null);        //Draw the background
        return;
    }

 */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {     //Add a bitmap to the cache
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {       //Retrieve a bitmap from the cache
        return (Bitmap) mMemoryCache.get(key);
    }

    public void drawText(String percent, int x, int y) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(250);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(percent, x, y, paint);
        return;
    }

    public void drawSmallText(String percent, int x, int y) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(percent, x, y, paint);
        return;
    }

    public void drawCircle(int x, int y, int radius) {
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, radius, paint);
    }

    public int getWidth() {
        return frameBuffer.getWidth();
    }

    public int getHeight() {
        return frameBuffer.getHeight();
    }


    public Screen getStartScreen() {
        return null;
    }

}