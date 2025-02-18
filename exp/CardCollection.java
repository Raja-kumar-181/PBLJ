import java.util.*;

class Card {
    String symbol;
    int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    @Override
    public String toString() {
        return symbol + "-" + number;
    }
}

public class CardCollection {
    static Map<String, List<Card>> cardMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void addCard() {
        System.out.print("Enter Symbol: ");
        String symbol = scanner.next();
        System.out.print("Enter Number: ");
        int number = scanner.nextInt();

        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(new Card(symbol, number));

        System.out.println("Card Added Successfully!");
    }

    public static void findCards() {
        System.out.print("Enter Symbol to Find Cards: ");
        String symbol = scanner.next();
        if (cardMap.containsKey(symbol)) {
            System.out.println("Cards: " + cardMap.get(symbol));
        } else {
            System.out.println("No Cards Found for This Symbol!");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Card\n2. Find Cards by Symbol\n3. Exit");
            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addCard();
                case 2 -> findCards();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid Choice! Try Again.");
            }
        }
    }
}
