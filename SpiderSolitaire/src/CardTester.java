public class CardTester
{
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        Card c1 = new Card("J", 11);
        c1.setFaceUp(true);
        Card c2 = new Card("J", 11);
        c2.setFaceUp(true);
        
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(c1.equals(c2) + " " + c1.compareTo(c2));
    }
}
