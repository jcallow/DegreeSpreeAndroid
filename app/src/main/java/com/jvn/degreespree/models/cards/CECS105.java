package com.jvn.degreespree.models.cards;

import com.jvn.degreespree.R;
import com.jvn.degreespree.models.Player;

/**
 * Created by john on 11/1/15.
 */
public class CECS105 extends Card {

    public CECS105() {
        imageRef = R.drawable.cecs105;
    }

    @Override
    public void play(Player player) {
        int positionIndex = player.getBoardPosition().getIndex();
        if (positionIndex == 14 || positionIndex == 17) {
            player.setIntegrity(player.getIntegrity() + 1);
        } else {

        }
    }
}
