package com.santi.rockpaperscissors.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.santi.rockpaperscissors.model.Player;
import com.santi.rockpaperscissors.model.Shape;

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
    public void shouldGetSomePlayerName() {
        // GIVEN
        when(shapeComparator.compare(Shape.PAPER, Shape.ROCK)).thenReturn(1);
        Player player1 =
            Player
                .builder()
                .name("player1")
                .value(Shape.PAPER)
                .build();
        Player player2 =
            Player
                .builder()
                .name("player2")
                .value(Shape.ROCK)
                .build();

        // WHEN
        String winnerName = roundEngine.getWinnerName(player1, player2);

        // THEN
        assertThat(winnerName, is("player1"));
    }

    @Test
    public void shouldNotGetAnyPlayerName() {
        // GIVEN
        when(shapeComparator.compare(Shape.PAPER, Shape.PAPER)).thenReturn(0);
        Player player1 =
            Player
                .builder()
                .name("player1")
                .value(Shape.PAPER)
                .build();
        Player player2 =
            Player
                .builder()
                .name("player2")
                .value(Shape.PAPER)
                .build();

        // WHEN
        String winnerName = roundEngine.getWinnerName(player1, player2);

        // THEN
        assertThat(winnerName, isEmptyString());
    }

}
