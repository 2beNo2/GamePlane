package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;


public class Sprite {
    private Paint paint = new Paint();
    private Bitmap bitmap = null;
    private float x = 0;
    private float y = 0;
    private boolean isDestroy = false;
    private int frame = 0; //绘制次数
    private Rect srcRect;
    private RectF dstRectF;

    public Sprite(Bitmap bitmap){
        this.bitmap = bitmap;
        srcRect = new Rect(0, 0, getBitmapWidth(), getBitmapHeight());
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void move(float offsetX, float offsetY){
        x += offsetX;
        y += offsetY;
    }

    public void moveTo(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean isDestroy() {
        return isDestroy;
    }

    public void destroyed() {
        bitmap = null;
        this.isDestroy = true;
    }

    public int getFrame() {
        return frame;
    }

    public int getBitmapWidth(){
        if(bitmap != null){
            return bitmap.getWidth();
        }
        return 0;
    }

    public int getBitmapHeight(){
        if(bitmap != null){
            return bitmap.getHeight();
        }
        return 0;
    }

    public Rect getSrcRect(){
        return srcRect;
    }

    public RectF getDstRectF(){
        dstRectF = new RectF(getX(), getY(), getX() + getBitmapWidth(), getY() + getBitmapHeight());
        return dstRectF;
    }

    public boolean isContains(float x, float y){
        return getDstRectF().contains(x, y);
    }

    public boolean isHit(RectF rect) {
        return getDstRectF().intersect(rect);
    }

    public void onDraw(Canvas canvas){
        if(!isDestroy() && this.bitmap != null){
            Rect srcRef = getSrcRect();
            RectF dstRecF = getDstRectF();
            canvas.drawBitmap(bitmap, srcRef, dstRecF, paint);
        }

        RectF rect = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        if (!rect.intersect(getDstRectF())) {
            destroyed();
        }
        frame++;
    }

}
