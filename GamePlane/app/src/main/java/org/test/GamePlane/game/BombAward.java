package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BombAward extends MoveSprite{
    public BombAward(Bitmap bitmap) {
        super(bitmap, 30);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Player player = GameView.getPlayer();
        if(null == player) return;
        if(isHit(player.getDstRectF())){
            GameView.destroyAllEnemy();
            destroyed();
        }
    }
}
