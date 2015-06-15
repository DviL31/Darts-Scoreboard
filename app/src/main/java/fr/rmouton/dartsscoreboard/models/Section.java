package fr.rmouton.dartsscoreboard.models;

/**
 * A Dartboard section, from 1 to 20 and bull.
 * Created by Romain Mouton on 15/06/15.
 */
public enum Section {

    BULL(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12),
    THIRTEEN(13),
    FOURTEEN(14),
    FIVETEEN(15),
    SIXTEEN(16),
    SEVENTEEN(17),
    EIGHTEEN(18),
    NINETEEN(19),
    TWENTY(20);

    /**
     * The number of point of this section
     */
    public int value;

    Section(int value) {
        this.value = value;
    }

    /**
     * @return The name of the section
     */
    String label() {
        if (value == 0)
            return "Bull";
        return String.valueOf(value);
    }

}
