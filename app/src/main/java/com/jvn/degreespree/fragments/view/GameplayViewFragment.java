package com.jvn.degreespree.fragments.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jvn.degreespree.R;
import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.BoardPosition;

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
    private ListView mMovableLocationLists;
    private ArrayAdapter<BoardPosition> movableLocations;

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

    public void updateMovableLocation(ArrayList<BoardPosition> positions) {
        movableLocations.clear();
        movableLocations.addAll(positions);
        movableLocations.notifyDataSetChanged();
    }
}
