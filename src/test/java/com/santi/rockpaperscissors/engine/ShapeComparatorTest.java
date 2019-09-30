package com.santi.rockpaperscissors.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import com.santi.rockpaperscissors.model.Shape;

public class ShapeComparatorTest {

    private ShapeComparator shapeComparator;

    @Before
    public void setUp() {
        shapeComparator = new ShapeComparator(new ShapePrecedenceGraph());
    }

    @Test
    public void shouldWinRock() {
        // WHEN
        int result = shapeComparator.compare(Shape.ROCK, Shape.SCISSORS);

        // THEN
        assertThat(result, is(1));
    }

    @Test
    public void shouldWinPaper() {
        // WHEN
        int result = shapeComparator.compare(Shape.ROCK, Shape.PAPER);

        // THEN
        assertThat(result, is(-1));
    }

    @Test
    public void shouldWinScissors() {
        // WHEN
        int result = shapeComparator.compare(Shape.SCISSORS, Shape.PAPER);

        // THEN
        assertThat(result, is(1));
    }

    @Test
    public void shouldBeADrawn() {
        // WHEN
        int result = shapeComparator.compare(Shape.SCISSORS, Shape.SCISSORS);

        // THEN
        assertThat(result, is(0));
    }
}
