package fr.rmouton.dartsscoreboard.models;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Romain Mouton on 16/06/15.
 */
public class X01ScoreboardTest extends TestCase {

    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void testType() {
        assertEquals(new X01Scoreboard(X01Scoreboard.Type.X301).type, X01Scoreboard.Type.X301);
    }
}