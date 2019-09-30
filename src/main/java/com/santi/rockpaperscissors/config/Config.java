package com.santi.rockpaperscissors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.NativeWebRequest;
import com.santi.rockpaperscissors.api.DefaultCustomersApiDelegate;
import com.santi.rockpaperscissors.api.DefaultRoundsApiDelegate;
import com.santi.rockpaperscissors.engine.RoundEngine;
import com.santi.rockpaperscissors.engine.ShapeComparator;
import com.santi.rockpaperscissors.engine.ShapePrecedenceGraph;
import com.santi.rockpaperscissors.mapper.RoundResultMapper;
import com.santi.rockpaperscissors.mapper.StatisticMapper;
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
    public DefaultCustomersApiDelegate defaultCustomersApiDelegate(
        NativeWebRequest nativeWebRequest,
        RoundEngine roundEngine,
        RockPaperScissorsStorage storage,
        RoundResultMapper roundResultMapper) {
        return new DefaultCustomersApiDelegate(
            nativeWebRequest,
            roundEngine,
            storage,
            roundResultMapper);
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
