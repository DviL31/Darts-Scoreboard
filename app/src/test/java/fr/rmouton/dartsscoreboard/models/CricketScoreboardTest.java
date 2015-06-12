package fr.rmouton.dartsscoreboard.models;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Romain Mouton on 12/06/15.
 */
public class CricketScoreboardTest extends TestCase {


    @Test
    public void testScoreboardFather() {
        assertTrue(new CricketScoreboard() instanceof Scoreboard);
    }

    @Test
    public void test() {

    }

}