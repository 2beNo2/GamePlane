package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Enemy extends MoveSprite{
    private int blood = 0;
    private int score = 0;

    public Enemy(Bitmap bitmap, int speed, int blood) {
        super(bitmap, speed);
        this.blood = blood;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList<Bullet> bullets = GameView.getAllBullets();
        for(Bullet bullet : bullets){
            if(isHit(bullet.getDstRectF())){
                bullet.destroyed();
                --blood;
                if(blood == 0){
                    Explosion explosion = new Explosion(GameView.getBitmapByIndex(6), 14, 2);
                    explosion.moveTo(getX(), getY());
                    GameView.addSpriteObj(explosion);
                    destroyed();
                    break;
                }
            }
        }
    }

}
