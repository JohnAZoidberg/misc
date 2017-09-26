package de.struckmeierfliesen.ds.testing;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    class Test<T> {
        public void test(T arg) {
            T t = null;
            try {
                t = (T) arg.getClass().newInstance();
                System.out.println(t.toString());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    class Foo {
        private String yeah;

        private Foo() {
            yeah = "meh";
        }

        private Foo(String hi) {
            yeah = hi;
        }


        @Override
        public String toString() {
            return yeah;
        }
    }

    public Generics() {
        Test<Foo> test = new Test<>();
        Foo arg = new Foo("yeah");
        try {
            arg.getClass().newInstance().toString();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //test.test(arg);
    }

    private void testCoordinates() {
        int[] x = {0, 1, 2, 3, 4, 5};
        int[] y = {0, 2, 4, 6, 8, 10};

        List<NumberPair<Integer, Integer>> coordinates = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            coordinates.add(i, new NumberPair<>(x[i], y[i]));
        }
        printCoordinates(coordinates);
        printPairs(coordinates);
    }

    private void testCasting() {
        List<?> wildcard = new ArrayList<Number>();
        Object o = wildcard.get(0);

        List<? extends Number> subclass = new ArrayList<Number>();
        Number number = subclass.get(0);

        List<? super Number> superclass = new ArrayList<Number>();
        superclass.add(new Integer(3));
        Object o1 = superclass.get(0);

        List<Number> explicit = new ArrayList<Number>();
        Number number1 = explicit.get(0);
        explicit.add(new Integer(5));

        superclass = explicit;

        subclass = explicit;

        wildcard = explicit;
        wildcard = subclass;
        wildcard = superclass;
    }

    private void printCoordinates(List<? extends Pair<Integer, Integer>> pairs) {
        for (Pair<Integer, Integer> pair : pairs) {
            int x = pair.getLeft();
            int y = pair.getRight();
            System.out.println(x + " - " + y);
        }
    }

    private void printPairs(List<? extends Pair<?, ?>> pairs) {
        for (Pair pair : pairs) {
            System.out.println(pair.getLeft().toString() + " - " + pair.getRight().toString());
        }
    }
    class NumberPair<L extends Number, R extends Number> extends Pair<L, R> {
        public NumberPair(L left, R right) {
            super(left, right);
        }

    }
    class Pair<L, R> {
        final L left;

        final R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }
        public R getRight() {
            return right;
        }

    }
}
