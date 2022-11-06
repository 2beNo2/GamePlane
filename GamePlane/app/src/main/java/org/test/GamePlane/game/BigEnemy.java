package org.test.GamePlane.game;

import android.graphics.Bitmap;

public class BigEnemy extends Enemy{
    public BigEnemy(Bitmap bitmap) {
        super(bitmap, 3, 20);
        setScore(1000);
    }
}
