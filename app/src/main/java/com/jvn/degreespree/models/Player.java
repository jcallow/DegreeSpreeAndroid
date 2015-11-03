package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.Card;

import java.util.ArrayList;

/**
 * Created by john on 10/5/15.
 */
abstract public class Player {

    protected String playerName;
    protected BoardPosition boardPosition;

    protected int learning = 0;
    protected int craft = 0;
    protected int integrity = 0;
    protected int qualityPoints = 0;

    protected ArrayList<Card> cards = new ArrayList<>();
    protected Card cardInHand;
    protected GameController gameController;

    protected int movesLeft = 0;

    public String getPlayerName() {
        return playerName;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(BoardPosition position) {
        boardPosition = position;
    }

    public abstract boolean isHuman();

    public abstract void startTurn();

    public abstract void endTurn();

    public int getLearning() {
        return learning;
    }

    public void setLearning(int learning) {
        this.learning = learning;
    }

    public int getCraft() {
        return craft;
    }

    public void setCraft(int craft) {
        this.craft = craft;
    }

    public int getIntegrity() {
        return integrity;
    }

    public void setIntegrity(int integrety) {
        this.integrity = integrety;
    }

    public int getQualityPoints() {
        return qualityPoints;
    }

    public void setQualityPoints(int qualityPoints) {
        this.qualityPoints = qualityPoints;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void decrementMovesLeft() {
        movesLeft--;
    }

    public void bind(GameController controller) {
        this.gameController = controller;
    }

    public void playCardInHand() {
        cardInHand.play(this);
        gameController.discard(cardInHand);
        cards.remove(cardInHand);

        if (cards.size() > 0) {
            cardInHand = cards.get(0);
        } else {
            cardInHand = null;
        }
    }

    public void nextCard() {
        int index = cards.indexOf(cardInHand);

        index = (index + 1)%cards.size();

        cardInHand = cards.get(index);
    }

    public void addCardToHand(Card card) {
        cards.add(card);
        cardInHand = cards.get(0);
    }

    public void addToHand(ArrayList<Card> crds) {
        cards.addAll(crds);
        cardInHand = cards.get(0);
    }

    public Card getCardInHand() {
        return cardInHand;
    }

    public Card discard() {
        Card card = cardInHand;
        cards.remove(cardInHand);
        gameController.discard(cardInHand);
        if (cards.size() > 0) {
            cardInHand = cards.get(0);
        } else {
            cardInHand = null;
        }

        return card;

    }

}
