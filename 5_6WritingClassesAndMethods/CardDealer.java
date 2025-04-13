import java.util.Random;

public class CardDealer {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("Dealing 5 random cards:");

        for (int i = 0; i < 5; i++) {
            int suitIndex = random.nextInt(Card.SUITS.length);
            int valueIndex = random.nextInt(Card.VALUES.length);

            Card card = new Card(
                Card.SUITS[suitIndex],
                Card.VALUES[valueIndex]
            );
            System.out.println(card);
        }
    }
}
