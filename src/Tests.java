import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tests {
    //Do not need some of the tests because we changed that you cannot move around in a room

    @Test
    public void testEnterRoom(){
        Cluedo cluedo = new Cluedo();
        setupMockPlayer(cluedo, new Point(7, 6), 0, null);
        cluedo.setup(true);
        Player p1 = cluedo.getPlayers().get(0);

        p1.translate("right", 1);
        assertEquals(p1.getCoords(),new Point(8, 6));
        p1.enterRoom();
        assertEquals(p1.getCoords(), new Point(9, 6));
        assertNotNull(p1.getRoom());
    }

    @Test
    public void testOutsideRoomWalkIntoWall(){
        Cluedo cluedo = new Cluedo();
        setupMockPlayer(cluedo, new Point(7, 6), 0, null);
        cluedo.setup(true);
        Player p1 = cluedo.getPlayers().get(0);

        assertFalse(p1.translate("left",1));
        assertEquals(p1.getCoords(), new Point(7,6));
    }

//    @Test
//    public void testInsideRoomWalkIntoTopWall(){
//        Cluedo cluedo = new Cluedo();
//        setupMockPlayer(cluedo, new Point(4, 3), 0, null);
//        cluedo.setup(true);
//        Player p1 = cluedo.getPlayers().get(0);
//        p1.setRoom(cluedo.getBoard().getRooms().get("kitchen"));
//
//        assertTrue(p1.translate("upwards",1));
//        assertEquals(p1.getCoords(), new Point(4,2));
//        assertFalse(p1.translate("upwards",1));
//        assertEquals(p1.getCoords(), new Point(4,2));
//        assertTrue(p1.translate("right",1));
//        assertEquals(p1.getCoords(), new Point(5,2));
//        assertTrue(p1.translate("left",1));
//        assertEquals(p1.getCoords(), new Point(4,2));
//
//    }

//    @Test
//    public void testInsideRoomWalkIntoLeftWall(){
//        Cluedo cluedo = new Cluedo();
//        setupMockPlayer(cluedo, new Point(2, 4), 0, null);
//        cluedo.setup(true);
//        Player p1 = cluedo.getPlayers().get(0);
//        p1.setRoom(cluedo.getBoard().getRooms().get("kitchen"));
//
//        assertTrue(p1.translate("left",1));
//        assertEquals(p1.getCoords(), new Point(1,4));
//        assertFalse(p1.translate("left",1));
//        assertEquals(p1.getCoords(), new Point(1,4));
//        assertTrue(p1.translate("downwards",1));
//        assertEquals(p1.getCoords(), new Point(1,5));
//        assertTrue(p1.translate("upwards",1));
//        assertEquals(p1.getCoords(), new Point(1,4));
//    }

//    @Test
//    public void testInsideRoomWalkIntoBottomWall(){
//        Cluedo cluedo = new Cluedo();
//        setupMockPlayer(cluedo, new Point(4, 6), 0, null);
//        cluedo.setup(true);
//        Player p1 = cluedo.getPlayers().get(0);
//        p1.setRoom(cluedo.getBoard().getRooms().get("kitchen"));
//
//        assertTrue(p1.translate("downwards",1));
//        assertEquals(p1.getCoords(), new Point(4,7));
//        assertFalse(p1.translate("downwards",1));
//        assertEquals(p1.getCoords(), new Point(4,7));
//        assertTrue(p1.translate("left",1));
//        assertEquals(p1.getCoords(), new Point(3,7));
//        assertTrue(p1.translate("right",1));
//        assertEquals(p1.getCoords(), new Point(4,7));
//    }

//    @Test
//    public void testInsideRoomWalkIntoRightWall(){
//        Cluedo cluedo = new Cluedo();
//        setupMockPlayer(cluedo, new Point(5, 4), 0, null);
//        cluedo.setup(true);
//        Player p1 = cluedo.getPlayers().get(0);
//        p1.setRoom(cluedo.getBoard().getRooms().get("kitchen"));
//
//        assertTrue(p1.translate("right",1));
//        assertEquals(p1.getCoords(), new Point(6,4));
//        assertFalse(p1.translate("right",1));
//        assertEquals(p1.getCoords(), new Point(6,4));
//        assertTrue(p1.translate("downwards",1));
//        assertEquals(p1.getCoords(), new Point(6,5));
//        assertTrue(p1.translate("upwards",1));
//        assertEquals(p1.getCoords(), new Point(6,4));
//    }

//    @Test
//    public void testExitRoom(){
//        Cluedo cluedo = new Cluedo();
//        setupMockPlayer(cluedo, new Point(5, 6), 0, null);
//        cluedo.setup(true);
//        Player p1 = cluedo.getPlayers().get(0);
//        p1.setRoom(cluedo.getBoard().getRooms().get("kitchen"));
//
//        assertTrue(p1.translate("downwards",1));
//        assertEquals(p1.getCoords(), new Point(5,7));
//        assertTrue(p1.translate("downwards",1));
//        assertEquals(p1.getCoords(), new Point(5,8));
//
//        assertNull(p1.getRoom());
//    }

    @Test
    public void testRefutableCardsEmpty() {
        Cluedo cluedo = new Cluedo();
        List<Card> hand = Arrays.asList(new Card("Col. Mustard", "Player"), new Card("Candlestick", "Weapon"), new Card("Hall", "Room"));
        setupMockPlayer(cluedo, null, 0, hand);
        Player p1 = cluedo.getPlayers().get(0);
        List<String> suggestion = Arrays.asList("Prof. Peacock", "Dagger", "Lounge");

        assertTrue(p1.refutableCards(suggestion).isEmpty());
    }

    @Test
    public void testRefutableCardsSingle() {
        Cluedo cluedo = new Cluedo();
        List<Card> hand = Arrays.asList(new Card("Prof. Peacock", "Player"), new Card("Candlestick", "Weapon"), new Card("Hall", "Room"));
        setupMockPlayer(cluedo, null, 0, hand);
        Player p1 = cluedo.getPlayers().get(0);
        List<String> suggestion = Arrays.asList("Prof. Peacock", "Dagger", "Lounge");

        assertTrue(p1.refutableCards(suggestion).get(0).getName().equals("Prof. Peacock"));
    }

    @Test
    public void testRefutableCardsMultiple() {
        Cluedo cluedo = new Cluedo();
        List<Card> hand = Arrays.asList(new Card("Prof. Peacock", "Player"), new Card("Candlestick", "Weapon"), new Card("Lounge", "Room"));
        setupMockPlayer(cluedo, null, 0, hand);
        Player p1 = cluedo.getPlayers().get(0);
        List<String> suggestion = Arrays.asList("Prof. Peacock", "Dagger", "Lounge");
        List<String> correctAnswer = Arrays.asList("Prof. Peacock", "Lounge");

        assertTrue(p1.refutableCards(suggestion).get(0).getName().equals(correctAnswer.get(0)));
        assertTrue(p1.refutableCards(suggestion).get(1).getName().equals(correctAnswer.get(1)));
    }

    @Test
    public void correctAccusation() {
        Cluedo cluedo = new Cluedo();
        cluedo.setup(true);

        List<String> accusation = new ArrayList<>();
        accusation.add(cluedo.getSolution().get(0).getName());
        accusation.add(cluedo.getSolution().get(1).getName());
        accusation.add(cluedo.getSolution().get(2).getName());
        cluedo.accuse(accusation);

        assertTrue(cluedo.isGameWon());
    }

    @Test
    public void incorrectAccusation() {
        Cluedo cluedo = new Cluedo();
        cluedo.setup(true);
        setupMockPlayer(cluedo, null, 0, null);

        List<String> accusation = new ArrayList<>();
        accusation.add("wrong");
        accusation.add("wrong");
        accusation.add("wrong");
        cluedo.accuse(accusation);

        assertTrue(!cluedo.isGameWon());
        assertTrue(cluedo.getPlayers().isEmpty());
    }
    private void setupMockPlayer(Cluedo game, Point coords, int playerNumber, List<Card> hand) {
        Player p = new Player(coords, "Miss. Scarlett", "Mock Player");
        game.getPlayers().add(p);
        if (hand != null){
            for (Card c : hand){
                p.giveCard(c);
            }
        }


    }
}


