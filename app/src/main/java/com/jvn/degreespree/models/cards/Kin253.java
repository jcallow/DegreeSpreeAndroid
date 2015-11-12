package com.jvn.degreespree.models.cards;

/**
 * Created by john on 11/1/15.
 */
import android.util.Log;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.Reward;

/**
 * Created by john on 11/1/15.
 */
public class Kin253 extends Card {

    public Kin253() {
        cardName = "KIN 253";
        imageRef = R.drawable.kin253;
    }

    private void fail(Player player) {
        BoardPosition position = controller.getGameBoard().getPosition(13);
        controller.movePlayer(position);
        player.endTurn();
    }

    @Override
    protected boolean correctPosition(Player player) {
        int position = player.getBoardPosition().getIndex();
        return (position == 0);
    }

    @Override
    protected boolean meetsRequirements(Player player) {
        if (player.getIntegrity() >= 4) return true;
        else return false;
    }

    @Override
    protected void success(Reward reward) {
        reward.add(0,2,0,0);
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }

    @Override
    protected void fail(Reward reward) {
        playedBy.rewardPlayer(reward);
        playedBy.endTurn();
    }
}
