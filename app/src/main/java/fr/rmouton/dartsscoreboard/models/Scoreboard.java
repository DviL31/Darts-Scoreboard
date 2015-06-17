package fr.rmouton.dartsscoreboard.models;

import android.util.SparseArray;

import java.util.List;

/**
 * The generic darts scoreboard.
 * Created by Romain Mouton on 12/06/15.
 */
public abstract class Scoreboard {

    /**
     * All players playing this darts game.
     */
    public SparseArray<Player> players;

    /**
     * The unique dartboard.
     */
    public Dartboard dartboard = new Dartboard();

    public Scoreboard(List<Player> players) {
        this.players = new SparseArray<>(players.size());
        for (Player player : players) {
            this.players.put(player.id, player);
        }
    }

    /**
     * Retrieve a player with an id.
     *
     * @param playerId The player id
     * @return A player with the playerId id
     */
    public Player getPlayer(int playerId) {
        return players.get(playerId);
    }

    /**
     * The player hit a section.
     *
     * @param section The section hit.
     */
    public void hit(Player player, Section section) {
        hit(player, section, 1);
    }

    /**
     * The player hit a section count times.
     *
     * @param section The section hit.
     */
    public void hit(Player player, Section section, int count) {

    }

    /**
     * The party is ended.
     *
     * @param winner The winner player.
     */
    protected void endParty(Player winner) {

    }

    /**
     * A new game starts, clear scoring but keep players
     */
    protected void newGame() {
    }
}
