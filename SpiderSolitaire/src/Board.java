import java.util.ArrayList;
import java.io.*;
import javax.swing.JFileChooser;
import java.util.Scanner;

/**
 * -ACTIVITY 4/5 REFLECTION-
 * Name: George Kim
 * Period: 2
 * Date: 12/6/17 11:15 PM
 * 
 * Time: 5 hours
 * 
 * This portion of the lab certainly took a substantial amount of time. In order to simplify the process of filling up decks,
 * I created an arraylist of symbols and another arraylist of values, each corresponding to each other (ie index 0 in both
 * when combined would create the correct card). Using a for loop, I could fill up a deck with 13 correct cards. Then I moved
 * onto shuffling the deck, which actually was not all that difficult. I just took each card in the deck and randomly moved
 * it to another index in the same deck. At the end, I morphed the total number of decks together to form an overall deck.
 * Most of the methods were pretty trivial to code, but the two I had the most problem with was clear and makeMove. For clear,
 * I could identify runs pretty easily using the same system that had with the ArrayList of symbols, merely checking that the
 * symbols of the selected run are in order. However, it wasn't until testing my game that I realized that once I cleared my
 * complete run, the next card didn't flip over automatically. This was pretty simple to fix and only required a simple
 * line of code that flipped the next card in the stack if there even is one. makeMove took the bulk of my time, with a bunch
 * of edge cases that took a while to discover. The most notable, however, was the situation where I cleared a complete run
 * and the deck was left with nothing. Essentially, I couldn't draw cards until this empty deck was filled, but when I tried
 * to fill the empty deck, I received an index out of bounds error because in my code, I was checking the top card on the 
 * destination deck and making sure it's value was precisely 1 over the selected run. An if-statement took care of the issue,
 * but nevertheless that was a huge flaw in the game. Apart from these two major flaws, I encountered not much other significant
 * trouble.
 * 
 * -ACTIVITY 7 REFLECTION-
 * Name: George Kim
 * Period: 2
 * Date: 12/14 5:13 PM
 * 
 * Time: 1 hour
 * 
 * Activity 7 was a wonderful creative portion and probably my favorite. Finding a way to save and load data required a lot
 * of creativity on my part. I had to make sure that I was organized with what data was being stored. Even more thinking was
 * required when I remembered that saving the data itself wasn't the only task I had, I had to make sure I could actually
 * read it later on in order to load it. In this process, rereading the Scanner API was highly beneficial. I was a standstill
 * when I couldn't figure out how to save whole stacks in a way such that when loading, I could distinguish one stack from 
 * another. In particular, rereading the nextLine() method helped me realize that when calling nextLine(), the scanner actually
 * returns the contents of that entire line it skipped, which was completely different from my original belief that nextLine()
 * simply moved the Scanner to the next line and that's all it did. Once I figured this out, I was able to successfully load
 * and save my games. Using JFileChooser wasn't that difficult to learn due to it being quite similar to File I/O. Overall, 
 * however, it was very satisfying to see my entire game work fluidly. I played the game a couple of times to see if I ever
 * ran into any sort of weird glitch or error, and from what I can tell I couldn't find a flaw as of yet. 
 */
