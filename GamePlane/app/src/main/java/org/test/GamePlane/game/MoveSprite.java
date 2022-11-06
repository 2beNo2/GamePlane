package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MoveSprite extends Sprite{
    private int speed = 0;

    public MoveSprite(Bitmap bitmap, int speed) {
        super(bitmap);
        this.speed = speed;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        move(0, speed);
    }
}
