import entity.Card;
import entity.Deck;
import entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Deck deck;
    private List<Player> players;
    private List<Card> discardPile;
    private int currentPlayerIndex;

    //+1 for clockwise -1 for anticlockwise
    private int direction;

    public Game(List<String> playerNames) {
        deck = new Deck();
        players = new ArrayList<>();
        for(String name:playerNames){
            players.add(new Player(name));
        }
        discardPile = new ArrayList<>();
        currentPlayerIndex = 0;
        direction = 1;

    }

    public void start(){
        dealHands();
        discardPile.add(deck.deal());
        boolean gameEnded = false;
        while(!gameEnded){
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("\n" + currentPlayer.getName() + "'s turn");
            System.out.println("Top card: " + discardPile.get(discardPile.size() - 1).getRank() + " of " + discardPile.get(discardPile.size() - 1).getSuit());

            currentPlayer.displayHand();

            if (currentPlayer.canPlay(discardPile.get(discardPile.size() - 1))) {
                int cardIndex = getCardIndexFromPlayer(currentPlayer);
                Card card = currentPlayer.playCard(cardIndex);
                discardPile.add(card);

                System.out.println(currentPlayer.getName() + " played " + card.getRank() + " of " + card.getSuit());

                if (card.getRank().equals("Ace")) {
                    System.out.println("Next player will be skipped.");
                    currentPlayerIndex = getNextPlayerIndex(2);
                }
                else if (card.getRank().equals("King")) {
                    System.out.println("Game direction reversed.");
                    direction *= -1;
                    currentPlayerIndex = getNextPlayerIndex(2);
                }
                else if (card.getRank().equals("Queen")) {
                    System.out.println("Next player draws two cards.");
                    Player nextPlayer = getNextPlayer();
                    nextPlayer.addCard(deck.deal());
                    nextPlayer.addCard(deck.deal());
                    currentPlayerIndex = getNextPlayerIndex(1);
                }
                else if (card.getRank().equals("Jack")) {
                    System.out.println("Next player draws four cards.");
                    Player nextPlayer = getNextPlayer();
                    nextPlayer.addCard(deck.deal());
                    nextPlayer.addCard(deck.deal());
                    nextPlayer.addCard(deck.deal());
                    nextPlayer.addCard(deck.deal());
                    currentPlayerIndex = getNextPlayerIndex(1);
                }
                else {
                    currentPlayerIndex = getNextPlayerIndex(1);
                }
            }
            else {
                if (deck.getSize() == 0) {
                    System.out.println("Draw! No winner.");
                    gameEnded = true;
                } else {
                    System.out.println(currentPlayer.getName() + " can't play. Drawing a card...");
                    Card drawnCard = deck.deal();
                    currentPlayer.addCard(drawnCard);
                    System.out.println(currentPlayer.getName() + " drew " + drawnCard.getRank() + " of " + drawnCard.getSuit());
                    currentPlayerIndex = getNextPlayerIndex(1);
                }
            }
            if (!currentPlayer.hasCards()) {
                System.out.println(currentPlayer.getName() + " has no cards left. Game over!");
                gameEnded = true;
            }
        }
        }

    private void dealHands() {
        for (int i = 0; i < 5; i++) {
            for (Player player : players) {
                player.addCard(deck.deal());
            }
        }
    }

    private int getNextPlayerIndex(int offset) {
        int index = (currentPlayerIndex + (offset * direction)) % players.size();
        if (index < 0) {
            index += players.size();
        }
        return index;
    }

    private Player getNextPlayer() {
        int nextPlayerIndex = getNextPlayerIndex(1);
        return players.get(nextPlayerIndex);
    }

    private int getCardIndexFromPlayer(Player player) {
        Scanner scanner = new Scanner(System.in);
        int index = -1;
        while (index < 0 || index >= player.handSize()) {
            System.out.print("Enter the index of the card to play: ");
            try {
                index = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return index;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        List<String> playerNames = new ArrayList<>();
        System.out.println("Enter the number of Players: ");
        int size = sc.nextInt();
        for(int j=1 ; j<=size ; j++){
            System.out.println("Enter name "+j);
            playerNames.add(sc.next());
        }
        Game game = new Game(playerNames);

        game.start();
    }
}
