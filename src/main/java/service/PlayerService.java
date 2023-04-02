package service;

import entity.Card;

import java.util.List;

public interface PlayerService {

    public void addCard(Card card,List<Card> hand);

    public Card removeCard(int index,List<Card> hand);
    public int handSize(List<Card> hand);
    public void displayHand(String name,List<Card> hand);
    public boolean canPlay(Card topCard,List<Card> hand);
    public Card playCard(int index,List<Card> hand);
    public boolean hasCards(List<Card> hand);
}
