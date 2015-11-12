package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by john on 11/1/15.
 */
public class CECS174 extends Card {

    public CECS174() {
        cardName = "CECS174";
        imageRef = R.drawable.cecs174;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 14);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(1,0,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
