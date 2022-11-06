package org.test.GamePlane.game;

import android.graphics.Bitmap;

public class Bullet extends MoveSprite{
    private int power = 0;

    public Bullet(Bitmap bitmap, int speed, int power) {
        super(bitmap, speed);
        this.power = power;
    }

}
