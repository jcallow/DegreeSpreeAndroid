package com.jvn.degreespree.models;

import com.jvn.degreespree.controllers.GameController;
import com.jvn.degreespree.models.cards.BuddyUp;
import com.jvn.degreespree.models.cards.CECS100;
import com.jvn.degreespree.models.cards.CECS105;
import com.jvn.degreespree.models.cards.CECS174;
import com.jvn.degreespree.models.cards.Card;
import com.jvn.degreespree.models.cards.ChooseMajor;
import com.jvn.degreespree.models.cards.EnjoyPeace;
import com.jvn.degreespree.models.cards.ExercisingMB;
import com.jvn.degreespree.models.cards.FindLab;
import com.jvn.degreespree.models.cards.GoodbyeProfessor;
import com.jvn.degreespree.models.cards.Kin253;
import com.jvn.degreespree.models.cards.LateForClass;
import com.jvn.degreespree.models.cards.LearnNetBeans;
import com.jvn.degreespree.models.cards.LunchBratWurst;
import com.jvn.degreespree.models.cards.Math122;
import com.jvn.degreespree.models.cards.Math123;
import com.jvn.degreespree.models.cards.ParkingViolation;
import com.jvn.degreespree.models.cards.PassSoccer;
import com.jvn.degreespree.models.cards.Phys151;
import com.jvn.degreespree.models.cards.ResearchCompilers;
import com.jvn.degreespree.models.cards.TheBigGame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/**
 * Created by john on 11/1/15.
 */
public class Deck {

    private ArrayList<Card> cards;
    private ArrayList<Card> discards;
    private GameController controller;

    public Deck(GameController controller) {
        cards = new ArrayList<>();
        discards = new ArrayList<>();
        this.controller = controller;
        initDeck();
    }

    public Card drawCard() {
        if (cards.size() == 0) {
            Collections.shuffle(discards);
            cards.addAll(discards);
            discards.clear();
        }

        Card card = cards.get(0);
        cards.remove(0);

        return card;
    }

    public ArrayList<Card> take(int n) {
        ArrayList<Card> crds = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            crds.add(drawCard());
        }
        return crds;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void discard(Card card) {
        discards.add(card);
    }

    private void initDeck() {

        cards.add(new BuddyUp());
        cards.add(new CECS100());
        cards.add(new CECS105());
        cards.add(new CECS174());
        cards.add(new ChooseMajor());
        cards.add(new EnjoyPeace());
        cards.add(new ExercisingMB());
        cards.add(new FindLab());
        cards.add(new GoodbyeProfessor());
        cards.add(new Kin253());
        cards.add(new LateForClass());
        cards.add(new LearnNetBeans());
        cards.add(new LunchBratWurst());
        cards.add(new Math122());
        cards.add(new Math123());
        cards.add(new ParkingViolation());
        cards.add(new PassSoccer());
        cards.add(new Phys151());
        cards.add(new ResearchCompilers());
        cards.add(new TheBigGame());


        for(int i = 0; i < 20; i++) {
            cards.add(new EnjoyPeace());
        }

        for (Card card : cards) {
            card.bind(controller);
        }
        shuffle();

    }

    public int inPlay() {
        return cards.size();
    }

    public int outPlay() {
        return discards.size();
    }



}
