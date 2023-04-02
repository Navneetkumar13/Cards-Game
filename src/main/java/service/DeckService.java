package service;

import java.util.List;
import entity.Card;

public interface DeckService {

    public void shuffleCards(List<Card> cards);
    public Card deal(List<Card> cards);
    public void reset(List<Card> cards);
    public int size(List<Card> cards);
}
