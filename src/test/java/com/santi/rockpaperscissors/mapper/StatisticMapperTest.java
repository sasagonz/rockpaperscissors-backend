package com.santi.rockpaperscissors.mapper;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Shape;
import com.santi.rockpaperscissors.model.Statistic;
import com.santi.rockpaperscissors.model.Winner;

public class StatisticMapperTest {

    private StatisticMapper statisticMapper;

    @Before
    public void setUp() {
        statisticMapper = new StatisticMapper();
    }

    @Test
    public void shouldMapFrom() {
        // GIVEN
        List<RoundResult> roundResults =
            Arrays
                .asList(
                    createRoundResult(Shape.PAPER, Shape.ROCK, Winner.PLAYER1),
                    createRoundResult(Shape.PAPER, Shape.SCISSORS, Winner.PLAYER2),
                    createRoundResult(Shape.PAPER, Shape.PAPER, Winner.DRAWN));

        // WHEN
        Statistic statistic = statisticMapper.from(roundResults);

        // THEN
        assertEquals(Long.valueOf(3), statistic.getTotalRoundsPlayed());
        assertEquals(Long.valueOf(1), statistic.getTotalDrawns());
        assertEquals(Long.valueOf(1), statistic.getTotalWinsFirstPlayer());
        assertEquals(Long.valueOf(1), statistic.getTotalWinsSecondPlayer());
    }

    private RoundResult createRoundResult(Shape player1, Shape player2, Winner winner) {
        RoundResult roundResult = new RoundResult();
        roundResult.setPlayer1(player1);
        roundResult.setPlayer2(player2);
        roundResult.setResult(winner);
        return roundResult;
    }
}
