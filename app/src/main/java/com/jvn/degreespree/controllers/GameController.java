package com.jvn.degreespree.controllers;

import android.content.Context;

import com.jvn.degreespree.MainView;
import com.jvn.degreespree.Utils.ScreenUtils;
import com.jvn.degreespree.fragments.view.BoardViewFragment;
import com.jvn.degreespree.fragments.view.GameplayViewFragment;
import com.jvn.degreespree.fragments.view.StartFragment;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.GameBoard;
import com.jvn.degreespree.models.HumanPlayer;
import com.jvn.degreespree.models.Player;

import java.util.ArrayList;

/**
 * Created by john on 10/5/15.
 */
public class GameController {

    private MainView mainView;
    private Context applicationContext;
    private StartFragment startView;
    private BoardViewFragment boardView;
    private GameplayViewFragment menuView;


    private ArrayList<Player> players;
    private Player currentPlayersTurn;
    private GameBoard gameBoard;

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
    }

    public void startGame() {
        players = new ArrayList<>(3);
        Player player = new HumanPlayer("John");
        player.setBoardPosition(gameBoard.getPosition(17));
        currentPlayersTurn = player;
        players.add(player);
        viewBoard();

        addPlayers(players);

        startTurn(players.get(0));

    }

    private void addPlayers(ArrayList<Player> players) {
        boardView.addPlayers(players);
    }

    private void startTurn(Player player) {
        ArrayList<BoardPosition> positions = gameBoard.getPositions(player.getBoardPosition().getNearbyPositions());
        menuView.updateMovableLocation(positions);
    }

    public void viewBoard() {
        mainView.setView(boardView);
    }

    public void viewGameMenu() {
        mainView.setView(menuView);
    }

    public void movePlayer(BoardPosition position) {
        currentPlayersTurn.setBoardPosition(position);
        boardView.movePlayer(currentPlayersTurn);
        ArrayList<BoardPosition> movableLocations = gameBoard.getPositions(position.getNearbyPositions());
        menuView.updateMovableLocation(movableLocations);
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}
