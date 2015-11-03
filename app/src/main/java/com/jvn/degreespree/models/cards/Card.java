package com.jvn.degreespree.models.cards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.jvn.degreespree.models.Player;

/**
 * Created by john on 10/10/15.
 */
public abstract class Card {
    protected Integer imageRef;

    abstract public void play(Player player);

    public Integer getImageRef() {
        return imageRef;
    }
}
