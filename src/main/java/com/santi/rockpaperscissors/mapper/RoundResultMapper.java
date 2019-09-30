package com.santi.rockpaperscissors.mapper;

import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Winner;

public class RoundResultMapper {

    public RoundResult from(RoundRequest roundRequest, Winner winner) {
        RoundResult roundResult = new RoundResult();
        roundResult.setPlayer1(roundRequest.getPlayer1());
        roundResult.setPlayer2(roundRequest.getPlayer2());
        roundResult.setResult(winner);
        return roundResult;
    }
}
