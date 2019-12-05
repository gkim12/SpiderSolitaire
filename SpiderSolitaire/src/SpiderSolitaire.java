import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * -ACTIVITY 6 REFLECTION-
 * Name: George Kim
 * Period: 2
 * Date: 12/5 11:52 PM
 * 
 * Time: 10 minutes
 * 
 * Compared to Activity 4 and 5, this activity was easier by a light year. Trying to break my code entering arbitrary, nonsensical
 * phrases was amusing, but try-catch was already done extensively in the past so there wasn't anything new to learn. In terms
 * of trying to break my code, I simply considered the case already presented in the lab document, and the other scenario of
 * trying to call move or clear to a stack that doesn't exist. I guess one crucial detail I initially ignored was that when
 * I entered my gibberish into the terminal input, that gibberish would be read by input.next(), and thus in the catch block
 * I called input.nextLine() to start fresh and delete any of that nonsense that was previously inputted into the scanner.
 */
public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in the game.  
     *  The number of cards in a deck depends on the
     *  type of Card used.  For example, a 1-suit deck
     *  of standard playing cards consists of only 13 cards
     *  whereas a 4-suit deck consists of 52 cards.
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();

            try{
                if (command.equals("move")) {
                    /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                    String symbol = input.next();
                    int sourceStack = input.nextInt();
                    int destinationStack = input.nextInt();
                    board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
                }
                else if (command.equals("draw")) {
                    board.drawCards();
                }
                else if (command.equals("clear")) {
                    /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                    int sourceStack = input.nextInt();
                    board.clear(sourceStack - 1);
                }
                else if (command.equals("restart")) {
                    board = new Board(NUM_STACKS, NUM_DECKS);
                }
                else if(command.equals("save")){
                    board.saveGame();
                }
                else if(command.equals("load")){
                    board.loadGame();
                }
                else if (command.equals("quit")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid command.");
                }
            }
            catch(InputMismatchException err){
                System.out.println("Error: Invalid information entered.");
                input.nextLine();
            }
            catch(IndexOutOfBoundsException err){
                System.out.println("Error: Select an acceptable number.");
                input.nextLine();
            }
            board.printBoard();

            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}