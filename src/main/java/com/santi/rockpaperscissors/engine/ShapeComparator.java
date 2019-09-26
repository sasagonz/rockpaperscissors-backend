package com.santi.rockpaperscissors.engine;

import java.util.Comparator;
import com.santi.rockpaperscissors.model.Shape;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShapeComparator implements Comparator<Shape> {

    private ShapePrecedenceGraph shapePrecedenceGraph;

    @Override
    public int compare(Shape o1, Shape o2) {
        if (o1 == o2) {
            return 0;
        } else if (shapePrecedenceGraph
            .getWinsOverList()
            .get(o1)
            .contains(o2)) {
            return 1;
        } else {
            return -1;
        }
    }
}
