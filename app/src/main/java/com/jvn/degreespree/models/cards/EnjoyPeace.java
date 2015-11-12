package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by john on 11/1/15.
 */
public class EnjoyPeace extends Card {

    public EnjoyPeace() {
        cardName = "Enjoying the Peace";
        imageRef = R.drawable.enjoypeace;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 1);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        return true;
    }

    @Override
    protected void success(Reward reward) {
        //TODO: open dialog
        reward.add(0,1,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}