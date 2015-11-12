package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by john on 11/1/15.
 */
public class ChooseMajor extends Card {

    public ChooseMajor() {
        cardName = "Choose Major";
        imageRef = R.drawable.choosemajor;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 19);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        if (player.getIntegrity() >= 3) return true;
        else return false;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,0,0,5);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        reward.add(0,0,0,-3);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
