package com.jvn.degreespree.models;

/**
 * Created by john on 10/5/15.
 */
public interface Player {

    String getPlayerName();

    BoardPosition getBoardPosition();

    void setBoardPosition(BoardPosition position);

}
