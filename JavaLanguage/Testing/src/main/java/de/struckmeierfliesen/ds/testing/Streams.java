package de.struckmeierfliesen.ds.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    public Streams() {
        List<Integer> collect = Stream.iterate(0, n -> n + 10).limit(10).collect(Collectors.toCollection(ArrayList::new));

        //collect.forEach(System.out::println);
    }
}