public class Board
{   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    int numStacks;
    ArrayList<Deck> stacks;
    ArrayList<String> symbols;
    ArrayList<Integer> values;
    Deck overallDeck;
    Deck stackDeck;
    Deck drawDeck;
	private Scanner sc;

    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  The number of Cards in a Deck
     *  depends on the number of suits. Here are examples:
     *  
     *  # suits     # numDecks      #cards in overall Deck
     *      1            1          13 (all same suit)
     *      1            2          26 (all same suit)
     *      2            1          26 (one of each suit)
     *      2            2          52 (two of each suit)
     *      4            2          104 (two of each suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.  If you'd like to specify
     *  more than one suit, feel free to add to the parameter list.
     */    
    public Board(int numStacks, int numDecks) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        this.numStacks = numStacks;
        overallDeck = new Deck();
        stackDeck = new Deck();
        drawDeck = new Deck();

        symbols = new ArrayList<>();
        values = new ArrayList<>();

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
        for(int i = 0; i < numStacks; i++){
            for(int j = 0; j < 13; j++){
                Card currentCard = new Card(symbols.get(j), values.get(j));
                overallDeck.addCard(currentCard);
            }
        }
        overallDeck.shuffle();

        //Distribute half of overall deck to stacks and draw pile
        for(int i = 0; i < overallDeck.getNumCards(); i++){
            if(i % 2 == 0){
                stackDeck.addCard(overallDeck.getCard(i));
            }
            else{
                drawDeck.addCard(overallDeck.getCard(i));
            }
        }

        //Split cards among stacks
        stacks = new ArrayList<>();
        int numCardsPerStack = stackDeck.getNumCards() / numStacks;

        for(int i = 0; i < numStacks; i++){
            Deck stack = new Deck();
            for(int j = 0; j < numCardsPerStack; j++){
                Card card = stackDeck.getCard(0);
                if(j == numCardsPerStack - 1){
                    card.setFaceUp(true);
                }
                stack.addCard(card);
                stackDeck.removeCard(0);
            }
            stacks.add(stack);
        }
    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.  Change the parameter list to match
     *  your implementation of Card if you need to.
     */
    public void makeMove(String symbol, int src, int dest) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        Deck sourceStack = stacks.get(src);
        Deck destStack = stacks.get(dest);
        int indexSymbol = -1; //index of symbol (if present) in sourcestack

        for(int i = sourceStack.getNumCards() - 1; i >= 0; i--){
            if(sourceStack.getCard(i).getSymbol().equals(symbol)){
                indexSymbol = i;
                break;
            }
        }

        if(indexSymbol != -1){
            //Check to see if run is legal
            boolean sourceRun = true; //is run legal
            int symbolIndex = -1; //index of symbol in symbol arraylist

            for(int i = 0; i < symbols.size(); i++){
                if(symbols.get(i).equals(symbol)){
                    symbolIndex = i;
                    break;
                }
            }

            for(int i = 0; i < sourceStack.getNumCards() - indexSymbol; i++){
                if(symbols.get(symbolIndex - i).equals(sourceStack.getCard(indexSymbol + i).getSymbol()) == false){
                    sourceRun = false;
                    break;
                }
            }

            //Check to see if all cards in run are face up
            boolean allFaceUp = true;

            for(int i = indexSymbol; i < sourceStack.getNumCards(); i++){
                if(sourceStack.getCard(i).isFaceUp() == false){
                    allFaceUp = false;
                    break;
                }
            }

            //Check to see if destination will produce a longer run
            boolean newRun = true;

            if(destStack.getNumCards() > 0 && destStack.getCard(destStack.getNumCards() - 1).getSymbol().equals(symbols.get(symbolIndex + 1)) == false){
                newRun = false;
            }

            //If run is legal and all cards face down, make move
            if(sourceRun && allFaceUp && newRun){
                for(int i = indexSymbol; i < sourceStack.getNumCards(); i++){
                    destStack.addCard(sourceStack.getCard(i));
                }

                while(indexSymbol < sourceStack.getNumCards()){
                    sourceStack.removeCard(indexSymbol);
                }

                if(sourceStack.getNumCards() != 0){
                    sourceStack.getCard(sourceStack.getNumCards() - 1).setFaceUp(true);
                }
            }else if(allFaceUp == false){
                System.out.println("Error: Invalid Move, card facedown.");
            }
            else if(sourceRun == false){
                System.out.println("Error: Invalid Move, not a run.");
            }
            else if(newRun == false){
                System.out.println("Error: Invalid Move, destination card must be exactly one higher in value.");
            }
            else{
                System.out.println("Error: Invalid Move, not a run and card facedown.");
            }
        }
        else{
            System.out.println("Error: card does not exist.");
        }
    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */

        boolean noEmptyStacks = true;

        for(int i = 0; i < numStacks; i++){
            if(stacks.get(i).getNumCards() == 0){
                noEmptyStacks = false;
            }
        }

        if(noEmptyStacks){
            for(int i = 0; i < numStacks; i++){
                if(drawDeck.getNumCards() != 0){
                    Card c = drawDeck.getCard(0);
                    c.setFaceUp(true);
                    stacks.get(i).addCard(c);
                    drawDeck.removeCard(0);
                }
            }
        }
    } 

    /**
     *  Returns true if all stacks and the draw pile are all empty
     *  
     *  @return whether all stacks and the draw pile are empty
     */ 
    public boolean isEmpty() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean result = true;

        for(int i = 0; i < numStacks; i++){
            if(stacks.get(i).getNumCards() != 0){
                result = false;
            }
        }

        if(drawDeck.getNumCards() != 0){
            result = false;
        }

        return result;
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        Deck stack = stacks.get(sourceStack);
        boolean run = true;

        for(int i = 0; i < 13; i++){
            if(symbols.get(i) != stack.getCard(stack.getNumCards() - (i + 1)).getSymbol()){
                run = false;
                break;
            }
        }

        if(run == true){
            for(int i = 0; i < 13; i++){
                stack.removeCard(stack.getNumCards() - (13 - i));
            }

            if(stack.getNumCards() != 0){
                stack.getCard(stack.getNumCards() - 1).setFaceUp(true);
            }
        }else{
            System.out.println("Error: Invalid Move");
        }
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */

        System.out.println("Stacks:");
        for(int i = 0; i < numStacks; i++){
            System.out.println((i + 1) + ": " + stacks.get(i).toString());
        }

        System.out.println();
        System.out.println("Draw Pile:");
        System.out.println(drawDeck.toString());
    }

    /**
     * Saves the data necessary to load the game the later on. Number of stacks, cards in each stack, and cards in 
     * draw pile are saved to text file.
     */
    public void saveGame(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        try{
            FileWriter out = new FileWriter(file);
            out.write("" + numStacks + "\r\n");
            for(int i = 0; i < numStacks; i++){
                out.write(stacks.get(i).save());
                out.write("\r\n");
            }
            out.write(drawDeck.save());
            out.close();
        }catch(IOException err){
            System.out.println("File not found.");
        }catch(NullPointerException err){
            System.out.println("Choose a file.");
        }
    }

    /**
     * Loads a saved game stored in a text file, which contains data such as number of stacks, cards in each stack, and
     * cards in draw pile.
     */
    public void loadGame(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        try{
            sc = new Scanner(file);

            stacks = new ArrayList<>();
            numStacks = Integer.parseInt(sc.next());
            sc.nextLine();
            for(int i = 0; i < numStacks; i++){
                Deck currentStack = new Deck(sc.nextLine());
                stacks.add(currentStack);
            }

            drawDeck = new Deck(sc.nextLine());
        }catch(FileNotFoundException err){
            System.out.println("File not found.");
        }catch(NullPointerException err){
            System.out.println("Choose a file.");
        }
    }
}