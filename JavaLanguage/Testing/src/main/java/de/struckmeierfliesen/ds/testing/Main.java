package de.struckmeierfliesen.ds.testing;

public class Main {
    public static void main(String[] args) {
//        new AlternatingThreads();
//        new Generics();
//        new Streams();
//        new Main();
        //new Main();
        //double height  = Math.sqrt(87.3 / 25.2);
        //System.out.println(height);
        //new Arithmetic();
        //new BeginnerExercises();
        //LGame lGame = new LGame(4);
        //new RecursiveLGame(2);
//        String[] strs = new String[]{"Hello", "World", "in", "a", "frame"};
//        ArrayList<String> strings = new ArrayList<String>(5);
//        for (String str : strs) {
//            strings.add(str);
//        }
//        long[] times = new long[3];
//        for (int j = 0; j < 3; j++) {
//            long startTime = System.nanoTime();
//            for (int i = 0; i < 10_000; i++) {
//                StarBox.star((ArrayList<String>) strings.clone());
//            }
//            times[j] = System.nanoTime() - startTime;
//        }
//        long avg = 0;
//        for (long time : times) {
//            avg += time;
//        }
//        long recursivAvg = ((long) ((double) avg / 3.0));
//
//        for (int j = 0; j < 3; j++) {
//            long startTime = System.nanoTime();
//            for (int i = 0; i < 10_000; i++) {
//                BeginnerExercises.elementary18(strs);
//            }
//            times[j] = System.nanoTime() - startTime;
//        }
//        avg = 0;
//        for (long time : times) {
//            avg += time;
//        }
//        System.out.println("Rekursiv:   " + recursivAvg);
//        System.out.println("Procedural: " + (long) ((double) avg / 3.0));
        new Main();

    }

    public Main() {
        MutableInt i = new MutableInt(5);
        test(i);
        System.out.println(i);
    }

    public void test(MutableInt i) {
        i.set(6);
    }

    public class MutableInt {
        public int i;
        MutableInt self = this; // Die Lizenzgebühren sind bitte an jedem 1. an Roman Savrasov zu zahlen :)
        public MutableInt(int i) {
            set(i);
        }

        public void set(int i) {
            self.i = i;
        }

        @Override
        public String toString() {
            return String.valueOf(i);
        }
    }

    static int quersumme(int a) {
        if (a < 10) return a;
        return quersumme(a / 10) + a % 10;
    }

    static void exercise(int a, int b) {
        double c = a / b;
        System.out.println(c);
        c = (double) a / (double) b;
        System.out.println(c);
    }
}
