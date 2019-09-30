package com.santi.rockpaperscissors.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.santi.rockpaperscissors.model.Shape;
import com.santi.rockpaperscissors.model.Winner;

@RunWith(MockitoJUnitRunner.class)
public class RoundEngineTest {

    private RoundEngine roundEngine;

    @Mock
    private ShapeComparator shapeComparator;

    @Before
    public void setUp() {
        roundEngine = new RoundEngine(shapeComparator);
    }

    @Test
    public void shouldWinPlayer1() {
        // GIVEN
        when(shapeComparator.compare(Shape.PAPER, Shape.ROCK)).thenReturn(1);

        // WHEN
        Winner winner = roundEngine.getWinner(Shape.PAPER, Shape.ROCK);

        // THEN
        assertThat(winner, is(Winner.PLAYER1));
    }

    @Test
    public void shouldWinPlayer2() {
        // GIVEN
        when(shapeComparator.compare(Shape.PAPER, Shape.SCISSORS)).thenReturn(-1);

        // WHEN
        Winner winner = roundEngine.getWinner(Shape.PAPER, Shape.SCISSORS);

        // THEN
        assertThat(winner, is(Winner.PLAYER2));
    }

    @Test
    public void shouldNotWinAnybody() {
        // GIVEN
        when(shapeComparator.compare(Shape.PAPER, Shape.PAPER)).thenReturn(0);

        // WHEN
        Winner winner = roundEngine.getWinner(Shape.PAPER, Shape.PAPER);

        // THEN
        assertThat(winner, is(Winner.DRAWN));
    }

}
