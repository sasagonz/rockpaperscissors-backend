package com.santi.rockpaperscissors.engine;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import com.santi.rockpaperscissors.model.Shape;
import lombok.Getter;

@Getter
public class ShapePrecedenceGraph {

    private Map<Shape, List<Shape>> winsOverList;

    public ShapePrecedenceGraph() {
        winsOverList = new EnumMap<>(Shape.class);
        winsOverList.put(Shape.ROCK, Arrays.asList(Shape.SCISSORS));
        winsOverList.put(Shape.PAPER, Arrays.asList(Shape.ROCK));
        winsOverList.put(Shape.SCISSORS, Arrays.asList(Shape.PAPER));
    }
}
