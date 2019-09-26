package com.santi.rockpaperscissors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.santi.rockpaperscissors.engine.RoundEngine;
import com.santi.rockpaperscissors.engine.ShapeComparator;
import com.santi.rockpaperscissors.engine.ShapePrecedenceGraph;

@Configuration
public class Config {

    @Bean
    public RoundEngine roundCalculus(ShapeComparator shapeComparator) {
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
}
