package com.jvn.degreespree.models.cards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by john on 10/10/15.
 */
public abstract class Card {
    protected Integer imageRef;
    protected String cardName = "card";
    protected GameController controller;
    protected Player playedBy;

    public void bind(GameController controller) {
        this.controller = controller;
    }

    public Integer getImageRef() {
        return imageRef;
    }

    @Override
    public String toString() {
        return cardName;
    }

    public void play(Player player) {
        playedBy = player;
        boolean correctPos = correctPosition(playedBy);
        boolean metRequirements = meetsRequirements(playedBy);

        if (correctPos && metRequirements) {
            success(new Reward(0,0,0,0,true,true));
        } else if (!correctPos && metRequirements){
            fail(new Reward(0,0,0,0,false,true));
        } else if (correctPos && !metRequirements) {
            fail(new Reward(0,0,0,0,true,false));
        } else if (!correctPos && !metRequirements) {
            fail(new Reward(0,0,0,0,false,false));
        }
    }

    abstract protected boolean correctPosition(Player player);

    abstract protected boolean meetsRequirements(Player player);

    abstract protected void success(Reward reward);

    abstract protected void fail(Reward reward);
}
