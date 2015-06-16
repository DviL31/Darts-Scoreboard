package fr.rmouton.dartsscoreboard.models;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Romain Mouton on 16/06/15.
 */
public class PlayerTest extends TestCase {

    @Test
    public void testToString() throws Exception {

        assertEquals(new Player().toString(), "Player 1");
        assertEquals(new Player(1, "Pig").toString(), "Pig");
    }
}