package com.santi.rockpaperscissors.processor;

import java.util.function.BiFunction;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Winner;

public class WinnerEnricher implements BiFunction<RoundResult, Winner, RoundResult> {

    @Override
    public RoundResult apply(RoundResult roundResult, Winner winner) {
        roundResult.setResult(winner);
        return roundResult;
    }
}
