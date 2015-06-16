package fr.rmouton.dartsscoreboard.models;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Romain Mouton on 12/06/15.
 */
public class CricketScoreboardTest extends TestCase {


    @Test
    public void testScoreboardFather() {
        assertTrue(new CricketScoreboard(Arrays.asList(new Player())) instanceof Scoreboard);
    }

    @Test
    public void testNewPartyIsNotEnded() {
        CricketScoreboard cricketScoreboard = new CricketScoreboard(Arrays.asList(new Player()));
        assertFalse(cricketScoreboard.isPartyEnded());
    }

    @Test
    public void tewtNewPartyDefaultValues() {
        List<Player> players = Arrays.asList(new Player());
        CricketScoreboard cricketScoreboard = new CricketScoreboard(players);

        assertEquals(cricketScoreboard.players.size(), players.size());
        assertNotNull(cricketScoreboard.scores);
        assertNotNull(cricketScoreboard.hitSections);
        assertNull(cricketScoreboard.getWinner());
        assertFalse(cricketScoreboard.isCutThroat());
    }

    @Test
    public void testHit() {
        Player duck = new Player(1, "Duck");
        Player rabbit = new Player(2, "Rabbit");
        List<Player> players = Arrays.asList(duck, rabbit);
        CricketScoreboard cricketScoreboard = new CricketScoreboard(players);

        HashMap<Section, Integer> player1hitSections = cricketScoreboard.hitSections.get(1);
        HashMap<Section, Integer> player2hitSections = cricketScoreboard.hitSections.get(2);

        // No hit
        assertNull(player1hitSections);
        assertNull(cricketScoreboard.hitSections.get(2));

        // Player 1 hit the twenty section
        cricketScoreboard.hit(duck, Section.TWENTY);
        player1hitSections = cricketScoreboard.hitSections.get(1);
        assertNotNull(player1hitSections);
        assertEquals(player1hitSections.get(Section.TWENTY).intValue(), 1);
        assertNull(player1hitSections.get(Section.FIVETEEN));
        assertNull(player2hitSections);

        // Player 2 hit the Fourteen section
        cricketScoreboard.hit(rabbit, Section.FOURTEEN);
        player1hitSections = cricketScoreboard.hitSections.get(1);
        player2hitSections = cricketScoreboard.hitSections.get(2);
        assertNotNull(player1hitSections);
        assertNotNull(player2hitSections);
        assertEquals(player2hitSections.get(Section.FOURTEEN).intValue(), 1);
        assertNull(player2hitSections.get(Section.TWENTY));

    }

    @Test
    public void testScore() {
        Player duck = new Player(1, "Duck");
        Player rabbit = new Player(2, "Rabbit");
        List<Player> players = Arrays.asList(duck, rabbit);
        CricketScoreboard cricketScoreboard = new CricketScoreboard(players);

        assertEquals(cricketScoreboard.maxScore(), 0);
        cricketScoreboard.hit(duck, Section.TWENTY, 4); // 20 points !
        assertEquals(cricketScoreboard.maxScore(), 20);
        cricketScoreboard.hit(duck, Section.BULL, 4); // 25 points !
        assertEquals(cricketScoreboard.maxScore(), 45);

        assertEquals(cricketScoreboard.hitSections.get(1).get(Section.TWENTY).intValue(), 3);

    }

    @Test
    public void testEndParty() {
        Player duck = new Player(1, "Duck");
        Player rabbit = new Player(2, "Rabbit");
        List<Player> players = Arrays.asList(duck, rabbit);
        CricketScoreboard cricketScoreboard = new CricketScoreboard(players);

        cricketScoreboard.hit(duck, Section.TWENTY, 3);
        cricketScoreboard.hit(duck, Section.NINETEEN, 3);
        cricketScoreboard.hit(duck, Section.EIGHTEEN, 3);
        cricketScoreboard.hit(duck, Section.SEVENTEEN, 3);
        cricketScoreboard.hit(duck, Section.SIXTEEN, 3);
        cricketScoreboard.hit(duck, Section.FIVETEEN, 3);
        cricketScoreboard.hit(duck, Section.BULL, 4);

        assertEquals(cricketScoreboard.getWinner(), duck);
        assertNotSame(cricketScoreboard.getWinner(), rabbit);

    }

    @Test
    public void testCutThroat() {
        CricketScoreboard cricketScoreboard = new CricketScoreboard(Arrays.asList(new Player()));
        assertFalse(cricketScoreboard.isCutThroat());
        cricketScoreboard.setCutThroat(true);
        assertTrue(cricketScoreboard.isCutThroat());
    }
}