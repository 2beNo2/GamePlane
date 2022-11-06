package org.test.GamePlane.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import org.test.GamePlane.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class GameView extends View {
    static public ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    static public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    static public ArrayList<Sprite> spritesNeedAdded = new ArrayList<Sprite>();
    private Paint paint = new Paint();
    static public int widthPixels = 0;
    static public int heightPixels = 0;
    private int y1 = 0;
    private int y2 = 0;
    private boolean isDown = false;
    private float oldX = 0;
    private float oldY = 0;
    private Context context;
    private Player player;
    private SmallPause smallPause;
    private BigPause bigPause;
    private int frame = 0;
    private Random random = new Random();
    static public long score = 0;
    static public boolean isPause = false;

    public GameView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init(){
        int[] bitmapIds = {
                R.drawable.bg,
                R.drawable.plane,
                R.drawable.blue_bullet,
                R.drawable.small,
                R.drawable.middle,
                R.drawable.big,
                R.drawable.explosion,
                R.drawable.pause1,
                R.drawable.bomb_award,
                R.drawable.bullet_award,
                R.drawable.yellow_bullet,
                R.drawable.pause2,
        };

        for(int bitmapId : bitmapIds){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bitmapId);
            bitmaps.add(bitmap);
        }

        //获取屏幕分辨率
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        widthPixels = dm.widthPixels;
        heightPixels = dm.heightPixels;
        y1 = -heightPixels;
        y2 = 0;

        //初始化玩家飞机
        player = new Player(getBitmapByIndex(1));
        player.moveTo((float) (widthPixels / 2 - player.getBitmapWidth() / 2),
                (float)(heightPixels - player.getBitmapHeight()));
        addSpriteObj(player);

        smallPause = new SmallPause(getBitmapByIndex(7));
        smallPause.moveTo(0, 0);
        addSpriteObj(smallPause);

    }

//    static public void restart(){
//        for (Sprite s : sprites) {
//            if (s instanceof Enemy) {
//                s.destroyed();
//            }
//        }
//
//        Player player = new Player(getBitmapByIndex(1));
//        player.moveTo((float) (widthPixels / 2 - player.getBitmapWidth() / 2),
//                (float)(heightPixels - player.getBitmapHeight()));
//        addSpriteObj(player);
//
//        score = 0;
//    }

    static public Bitmap getBitmapByIndex(int index){
        if(!bitmaps.isEmpty()){
            return bitmaps.get(index);
        }
        return null;
    }

    static public void addSpriteObj(Sprite sprite){
        if(sprite == null)
            return;
        spritesNeedAdded.add(sprite);
    }

    static public ArrayList<Bullet> getAllBullets() {
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        for (Sprite s : sprites) {
            if (s instanceof Bullet) {
                bullets.add((Bullet) s);
            }
        }
        return bullets;
    }

    static public ArrayList<Enemy> getAllEnemys() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (Sprite s : sprites) {
            if (s instanceof Enemy) {
                enemies.add((Enemy) s);
            }
        }
        return enemies;
    }

    static public void destroyAllEnemy(){
        for (Sprite s : sprites) {
            if (s instanceof Enemy) {
                Explosion explosion = new Explosion(GameView.getBitmapByIndex(6), 14, 2);
                explosion.moveTo(s.getX(), s.getY());
                GameView.addSpriteObj(explosion);
                s.destroyed();
            }
        }
    }

    static public Player getPlayer(){
        for (Sprite s : sprites) {
            if (s instanceof Player) {
                return (Player) s;
            }
        }
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getPlayer().isContains(event.getX(), event.getY())) {
                    oldX = event.getX();
                    oldY = event.getY();
                    isDown = true;
                }
                if(smallPause.isContains(event.getX(), event.getY())){
                    isPause = !isPause;
                    if(!isPause){
                        postInvalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isDown = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDown) {
                    float x = event.getX();
                    float y = event.getY();
                    getPlayer().move((int)(x - oldX), (int)(y - oldY));
                    oldX = x;
                    oldY = y;
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        createEnemy();
        drawBackGround(canvas);
        drawText(canvas);

        if(isPause){
            for (Sprite s : sprites){
                ((Sprite)s).onDraw(canvas);
            }
            return;
        }

        //绘制精灵
        sprites.addAll(spritesNeedAdded);
        spritesNeedAdded.clear();
        Iterator<Sprite> it = sprites.iterator();
        while (it.hasNext()){
            Sprite s = it.next();
            if(s.isDestroy()){
                if(s instanceof Enemy){
                    this.score += ((Enemy) s).getScore();
                }
                it.remove();
            }
            else {
                s.onDraw(canvas);
                if(s instanceof YellowBullet){
                    s.destroyed();
                }
            }
        }

        invalidate();
        frame++;
    }

    private void drawBackGround(Canvas canvas){
        Bitmap bitmap = getBitmapByIndex(0);
        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst  = new RectF(0, y1, (float) canvas.getWidth(), (float) canvas.getHeight() + y1);
        canvas.drawBitmap(bitmap, src, dst, paint);
        y1 += 10;
        if (y1 >= heightPixels)
            y1 = -heightPixels;

        dst.set(new RectF(0, y2, (float) canvas.getWidth(), (float) canvas.getHeight() + y2));
        canvas.drawBitmap(bitmap, src, dst, paint);
        y2 += 10;
        if (y2 >= heightPixels)
            y2 = -heightPixels;

        Paint tesxtPaint = new Paint();
        tesxtPaint.setTextSize(50);
        tesxtPaint.setColor(Color.BLACK);
        canvas.drawText(" " + score, smallPause.getBitmapWidth() + 10,
                smallPause.getBitmapHeight() / 2 + 10, tesxtPaint);
    }

    private void drawText(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        canvas.drawText("objects:" + sprites.size(), 200, 200, paint);

    }

    private void createEnemy(){
        if(frame % 60 == 0){
            SmallEnemy smallEnemy = new SmallEnemy(getBitmapByIndex(3));
            smallEnemy.moveTo((random.nextInt() >>> 1) % widthPixels,  0);
            addSpriteObj(smallEnemy);
        }

        if(frame % 120 == 0){
            MiddleEnemy middleEnemy = new MiddleEnemy(getBitmapByIndex(4));
            middleEnemy.moveTo((random.nextInt() >>> 1) % widthPixels,  0);
            addSpriteObj(middleEnemy);
        }

        if(frame % 300 == 0){
            BigEnemy bigEnemy = new BigEnemy(getBitmapByIndex(5));
            bigEnemy.moveTo((random.nextInt() >>> 1) % widthPixels,  0);
            addSpriteObj(bigEnemy);
        }

        if(frame % 600 == 0){
            BombAward bombAward = new BombAward(getBitmapByIndex(8));
            bombAward.moveTo((random.nextInt() >>> 1) % widthPixels,  0);
            addSpriteObj(bombAward);
        }

        if(frame % 800 == 0){
            BulletAward bulletAward = new BulletAward(getBitmapByIndex(9));
            bulletAward.moveTo((random.nextInt() >>> 1) % widthPixels,  0);
            addSpriteObj(bulletAward);
        }
    }

    static public void setPause(boolean status){
        isPause = status;
    }
}
