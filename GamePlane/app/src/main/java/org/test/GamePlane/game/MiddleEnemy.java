package org.test.GamePlane.game;

import android.graphics.Bitmap;

public class MiddleEnemy extends Enemy{
    public MiddleEnemy(Bitmap bitmap) {
        super(bitmap, 5, 8);
        setScore(300);
    }
}
