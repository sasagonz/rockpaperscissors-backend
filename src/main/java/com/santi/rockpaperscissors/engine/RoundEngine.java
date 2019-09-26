package com.santi.rockpaperscissors.engine;

import com.santi.rockpaperscissors.model.Player;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.Shape;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundEngine {

    private ShapeComparator shapeComparator;

    public String getWinner(RoundRequest roundRequest) {

        Player player1 = roundRequest.getPlayer1();
        Player player2 = roundRequest.getPlayer2();

        Shape value1 = player1.getValue();
        Shape value2 = player2.getValue();

        if (shapeComparator.compare(value1, value2) > 0) {
            return player1.getName();
        } else if (shapeComparator.compare(value1, value2) < 0) {
            return player2.getName();
        } else {
            return "";
        }
    }
}
