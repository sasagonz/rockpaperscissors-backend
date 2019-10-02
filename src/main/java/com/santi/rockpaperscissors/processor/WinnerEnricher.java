package com.santi.rockpaperscissors.processor;

import java.util.function.BiFunction;
import com.santi.rockpaperscissors.engine.RoundEngine;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Winner;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WinnerEnricher implements BiFunction<RoundResult, RoundRequest, RoundResult> {

    private RoundEngine roundEngine;

    @Override
    public RoundResult apply(RoundResult roundResult, RoundRequest roundRequest) {
        Winner winner = roundEngine.getWinner(roundRequest.getPlayer1(), roundRequest.getPlayer2());
        roundResult.setResult(winner);
        return roundResult;
    }
}
