/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return symbol;
    }

    /**
     * Getter method to access this <code>Card</code>'s value.
     * 
     * @return this <code>Card</code>'s value.
     */
    public int getValue() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return value;
    }
    
    /**
     * Returns whether or not this <code>Card</code> is face up
     * 
     * @return whether this Card is face up.
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Sets the condition of this <code>Card</code> to face up or down
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     * 
     * @param other the Card this Card is being compared to.
     *  
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        return (getValue() == other.getValue() && getSymbol().equals(other.getSymbol()));
    }
    
    /**
     * Returns the difference in value between this <code>Card</code> and
     * another
     * 
     * @param other the Card this Card is being compared to.
     * 
     *  @return difference in value between this card and other.
     */
    @Override
    public int compareTo(Card other){
        return getValue() - other.getValue();
    }
    
    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        if(!isFaceUp()){
            return "X";
        }
        else{
            return getSymbol() + getValue();
        }
    }
}
