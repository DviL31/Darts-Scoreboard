package fr.rmouton.dartsscoreboard.models;

/**
 * A X01 scoreboard. May be 301, 501, 701, 1001.
 * Created by Romain Mouton on 15/06/15.
 */
public class X01Scoreboard {

    enum Type {

        X301,
        X501,
        X701,
        X1001;
    }

    public Type type;

    public X01Scoreboard(Type type) {
        this.type = type;
    }
}
