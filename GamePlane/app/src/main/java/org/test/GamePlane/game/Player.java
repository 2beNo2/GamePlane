package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Player extends Sprite{
    private int model = 1;

    public Player(Bitmap bitmap) {
        super(bitmap);
    }

    public void setModel(){
        this.model++;
    }

    public int getModel(){
        return model;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        ArrayList<Enemy> enemies = GameView.getAllEnemys();
//        for(Enemy enemy : enemies){
//            if(isHit(enemy.getDstRectF())){
//                Explosion explosion = new Explosion(GameView.getBitmapByIndex(6), 14, 2);
//                explosion.moveTo(getX(), getY());
//                GameView.addSpriteObj(explosion);
//                destroyed();
//                GameView.restart();
//            }
//        }

        switch (getModel()){
            case 1:{
                if(getFrame() % 10 == 0){
                    BlueBullet blueBullet = new BlueBullet(GameView.getBitmapByIndex(2));
                    blueBullet.moveTo(getX() + getBitmapWidth() / 2, getY());
                    GameView.addSpriteObj(blueBullet);
                }
                break;
            }

            case 2:{
                if(getFrame() % 8 == 0){
                    BlueBullet blueBullet = new BlueBullet(GameView.getBitmapByIndex(2));
                    blueBullet.moveTo(getX() + getBitmapWidth() / 2 - 20, getY());
                    GameView.addSpriteObj(blueBullet);

                    blueBullet = new BlueBullet(GameView.getBitmapByIndex(2));
                    blueBullet.moveTo(getX() + getBitmapWidth() / 2 + 20, getY());
                    GameView.addSpriteObj(blueBullet);
                }
                break;
            }

            case 3:{
                if(getFrame() % 5 == 0){
                    BlueBullet blueBullet = new BlueBullet(GameView.getBitmapByIndex(2));
                    blueBullet.moveTo(getX() + getBitmapWidth() / 2 - 30, getY());
                    GameView.addSpriteObj(blueBullet);

                    blueBullet = new BlueBullet(GameView.getBitmapByIndex(2));
                    blueBullet.moveTo(getX() + getBitmapWidth() / 2 + 30, getY());
                    GameView.addSpriteObj(blueBullet);

                    blueBullet = new BlueBullet(GameView.getBitmapByIndex(2));
                    blueBullet.moveTo(getX() + getBitmapWidth() / 2 - 10, getY());
                    GameView.addSpriteObj(blueBullet);

                    blueBullet = new BlueBullet(GameView.getBitmapByIndex(2));
                    blueBullet.moveTo(getX() + getBitmapWidth() / 2 + 10, getY());
                    GameView.addSpriteObj(blueBullet);
                }
                break;
            }

            default:{
                YellowBullet yellowBullet = new YellowBullet(GameView.getBitmapByIndex(10));
                GameView.addSpriteObj(yellowBullet);
                break;
            }
        }

    }
}
