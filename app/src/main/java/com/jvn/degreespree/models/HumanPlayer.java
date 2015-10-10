package com.jvn.degreespree.models;

/**
 * Created by john on 10/5/15.
 */
public class HumanPlayer implements Player {

    private String playerName;
    private BoardPosition boardPosition;

    public HumanPlayer(String name) {
        playerName = name;
    }

    public void setBoardPosition(BoardPosition position) {
        boardPosition = position;
    }

    @Override
    public String getPlayerName(){
        return playerName;
    }

    @Override
    public BoardPosition getBoardPosition() {
        return boardPosition;
    }
}
