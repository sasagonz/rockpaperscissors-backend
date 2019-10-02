package com.santi.rockpaperscissors.processor;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.santi.rockpaperscissors.engine.RoundEngine;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Winner;
import com.santi.rockpaperscissors.processor.WinnerEnricher;

@RunWith(MockitoJUnitRunner.class)
public class WinnerEnricherTest {

    private WinnerEnricher winnerEnricher;

    @Mock
    private RoundEngine roundEngine;

    @Before
    public void setUp() {
        winnerEnricher = new WinnerEnricher(roundEngine);
    }

    @Test
    public void shouldEnrich() {
        // GIVEN
        RoundResult roundResult = new RoundResult();
        when(roundEngine.getWinner(any(), any())).thenReturn(Winner.PLAYER1);

        // WHEN
        roundResult = winnerEnricher.apply(roundResult, new RoundRequest());

        // THEN
        assertEquals(Winner.PLAYER1, roundResult.getResult());
    }

}
