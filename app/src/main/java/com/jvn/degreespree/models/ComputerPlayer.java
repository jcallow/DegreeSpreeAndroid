package com.jvn.degreespree.models;

import android.util.Log;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.Card;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by john on 10/10/15.
 */
public class ComputerPlayer extends Player {
    private static final String TAG = "ComputerPlayer";

    public ComputerPlayer(String name) {
        cards = new ArrayList<>();
        playerName = name;
        r = new Random();
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void startTurn() {
        gameController.drawCard();
        movesLeft = 3;

        while(movesLeft > 0) {
            boardPosition = getRandomPosition();
            Log.d(TAG, "Computer moved to: " + boardPosition);
            gameController.movePlayer(boardPosition);
        }

        playRandomCard();
    }

    private BoardPosition getRandomPosition() {
        int index = boardPosition.getNearbyPositions().get(r.nextInt(boardPosition.getNearbyPositions().size()));
        return gameController.getGameBoard().getPosition(index);
    }

    private void playRandomCard() {
        gameController.playCard(null);
    }

    @Override
    public void endTurn() {
        TurnInfo info = new TurnInfo(playerName, boardPosition.toString(), turnCard.toString(), turnReward);
        gameController.addTurnInfo(info);
        gameController.nextTurn();
    }

    public void chooseDiscard() {
        Card discard = cards.get(r.nextInt(cards.size()));
        discard(discard);
    }

    public void pickReward(int points, boolean learningOn, boolean craftOn, boolean integrityOn, Reward reward) {
        int remaining = points;

        while (remaining > 0) {
            int pick = r.nextInt(3);

            if (pick == 0 && learningOn) reward.add(1,0,0,0);
            if (pick == 1 && craftOn) reward.add(0,1,0,0);
            if (pick == 2 && integrityOn) reward.add(0,0,1,0);
        }
    }
}
