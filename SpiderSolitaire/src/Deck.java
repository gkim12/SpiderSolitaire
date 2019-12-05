import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * -ACTIVITY 1/2/3 REFLECTION-
 * Name: George Kim
 * Period: 2
 * Date: 11/30/17 11:48 PM
 * 
 * Time: 30 minutes
 * 
 * So far, the lab has been pretty amusing. The Card class was easy, as envisioning a card class in the first place is quite
 * elementary. The fun part thus far has been the Deck class. After some pondering, I decided to go with ArrayList because
 * I found the immutability of arrays to be too daunting and considered how that MIGHT severely hinder me in the future. Thus,
 * I chose ArrayLists to be safe. Thinking up methods was more challenging than first envisioned. First of all, I coded shuffle
 * and toString since those were required, but the rest took a while. I broke down the online spider solitaire game into every
 * little move it did, and possible methods that may help me in the future. A constitutent, fundamental function is simply 
 * adding a new card, which I implemented. I then considered swapping two specific cards, pulling out a specific card (which
 * is what the game does when it takes out a series of new cards when you click the face down cards), and selecting a specific
 * card. More methods will probably be added in the future, but for now, I believe it to be sufficient.
 */
public class Deck
{
    /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */

    private ArrayList<Card> deck;

    Random r = new Random();

    /**
     * Default constructor: initializes an empty deck
     */
    public Deck(){
        deck = new ArrayList<>();
    }
    
    /**
     * Constructor which takes in a special String (String that stores data necesary to create a new Deck) and creates
     * a new Deck using this data.
     * 
     * @param savedDeck a String which contains data necessary to create a new Deck
     */
    public Deck(String savedDeck){
        deck = new ArrayList<>();
        
        @SuppressWarnings("resource")
		final Scanner sc = new Scanner(savedDeck);
        while(sc.hasNext()){
            String currentCard = sc.next();
            
            String symbol = currentCard.substring(0, 1);
            int value;
            if(symbol.equals("K")){
                value = 13;
            }
            else if(symbol.equals("Q")){
                value = 12;
            }
            else if(symbol.equals("J")){
                value = 11;
            }
            else if(symbol.equals("T")){
                value = 10;
            }
            else if(symbol.equals("A")){
                value = 1;
            }
            else{
                value = Integer.parseInt(symbol);
            }
            
            String face = currentCard.substring(1, 2);
            boolean faceState;
            if(face.equals("u")){
                faceState = true;
            }
            else{
                faceState = false;
            }
            
            Card genCard = new Card(symbol, value);
            genCard.setFaceUp(faceState);
            
            deck.add(genCard);
        }
    }

    /**
     * Adds Card to top of Deck
     */
    public void addCard(Card other){
        deck.add(other);
    }

    /**
     * Adds Card to specified index in Deck
     */
    public void addCard(Card other, int index){
        deck.add(index, other);
    }

    /**
     * Swaps Cards at given indexes
     */
    public void swapCards(int i1, int i2){
        Card temp;

        temp = deck.get(i1);
        deck.remove(i1);
        deck.add(i1, deck.get(i2));
        deck.remove(i2);
        deck.add(i2, temp);
    }

    /**
     * Returns Card at specified index
     * 
     * @param index the index
     * 
     * @return a Card at the given index in Deck
     */
    public Card getCard(int index){
        return deck.get(index);
    }

    /**
     * Returns number of Cards in the Deck
     * 
     * @return the number of Cards in Deck
     */
    public int getNumCards(){
        return deck.size();
    }

    /**
     * Removes Card in Deck at given index
     * 
     * @param index remove a Card at the given index in Deck
     */
    public void removeCard(int index){
        deck.remove(index);
    }

    /**
     * Randomly shuffles deck by moving all cards to a random location in same Deck but values and symbols remain unchanged.
     */
    public void shuffle(){
        int randIndex;
        Card temp;
        for(int i = 0; i < deck.size(); i++){
            randIndex = r.nextInt(deck.size());
            temp = deck.get(i);
            deck.set(i, deck.get(randIndex));
            deck.set(randIndex, temp);
        }
    }

    /**
     * Saves deck as a string. Each card in deck's symbol, value, and face state (up or down) is saved as a string.
     * 
     * @return a String containing data about the Cards in Deck
     */
    public String save(){
        String savedDeck = "";

        for(int i = 0; i < deck.size(); i++){
            savedDeck += deck.get(i).getSymbol();
            if(deck.get(i).isFaceUp()){
                savedDeck += "u";
            }
            else{
                savedDeck += "d";
            }
            
            savedDeck += " ";
        }

        return savedDeck;
    }
    
    public void sort() {
    	for (int i = 0; i < deck.size() - 1; i++) {
			int maxIndex = 0;
			Card max = deck.get(maxIndex);
			for (int inner = 0; inner <= deck.size() - 1 - i; inner++) {
				if (deck.get(inner).compareTo(max) > 0) {
					max = deck.get(inner);
					maxIndex = inner;
				}
			}
			Card endVal = deck.get(deck.size() - 1 - i);
			deck.set(deck.size() - 1 - i, max);
			deck.set(maxIndex, endVal);
		}
    }

    /**
     * Overrides the toString() method in the Object class. Represents the data in each deck as a String.
     * 
     * @return a String printing out each Card in Deck
     */
    @Override
    public String toString(){
        if(deck.size() == 0){
            return "[]";
        }
        else{
            String result = "[";

            for(int i = 0; i < deck.size(); i++){
                result += deck.get(i).toString() + " ";
            }

            result = result.substring(0, result.length() - 1);
            result += "]";

            return result;
        }
    }
}
