package fr.rmouton.dartsscoreboard.models;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Romain Mouton on 12/06/15.
 */
public class DartboardTest extends TestCase {

    @Test
    public void testName() {
        Dartboard dartboard = new Dartboard();
        assertEquals("Dartboard", dartboard.getClass().getSimpleName());
    }

    @Test
    public void testSectionSize() {
        Dartboard dartboard = new Dartboard();
        assertEquals(dartboard.SECTIONS.length, 21);
    }

}