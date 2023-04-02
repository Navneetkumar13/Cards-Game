package entity;

import service.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand;
    private String name;

    private PlayerService playerService;

    public Player(String name) {
        hand = new ArrayList<>();
        this.name = name;
    }

    public void addCard(Card card){
        //playerService.addCard(card,this.hand);
        hand.add(card);
    }
    public Card removeCard(int index){
        return playerService.removeCard(index,hand);
    }
    public int handSize(){
        return hand.size();
    }

    public String getName(){
        return name;
    }
    public void displayHand(){
        //playerService.displayHand(name,hand);
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i).getRank() + " of " + hand.get(i).getSuit());
        }
    }

    public boolean canPlay(Card topCard){
        //return playerService.canPlay(topCard,hand);
        for (Card card : hand) {
            if (card.getRank().equals(topCard.getRank()) || card.getSuit().equals(topCard.getSuit())) {
                return true;
            }
        }
        return false;
    }
    public boolean hasCards(){
        return !hand.isEmpty();
        // return playerService.hasCards(hand);
    }

    public Card playCard(int index){
        return hand.remove(index);
        //return playerService.playCard(index,hand);
    }
}
