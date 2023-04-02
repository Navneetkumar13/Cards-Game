package service.implementation;

import entity.Card;
import service.PlayerService;

import java.util.List;

public class PlayerServiceImplementation implements PlayerService {
    @Override
    public void addCard(Card card, List<Card> hand) {
        hand.add(card);
    }

    @Override
    public Card removeCard(int index, List<Card> hand) {
        return hand.remove(index);
    }

    @Override
    public int handSize(List<Card> hand) {
        return hand.size();
    }


    @Override
    public void displayHand(String name,List<Card> hand) {
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i).getRank() + " of " + hand.get(i).getSuit());
        }
    }

    @Override
    public boolean canPlay(Card topCard, List<Card> hand) {
        for (Card card : hand) {
            if (card.getRank().equals(topCard.getRank()) || card.getSuit().equals(topCard.getSuit())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Card playCard(int index, List<Card> hand) {
        return hand.remove(index);
    }

    @Override
    public boolean hasCards(List<Card> hand) {
        return !hand.isEmpty();
    }
}
