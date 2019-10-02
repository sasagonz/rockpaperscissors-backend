package com.santi.rockpaperscissors.processor;

import java.util.function.Function;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;

public class RoundResultMapper implements Function<RoundRequest, RoundResult>{

    @Override
    public RoundResult apply(RoundRequest roundRequest) {
        RoundResult roundResult = new RoundResult();
        roundResult.setPlayer1(roundRequest.getPlayer1());
        roundResult.setPlayer2(roundRequest.getPlayer2());
        return roundResult;
    }
}
