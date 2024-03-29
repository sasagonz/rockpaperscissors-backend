package com.santi.rockpaperscissors.processor;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Shape;
import com.santi.rockpaperscissors.processor.RoundResultMapper;

public class RoundResultMapperTest {

    private RoundResultMapper roundResultMapper;

    @Before
    public void setUp() {
        roundResultMapper = new RoundResultMapper();
    }

    @Test
    public void shouldMapFrom() {
        // GIVEN
        RoundRequest roundRequest = new RoundRequest();
        roundRequest.setPlayer1(Shape.PAPER);
        roundRequest.setPlayer2(Shape.ROCK);

        // WHEN
        RoundResult roundResult = roundResultMapper.apply(roundRequest);

        // THEN
        assertEquals(Shape.PAPER, roundResult.getPlayer1());
        assertEquals(Shape.ROCK, roundResult.getPlayer2());
    }

}
