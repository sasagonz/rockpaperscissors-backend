package com.santi.rockpaperscissors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.NativeWebRequest;
import com.santi.rockpaperscissors.api.DefaultCustomersApiDelegate;
import com.santi.rockpaperscissors.api.DefaultRoundsApiDelegate;
import com.santi.rockpaperscissors.engine.RoundEngine;
import com.santi.rockpaperscissors.engine.ShapeComparator;
import com.santi.rockpaperscissors.engine.ShapePrecedenceGraph;
import com.santi.rockpaperscissors.processor.RoundResultMapper;
import com.santi.rockpaperscissors.processor.RoundResultTransformer;
import com.santi.rockpaperscissors.processor.StatisticMapper;
import com.santi.rockpaperscissors.processor.WinnerEnricher;
import com.santi.rockpaperscissors.storage.InMemoryMapStorage;
import com.santi.rockpaperscissors.storage.RockPaperScissorsStorage;

@Configuration
public class Config {

    @Bean
    public RoundEngine roundEngine(ShapeComparator shapeComparator) {
        return new RoundEngine(shapeComparator);
    }

    @Bean
    public ShapeComparator shapeComparator(ShapePrecedenceGraph shapePrecedenceGraph) {
        return new ShapeComparator(shapePrecedenceGraph);
    }

    @Bean
    public ShapePrecedenceGraph shapePrecedenceGraph() {
        return new ShapePrecedenceGraph();
    }

    @Bean
    public RockPaperScissorsStorage storage() {
        return new InMemoryMapStorage();
    }

    @Bean
    public RoundResultMapper roundResultMapper() {
        return new RoundResultMapper();
    }

    @Bean
    public WinnerEnricher winnerEnricher(RoundEngine roundEngine) {
        return new WinnerEnricher(roundEngine);
    }

    @Bean
    public RoundResultTransformer roundResultTransformer(
        RoundResultMapper roundResultMapper,
        WinnerEnricher winnerEnricher) {
        return new RoundResultTransformer(roundResultMapper, winnerEnricher);
    }

    @Bean
    public DefaultCustomersApiDelegate defaultCustomersApiDelegate(
        NativeWebRequest nativeWebRequest,
        RockPaperScissorsStorage storage,
        RoundResultTransformer roundResultTransformer) {
        return new DefaultCustomersApiDelegate(
            nativeWebRequest,
            storage,
            roundResultTransformer);
    }

    @Bean
    public StatisticMapper statisticMapper() {
        return new StatisticMapper();
    }

    @Bean
    public DefaultRoundsApiDelegate defaultRoundsApiDelegate(
        NativeWebRequest nativeWebRequest,
        RockPaperScissorsStorage rockPaperScissorsStorage,
        StatisticMapper statisticMapper) {
        return new DefaultRoundsApiDelegate(
            nativeWebRequest,
            rockPaperScissorsStorage,
            statisticMapper);
    }
}
