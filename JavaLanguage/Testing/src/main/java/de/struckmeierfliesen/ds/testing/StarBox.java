package de.struckmeierfliesen.ds.testing;

import java.util.ArrayList;

public class StarBox {
    public static void star(ArrayList<String> words) {
        String border = new String(new char[getLongestWord((ArrayList<String>) words.clone()).length() + 4]).replace("\0", "*");
        System.out.println(border);
        starLn(words);
        System.out.println(border);
    }

    private static void starLn(ArrayList<String> words) {
        System.out.println("* " + words.get(0)+ new String(new char[getLongestWord((ArrayList<String>) words.clone()).length() - words.get(0).length()]).replace("\0", " ") + " *");
        if (words.size() > 1) {
            words.remove(0);
            starLn(words);
        }
    }

    private static String getLongestWord(ArrayList<String> words) {
        if (words.size() == 1) {
            return words.get(0);
        } else {
            words.remove(0);
            String longestInTail = getLongestWord(words);
            if (words.get(0).length() > longestInTail.length()) {
                return words.get(0);
            } else {
                return longestInTail;
            }
        }
    }
}
