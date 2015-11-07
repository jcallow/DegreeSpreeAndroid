package com.jvn.degreespree.fragments.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.jvn.degreespree.R;
import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.BoardPosition;
import com.jvn.degreespree.models.Deck;
import com.jvn.degreespree.models.Player;
import com.jvn.degreespree.models.cards.Card;
import com.jvn.degreespree.widgets.StatsRow;

import java.util.ArrayList;

/**
 * Created by john on 9/22/15.
 */
public class GameplayViewFragment extends Fragment {

    private String TAG = "GameplayViewFragment";
    private GameController controller;

    private Button mMove;
    private Button mShowBoard;
    private Button mPlayCard;
    private Button mDrawCard;
    private ListView mMovableLocationLists;
    private ArrayAdapter<BoardPosition> movableLocations;
    private ImageView mCardView;
    private Integer currentCard = null;

    private BoardPosition currentlySelected = null;

    private boolean moveEnabled = false;
    private boolean drawEnabled = false;
    private boolean playEnabled = false;

    private StatsRow mPlayer1Score;
    private StatsRow mPlayer2Score;
    private StatsRow mPlayer3Score;

    public void bind(GameController controller) {
        this.controller = controller;
    }

    public void init() {
       initMoveList();
    }

    private void initMoveList() {

        movableLocations = new ArrayAdapter<BoardPosition>(controller.getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<BoardPosition>());

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_menu_fragment, container, false);
        init(v);
        mMovableLocationLists.setBackground(getResources().getDrawable(R.drawable.text_box_border));
        return v;
    }

    private void init(View v) {
        initShowBoardButton(v);
        initMoveButton(v);
        initMoveList(v);
        initPlayCard(v);
        initCardDisplay(v);
        initDrawCard(v);
        initNextCard(v);
        initScoreBoard(v);
    }

    private void initDrawCard(View v) {
        mDrawCard = (Button) v.findViewById(R.id.draw_card);

        mDrawCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                controller.drawCard();
            }
        });

        mDrawCard.setEnabled(drawEnabled);

    }

    private void initNextCard(View v) {

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.nextCard();
            }
        });
    }

    private void initShowBoardButton(View v) {
        mShowBoard = (Button) v.findViewById(R.id.show_board);

        mShowBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.viewBoard();
            }
        });
    }

    private void initMoveButton(View v) {
        mMove = (Button) v.findViewById(R.id.move);
        mMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentlySelected != null) {
                    controller.movePlayer(currentlySelected);
                }
            }
        });

        mMove.setEnabled(moveEnabled);
    }

    private void initPlayCard(View v) {
        mPlayCard = (Button) v.findViewById(R.id.play_card);
        mPlayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do nothing for now
                controller.playCard(null);
            }
        });
        mPlayCard.setEnabled(playEnabled);
    }

    private void initMoveList(View v) {
        mMovableLocationLists = (ListView) v.findViewById(R.id.movable_locations);
        mMovableLocationLists.setAdapter(movableLocations);

        mMovableLocationLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentlySelected = (BoardPosition) adapterView.getItemAtPosition(i);
                Log.d(TAG, currentlySelected.getPositionName());
            }
        });
    }

    private void initCardDisplay(View v) {
        mCardView = (ImageView) v.findViewById(R.id.card_view);
        if (currentCard != null) {
            mCardView.setImageResource(currentCard);
        }
    }

    private void initScoreBoard(View v) {
        mPlayer1Score = (StatsRow) v.findViewById(R.id.player1);
        mPlayer2Score = (StatsRow) v.findViewById(R.id.player2);
        mPlayer3Score = (StatsRow) v.findViewById(R.id.player3);

        controller.updateScores();
    }

    public void updateMovableLocation(ArrayList<BoardPosition> positions) {
        movableLocations.clear();
        movableLocations.addAll(positions);
        movableLocations.notifyDataSetChanged();
    }

    public void updateCardDisplay(Card card) {
        currentCard = card.getImageRef();
        // safety check.  Card could be updated while out of context.  Have to do this because I goofed
        // the way I built the view.
        if (mCardView != null) {
            mCardView.setImageResource(card.getImageRef());
        }
    }

    public void disableMove() {
        moveEnabled = false;
        if (mMove != null) mMove.setEnabled(moveEnabled);

    }

    public void enableMove() {
        moveEnabled = true;
        if (mMove != null) mMove.setEnabled(moveEnabled);
    }

    public void enableDrawCard() {
        drawEnabled = true;
        if (mDrawCard != null) mDrawCard.setEnabled(drawEnabled);
    }

    public void disableDrawCard() {
        drawEnabled = false;
        if (mDrawCard != null) mDrawCard.setEnabled(drawEnabled);
    }

    public void enablePlayCard() {
        playEnabled = true;
        if (mPlayCard != null) mPlayCard.setEnabled(playEnabled);
    }

    public void disablePlayCard() {
        playEnabled =false;
        if (mPlayCard != null) mPlayCard.setEnabled(playEnabled);
    }

    public void disableUi() {
        disableDrawCard();
        disableMove();
        disablePlayCard();
    }

    public void updateScoreBoard(ArrayList<Player> players, Deck deck) {
        if (mPlayer1Score != null) {
            mPlayer1Score.update(players.get(0));
        }
        if (mPlayer2Score != null) {
            mPlayer2Score.update(players.get(1));
        }
        if (mPlayer3Score != null) {
            mPlayer3Score.update(players.get(2));
        }
    }

    public void addTurnInfo() {

    }
}
