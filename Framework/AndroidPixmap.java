package com.esark.framework;

import android.graphics.Bitmap;
import com.esark.framework.Graphics.PixmapFormat;
import com.esark.framework.Pixmap;

import java.lang.ref.WeakReference;

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;
    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public PixmapFormat getFormat() {
        return format;
    }

    public void dispose() {
        bitmap.recycle();
        bitmap = null;
    }
}
