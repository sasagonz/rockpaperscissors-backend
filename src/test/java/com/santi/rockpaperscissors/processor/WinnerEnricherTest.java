package com.santi.rockpaperscissors.processor;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Winner;

public class WinnerEnricherTest {

    private WinnerEnricher winnerEnricher;

    @Before
    public void setUp() {
        winnerEnricher = new WinnerEnricher();
    }

    @Test
    public void shouldEnrich() {
        // GIVEN
        RoundResult roundResult = new RoundResult();
        Winner winner = Winner.PLAYER1;

        // WHEN
        roundResult = winnerEnricher.apply(roundResult, winner);

        // THEN
        assertEquals(Winner.PLAYER1, roundResult.getResult());
    }

}
