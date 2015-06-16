package fr.rmouton.dartsscoreboard.models;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Romain Mouton on 16/06/15.
 */
public class SectionTest extends TestCase {

    @Test
    public void testLabel() throws Exception {

        assertEquals(Section.TWELVE.label(), "12");
        assertEquals(Section.FOUR.label(), "4");
        assertEquals(Section.BULL.label(), "Bull");
    }
}