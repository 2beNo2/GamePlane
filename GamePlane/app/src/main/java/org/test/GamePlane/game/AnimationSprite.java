package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class AnimationSprite extends Sprite{
    private int segment;
    private int speed;
    private int index = 0;
    private Rect srcRect = new Rect();
    private RectF dstRectF = new RectF();

    public AnimationSprite(Bitmap bitmap, int segment, int speed) {
        super(bitmap);
        this.segment = segment;
        this.speed = speed;
    }

    public int getWidth(){
        return getBitmapWidth() / segment;
    }

    @Override
    public Rect getSrcRect() {
        srcRect.set(index * getWidth(), 0, index * getWidth() + getWidth(), getBitmapHeight());
        return srcRect;
    }

    @Override
    public RectF getDstRectF() {
        dstRectF.set(getX(), getY(), getX() + getWidth(), getY() + getBitmapHeight());
        return dstRectF;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getFrame() % speed == 0) {
            index++;
        }

        if (index == segment) {
            destroyed();
        }
    }
}
