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
import com.jvn.degreespree.models.cards.Card;

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
    private Button mDiscard;
    private Button mNextCard;
    private ListView mMovableLocationLists;
    private ArrayAdapter<BoardPosition> movableLocations;
    private ImageView mCardView;
    private Integer currentCard = null;

    private BoardPosition currentlySelected = null;

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
        initDrawCardButton(v);
        initCardDisplay(v);
        initDrawCard(v);
        initNextCard(v);
    }

    private void initDrawCard(View v) {
        mDrawCard = (Button) v.findViewById(R.id.draw_card);

        mDrawCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                controller.drawCard();
            }
        });
    }

    private void initDiscard() {

    }

    private void initNextCard(View v) {
        mNextCard = (Button) v.findViewById(R.id.next_card);

        mNextCard.setOnClickListener(new View.OnClickListener() {
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
    }

    private void initDrawCardButton(View v) {
        mPlayCard = (Button) v.findViewById(R.id.play_card);
        mPlayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do nothing for now
                controller.playCard(null);
            }
        });
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
}
