package org.test.GamePlane.game;

import android.graphics.Bitmap;

public class SmallEnemy extends Enemy{
    public SmallEnemy(Bitmap bitmap) {
        super(bitmap, 8, 3);
        setScore(100);
    }
}
