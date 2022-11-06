package org.test.GamePlane.game;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class YellowBullet extends Bullet{
    public YellowBullet(Bitmap bitmap) {
        super(bitmap, 0, 0);
    }

    @Override
    public RectF getDstRectF() {
        Player player = GameView.getPlayer();
        RectF rectF = new RectF(player.getX() + player.getBitmapWidth() / 2 - 10,
                0,
                player.getX() + player.getBitmapWidth() / 2 + 10,
                player.getY());
        return rectF;
    }
}
