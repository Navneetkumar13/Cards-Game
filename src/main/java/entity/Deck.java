package entity;

import entity.Card;
import service.DeckService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards;

    private DeckService deckService;

    public Deck(){
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for(String suit:suits){
            for(String rank: ranks){
                cards.add(new Card(suit,rank));
            }
        }
        shuffle();
      //  deckService.shuffle(cards);
    }

    public void shuffle(){
        Collections.shuffle(cards);
       // deckService.shuffleCards(this.cards);
    }

    public Card deal(){
        //return deckService.deal(this.cards);
        return cards.remove(0);
    }

    public void reset(){
        deckService.reset(cards);
    }

    public int getSize(){
        return cards.size();
    }
}
