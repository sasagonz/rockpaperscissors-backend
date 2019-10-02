package com.santi.rockpaperscissors.processor;

import java.util.function.Function;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundResultTransformer implements Function<RoundRequest, RoundResult> {

    private RoundResultMapper roundResultMapper;
    private WinnerEnricher winnerEnricher;

    @Override
    public RoundResult apply(RoundRequest roundRequest) {
        return roundResultMapper
            .andThen(roundResult -> winnerEnricher.apply(roundResult, roundRequest))
            .apply(roundRequest);
    }

}
