package service.implementation;

import service.DeckService;

import java.util.Collections;
import java.util.List;
import entity.Card;

public class DeckServiceImplementation implements DeckService {

    @Override
    public void shuffleCards(List<Card> cards) {
        System.out.println(cards);
        Collections.shuffle(cards);
        System.out.println("After shuffle: "+cards);
    }

    @Override
    public Card deal(List<Card> cards) {
        return cards.remove(0);
    }

    @Override
    public void reset(List<Card> cards) {
        cards.clear();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffleCards(cards);

    }

    @Override
    public int size(List<Card> cards) {
        return cards.size();
    }
}
