package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.Card;

import java.util.ArrayList;

/**
 * Created by john on 10/5/15.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        cards = new ArrayList<Card>();

        playerName = name;
        initializePoints();
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public void startTurn() {
        movesLeft = 3;
    }

    @Override
    public void endTurn() {

    }
}
