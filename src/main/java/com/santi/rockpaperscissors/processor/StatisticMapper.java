package com.santi.rockpaperscissors.processor;

import java.util.List;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Statistic;
import com.santi.rockpaperscissors.model.Winner;

public class StatisticMapper {

    public Statistic from(List<RoundResult> roundResults) {
        Statistic statistic = new Statistic();
        statistic.setTotalRoundsPlayed(Long.valueOf(roundResults.size()));
        statistic.setTotalWinsFirstPlayer(
            roundResults
                .stream()
                .filter(roundResult -> Winner.PLAYER1 == roundResult.getResult())
                .count());
        statistic.setTotalWinsSecondPlayer(
            roundResults
                .stream()
                .filter(roundResult -> Winner.PLAYER2 == roundResult.getResult())
                .count());
        statistic.setTotalDrawns(
            roundResults
                .stream()
                .filter(roundResult -> Winner.DRAWN == roundResult.getResult())
                .count());
        return statistic;
    }
}
