package com.santi.rockpaperscissors.engine;

import com.santi.rockpaperscissors.model.Shape;
import com.santi.rockpaperscissors.model.Winner;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundEngine {

    private ShapeComparator shapeComparator;

    public Winner getWinner(Shape value1, Shape value2) {
        if (shapeComparator.compare(value1, value2) > 0) {
            return Winner.PLAYER1;
        } else if (shapeComparator.compare(value1, value2) < 0) {
            return Winner.PLAYER2;
        } else {
            return Winner.DRAWN;
        }
    }
}
