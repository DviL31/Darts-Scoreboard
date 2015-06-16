package fr.rmouton.dartsscoreboard.models;

/**
 * A simple darts player.
 * Created by Romain Mouton on 15/06/15.
 */
public class Player {

    private static final String DEFAULT_PLAYER_NAME = "Player ";

    /**
     * A unique id player
     */
    public int id;

    /**
     * Player's name
     */
    public String name;

    public Player() {
        id = 1;
        name = DEFAULT_PLAYER_NAME + id;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
