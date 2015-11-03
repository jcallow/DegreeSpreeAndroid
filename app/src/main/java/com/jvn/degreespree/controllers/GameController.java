package com.jvn.degreespree.controllers;

import android.content.Context;
import android.util.Log;

import com.jvn.degreespree.MainView;
import com.jvn.degreespree.R;
import com.jvn.degreespree.Utils.ScreenUtils;
import com.jvn.degreespree.fragments.view.BoardViewFragment;
import com.jvn.degreespree.fragments.view.GameplayViewFragment;
import com.jvn.degreespree.fragments.view.StartFragment;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Deck;
import com.jvn.degreespree.models.cards.CECS105;
import com.jvn.degreespree.models.cards.Card;
import com.jvn.degreespree.models.ComputerPlayer;
import com.jvn.degreespree.models.GameBoard;
import com.jvn.degreespree.models.GameSettings;
import com.jvn.degreespree.models.HumanPlayer;
import com.jvn.degreespree.models.Player;

import java.util.ArrayList;

/**
 * Created by john on 10/5/15.
 */
public class GameController {
    private static final String TAG = "GameController";

    private MainView mainView;
    private Context applicationContext;
    private StartFragment startView;
    private BoardViewFragment boardView;
    private GameplayViewFragment menuView;

    private GameSettings gameSettings;
    private ArrayList<Player> players;
    private Player currentPlayersTurn;
    private int currentPlayerIndex = 0;
    private GameBoard gameBoard;
    private Deck deck;

    public GameController(MainView mainView) {
        this.mainView = mainView;
        this.applicationContext = mainView.getApplicationContext();

        ScreenUtils.setConversionRate(mainView.getDensity());

        startView = new StartFragment();
        startView.bind(this);

        boardView = new BoardViewFragment();
        boardView.bind(this);
        boardView.init();

        menuView = new GameplayViewFragment();
        menuView.bind(this);
        menuView.init();

        init();
        startGame();

    }

    private void init() {
        gameBoard = new GameBoard();
        deck = new Deck();
    }

    public void startGame() {

        // Switch to Start view eventually

        gameSettings = new GameSettings();
        players = gameSettings.getPlayers();

        for (Player player : players) {
            player.setBoardPosition(gameBoard.getPosition(17));
            player.bind(this);
            ArrayList<Card> crds = deck.take(5);
            player.addToHand(crds);
            if (player.isHuman()) {
                menuView.updateCardDisplay(player.getCardInHand());
            }
        }

        viewBoard();

        addPlayers(players);


        startTurn(players.get(currentPlayerIndex));

    }

    private void addPlayers(ArrayList<Player> players) {
        boardView.addPlayers(players);
    }

    private void startTurn(Player player) {
        currentPlayersTurn = player;
        currentPlayersTurn.addCardToHand(deck.drawCard());
        if (player.isHuman()) {
            player.startTurn();
            ArrayList<BoardPosition> positions = gameBoard.getPositions(player.getBoardPosition().getNearbyPositions());
            menuView.updateMovableLocation(positions);
        } else {
            // Eventually disable components so player cant screw the game up
            player.startTurn();
        }

    }

    private void endTurn(Player player) {
        player.endTurn();
    }

    private void nextTurn() {
        if (!gameHasEnded()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % 3;
            Player nextPlayer = players.get(currentPlayerIndex);

            startTurn(nextPlayer);
        } else {
            endGame();
        }

    }

    public void viewBoard() {
        mainView.setView(boardView);
    }

    public void viewGameMenu() {
        mainView.setView(menuView);
    }

    public void movePlayer(BoardPosition position) {
        if (currentPlayersTurn.getMovesLeft() > 0) {
            currentPlayersTurn.setBoardPosition(position);
            boardView.movePlayer(currentPlayersTurn);
            currentPlayersTurn.decrementMovesLeft();

            if (currentPlayersTurn.isHuman()) {
                ArrayList<BoardPosition> movableLocations = gameBoard.getPositions(position.getNearbyPositions());
                menuView.updateMovableLocation(movableLocations);
            }

        }
    }

    public void playCard(Card card) {
        currentPlayersTurn.playCardInHand();
        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
        }
        endTurn(currentPlayersTurn);
        nextTurn();
    }

    public void drawCard() {
        Card card = deck.drawCard();
        currentPlayersTurn.addCardToHand(card);

        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
        }
    }

    public void nextCard() {
        currentPlayersTurn.nextCard();

        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
        }
    }

    public void discardFromHand() {
        currentPlayersTurn.discard();

        if (currentPlayersTurn.isHuman()) {
            menuView.updateCardDisplay(currentPlayersTurn.getCardInHand());
        }
    }

    public void discard(Card card) {
        deck.discard(card);
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    private boolean gameHasEnded() {
        // check for win conditions
        return false;
    }

    private void endGame() {

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

}
