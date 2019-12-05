import java.util.ArrayList;

public class DeckTester
{
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        Deck deck = new Deck();
        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        //Add all symbols possible
        symbols.add("A");
        for(int i = 2; i <= 9; i++){
            symbols.add("" + i);
        }
        symbols.add("T");
        symbols.add("J");
        symbols.add("Q");
        symbols.add("K");

        //Add all values possible
        for(int i = 1; i <= 13; i++){
            values.add(i);
        }

        //Create deck
        for(int i = 0; i < 13; i++){
            Card currentCard = new Card(symbols.get(i), values.get(i));
            deck.addCard(currentCard);
            currentCard.setFaceUp(true);
        }
        
        deck.addCard(new Card("J", 11));

        deck.shuffle();
        System.out.println(deck.toString());
        
        String savedDeck = deck.save();
        System.out.println(savedDeck);
        
        Deck genDeck = new Deck(savedDeck);
        System.out.println("Generated Deck: " + genDeck.toString());
        
        System.out.print("Sorted Deck: ");
        deck.sort();
        System.out.println(deck.toString());
    }
}