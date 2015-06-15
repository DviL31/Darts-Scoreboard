package fr.rmouton.dartsscoreboard.models;

import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The cricket scoreboard.
 * Created by Romain Mouton on 12/06/15.
 */
public class CricketScoreboard extends Scoreboard {

    /**
     * 3 hit must be done to close a section.
     */
    private static final int MAX_HIT_BY_SECTION = 3;

    /**
     * Cut-throat style scoring can be used, in which case points are undesirable;
     * hitting a number that is opened results in points being given to any other players who do
     * not have that number closed, and the lowest score wins.
     */
    public boolean cutThroat;

    /**
     * All players current score.
     */
    public SparseIntArray scores;

    /**
     * All hit player's sections.
     */
    public SparseArray<HashMap<Section, Integer>> hitSections;

    //player 1 : 15:0, 16:2

    public CricketScoreboard(List<Player> players) {
        super(players);
        scores = new SparseIntArray(players.size());
        hitSections = new SparseArray<>(players.size());
    }

    /**
     * Check the current party status.
     *
     * @return true if the current party is ended. A player won this game.
     */
    public boolean isPartyEnded() {
        return getWinner() != null;
    }

    /**
     * Verify if we have a winner.
     * @return the winner is exists, null otherwise
     */
    public Player getWinner() {
        for (int i = 0; i < hitSections.size(); i++) {
            HashMap<Section, Integer> hitSection = hitSections.get(i);

            boolean allHit = true;
            for (Map.Entry<Section, Integer> entry : hitSection.entrySet()) {
                if (entry.getValue() != MAX_HIT_BY_SECTION) {
                    allHit = false;
                    break;
                }
            }

            // This player hit all section 3 times
            if (allHit) {
                if (scores.get(i) == maxScore())
                    return getPlayer(i);
            }
        }

        return null;
    }

    public int maxScore() {
        int maxScore = scores.get(0);
        for (int i = 1; i < scores.size(); i++) {
            if (scores.get(i) > maxScore)
                maxScore = scores.get(i);
        }
        return maxScore;
    }

    @Override
    public void hit(Player player, Section section) {
        super.hit(player, section);

        HashMap<Section, Integer> playerHitSections = hitSections.get(player.id);

        // first hit ?
        if (playerHitSections == null) {
            playerHitSections = new HashMap<>(7);
            hitSections.put(player.id, playerHitSections);
        }

        // first hit in this section ?
        if (!playerHitSections.containsKey(section)) {
            playerHitSections.put(section, 0);
        }

        // hit the section
        int currentSectionHit = playerHitSections.get(section);

        // Section already closed
        if (currentSectionHit == MAX_HIT_BY_SECTION) {
            // TODO try to score
        } // one more hit
        else {
            playerHitSections.put(section, currentSectionHit + 1);
        }

        // Do we have a winner
        Player winner = getWinner();
        if (winner != null) {
            endParty(winner);
        }
    }

}
