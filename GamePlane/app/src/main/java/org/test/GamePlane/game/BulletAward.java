package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BulletAward extends MoveSprite{
    public BulletAward(Bitmap bitmap) {
        super(bitmap, 25);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Player player = GameView.getPlayer();
        if(null == player) return;
        if(isHit(player.getDstRectF())){
            player.setModel();
            destroyed();
        }
    }
}
