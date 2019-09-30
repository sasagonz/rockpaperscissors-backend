package com.santi.rockpaperscissors.api;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import com.santi.rockpaperscissors.mapper.StatisticMapper;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.model.Statistic;
import com.santi.rockpaperscissors.storage.RockPaperScissorsStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultRoundsApiDelegate implements RoundsApiDelegate {

    private NativeWebRequest nativeWebRequest;
    private RockPaperScissorsStorage rockPaperScissorsStorage;
    private StatisticMapper statisticMapper;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }

    @Override
    public ResponseEntity<Statistic> getStatistics() {
        List<RoundResult> roundResults = rockPaperScissorsStorage.getRounds();
        Statistic statistic = statisticMapper.from(roundResults);
        return new ResponseEntity<>(statistic, HttpStatus.OK);

    }
}
