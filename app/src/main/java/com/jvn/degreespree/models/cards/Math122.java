package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by john on 11/1/15.
 */
public class Math122 extends Card {

    public Math122() {
        cardName = "Math 122";
        imageRef = R.drawable.math122;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 7);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        // TODO: add select chip dialog
        reward.add(1,1,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
