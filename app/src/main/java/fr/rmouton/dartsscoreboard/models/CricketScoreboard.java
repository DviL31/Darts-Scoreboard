package fr.rmouton.dartsscoreboard.models;

import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.List;

/**
 * The cricket scoreboard.
 * Created by Romain Mouton on 12/06/15.
 */
public class CricketScoreboard extends Scoreboard {

    /**
     * 3 hit must be done to close a section.
     */
    private static final int MAX_HIT_BY_SECTION = 3;

    private static final Section[] WINNING_SECTIONS = {Section.TWENTY, Section.NINETEEN,
            Section.EIGHTEEN, Section.SEVENTEEN, Section.SIXTEEN, Section.FIVETEEN, Section.BULL};
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

    public void setCutThroat(boolean cutThroat) {
        this.cutThroat = cutThroat;
    }

    public boolean isCutThroat() {
        return cutThroat;
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
     *
     * @return the winner is exists, null otherwise
     */
    public Player getWinner() {
        for (int i = 0; i < hitSections.size(); i++) {
            int playerId = hitSections.keyAt(i);
            HashMap<Section, Integer> hitSection = hitSections.get(playerId);

            // At least one hit
            if (hitSection != null) {
                boolean allHit = true;
                for (Section section : WINNING_SECTIONS) {
                    Integer countSection = hitSection.get(section);
                    if (countSection == null || countSection.intValue() != MAX_HIT_BY_SECTION) {
                        allHit = false;
                        break;
                    }
                }

                // This player hit all section 3 times
                if (allHit) {
                    Player player = getPlayer(playerId);
                    if (getScore(player) == maxScore()) {
                        return player;
                    }
                }
            }
        }

        return null;
    }

    public int getScore(Player player) {
        return scores != null ? scores.get(player.id) : 0;
    }

    public int maxScore() {
        int maxScore = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.valueAt(i) > maxScore)
                maxScore = scores.valueAt(i);
        }
        return maxScore;
    }

    @Override
    public void hit(Player player, Section section, int count) {
        super.hit(player, section, count);

        HashMap<Section, Integer> playerHitSections = hitSections.get(player.id);

        // first hit ?
        if (playerHitSections == null) {
            playerHitSections = new HashMap<>(7);
        }

        // first hit in this section ?
        if (!playerHitSections.containsKey(section)) {
            playerHitSections.put(section, 0);
        }

        for (int i = 0; i < count; i++) {

            // current hits for thius section
            int currentSectionHit = playerHitSections.get(section);

            // Section already closed
            if (currentSectionHit == MAX_HIT_BY_SECTION) {

                // TODO cutThroat
                boolean allPlayerHaveClosedThisSection = true;

                for (int j = 0; j < players.size(); j++) {
                    int playerId = players.keyAt(j);

                    // Do not check current player
                    if (playerId == player.id)
                        continue;

                    // check other players section hits
                    if (hitSections.get(playerId) == null ||
                            hitSections.get(playerId).get(section) == null ||
                            hitSections.get(playerId).get(section).intValue() != MAX_HIT_BY_SECTION) {
                        allPlayerHaveClosedThisSection = false;
                        break;
                    }
                }

                // Only score if at least one player hasn't closed this section
                if (!allPlayerHaveClosedThisSection) {
                    // score
                    int currentScore = scores.get(player.id);
                    scores.put(player.id, currentScore + section.value);
                }
            } // one more hit
            else {
                playerHitSections.put(section, currentSectionHit + 1);
            }
        }

        hitSections.put(player.id, playerHitSections);

        // Do we have a winner
        Player winner = getWinner();
        if (winner != null) {
            endParty(winner);
        }
    }

}
