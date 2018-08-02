import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Cluedo {
    private Player move;
    private Board board;
    private List<Card> solution;
    private List<Player> players;

    public Cluedo() {
        solution = new ArrayList<>();
        players = new ArrayList<>();
        setup();
    }

    private void setup() {
        board = new Board();
        playerSetup();
        cardSetup();
        weaponSetup();
        selectFirstTurn();
    }

    private void selectFirstTurn() {
        move = players.get((int) (Math.random() * players.size()));
    }

    private void playerSetup() {
        Scanner reader = new Scanner(System.in);
        int p;
        while (true) {
            System.out.println("Enter the number of players (Between 1 and 6)");
            p = reader.nextInt();
            if (p > 6 || p < 1) {
                System.out.println("Invalid number of players, there must be between 1 and 6 players.");
            } else {
                break;
            }
        }
        reader.close();

        List<Player> availablePlayers = new ArrayList<>();
        availablePlayers.add(new Player(new Point(8, 25), "Miss Scarlett"));
        availablePlayers.add(new Player(new Point(1, 18), "Col. Mustard"));
        availablePlayers.add(new Player(new Point(10, 1), "Mrs. White"));
        availablePlayers.add(new Player(new Point(15, 1), "Mr. Green"));
        availablePlayers.add(new Player(new Point(24, 7), "Mrs. Peacock"));
        availablePlayers.add(new Player(new Point(24, 20), "Prof. Plum"));

        for (int i = 0; i < p; i++)
            players.add(availablePlayers.get(i));

        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player " + i + ": " + players.get(i).getName());
        }
    }

    private void cardSetup() {
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card("Miss Scarlett", "Player"));
        playerCards.add(new Card("Col. Mustard", "Player"));
        playerCards.add(new Card("Mrs. White", "Player"));
        playerCards.add(new Card("Mr. Green", "Player"));
        playerCards.add(new Card("Mrs. Peacock", "Player"));
        playerCards.add(new Card("Prof. Plum", "Player"));

        List<Card> roomCards = new ArrayList<>();
        roomCards.add(new Card("Kitchen", "Room"));
        roomCards.add(new Card("Ballroom", "Room"));
        roomCards.add(new Card("Conservatory", "Room"));
        roomCards.add(new Card("Dining Room", "Room"));
        roomCards.add(new Card("Billiard Room", "Room"));
        roomCards.add(new Card("Library", "Room"));
        roomCards.add(new Card("Study", "Room"));
        roomCards.add(new Card("Hall", "Room"));
        roomCards.add(new Card("Lounge", "Room"));

        List<Card> weaponCards = new ArrayList<>();
        weaponCards.add(new Card("Candlestick", "Weapon"));
        weaponCards.add(new Card("Dagger", "Weapon"));
        weaponCards.add(new Card("Lead Pipe", "Weapon"));
        weaponCards.add(new Card("Revolver", "Weapon"));
        weaponCards.add(new Card("Rope", "Weapon"));
        weaponCards.add(new Card("Spanner", "Weapon"));

        generateSolutions(playerCards, roomCards, weaponCards);

        Collections.shuffle(playerCards);
        Collections.shuffle(roomCards);
        Collections.shuffle(weaponCards);

        solution.add(playerCards.remove(0));
        solution.add(roomCards.remove(0));
        solution.add(weaponCards.remove(0));

        List<Card> remainingCards = new ArrayList<>();
        remainingCards.addAll(playerCards);
        remainingCards.addAll(roomCards);
        remainingCards.addAll(weaponCards);

        Collections.shuffle(remainingCards);

        int counter = 0;
        for (Card card : remainingCards) {
            players.get(counter).giveCard(card);
            counter++;
            if (counter > players.size() - 1)
                counter = 0;
        }

        for (Player player : players) {
            player.setPotentialSolutions();
        }
    }

    private void generateSolutions(List<Card> playerCards, List<Card> roomCards, List<Card> weaponCards) {
        List<List<Card>> sols = new ArrayList<>();
        List<Card> sol = new ArrayList<>();
        for (int i = 0; i < playerCards.size(); i++) {
            for (int j = 0; j < roomCards.size(); j++) {
                for (int k = 0; k < weaponCards.size(); k++) {
                    sol = Arrays.asList(playerCards.get(i), roomCards.get(j), weaponCards.get(k));
                    sols.add(sol);
                }
            }
        }

        for (Player player : players) {
            player.setPotentialSolutions(sols);
        }
    }

    private void weaponSetup() {
       /* List<Room> roomShuffler = board.getRooms();
        Collections.shuffle(roomShuffler);
        roomShuffler.get(0).setWeapon(new Weapon("Candlestick", roomShuffler.get(0)));
        roomShuffler.get(1).setWeapon(new Weapon("Dagger", roomShuffler.get(1)));
        roomShuffler.get(2).setWeapon(new Weapon("Lead Pipe", roomShuffler.get(2)));
        roomShuffler.get(3).setWeapon(new Weapon("Revolver", roomShuffler.get(3)));
        roomShuffler.get(4).setWeapon(new Weapon("Rope", roomShuffler.get(4)));
        roomShuffler.get(5).setWeapon(new Weapon("Spanner", roomShuffler.get(5)));
        */
    }

    public static void main(String... args) {
        Cluedo cluedo = new Cluedo();
    }
}