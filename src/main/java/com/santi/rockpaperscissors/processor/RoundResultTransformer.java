package com.santi.rockpaperscissors.processor;

import com.santi.rockpaperscissors.engine.RoundEngine;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Winner;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundResultTransformer {

    private RoundResultMapper roundResultMapper;
    private WinnerEnricher winnerEnricher;
    private RoundEngine roundEngine;

    public RoundResult transform(RoundRequest roundRequest) {
        Winner winner =
            roundEngine
                .getWinner(
                    roundRequest.getPlayer1(),
                    roundRequest.getPlayer2());
        return roundResultMapper
            .andThen(roundResult -> winnerEnricher
                .apply(
                    roundResult,
                    winner))
            .apply(roundRequest);
    }

}
