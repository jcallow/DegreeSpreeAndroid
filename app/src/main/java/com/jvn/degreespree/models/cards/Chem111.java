package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by john on 11/11/15.
 */
public class Chem111 extends Card {

    public Chem111() {
        cardName = "Chem 111";
        imageRef = R.drawable.chem111;
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position < 11);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        if (player.getCraft() >= 6) return true;
        return false;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,0,0,5);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        BoardPosition position = controller.getGameBoard().getPosition(2);
        controller.movePlayer(position);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
