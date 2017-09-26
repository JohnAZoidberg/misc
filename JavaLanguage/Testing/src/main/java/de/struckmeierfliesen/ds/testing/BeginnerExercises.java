package de.struckmeierfliesen.ds.testing;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BeginnerExercises {
    public BeginnerExercises() {
        /*int reversed = listsStrings1(new int[]{1, 2, 3, 4, 5});
        System.out.println(reversed);*/
        elementary18(new String[] {"Hello", "World", "in", "a", "frame"});
    }

    public void elementary1() {
        System.out.println("Hello World");
    }

    public void elementary2() {
        Scanner reader = new Scanner(System.in);
        System.out.println("What is yor name?");
        String name = reader.next();
        System.out.println("Hello " + name + "!");
    }

    public void elementary3() {
        while (true) {
            Scanner reader = new Scanner(System.in);
            System.out.println("What is yor name?");
            String name = reader.next();
            if (name.equals("Bob") || name.equals("Alice")) {
                System.out.println("Hello " + name + "!");
                break;
            } else {
                System.out.println("Only Alice and Bob are welcome!");
            }
        }
    }

    public void elementary4() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter a number!");
        int n = reader.nextInt();
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println("The result is: " + sum);
    }

    public int askForNumber() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter a number!");
        return reader.nextInt();
    }

    public void elementary5() {
        int n = askForNumber();
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        System.out.println("The result is: " + sum);
    }

    public void elementary6() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter a number!");
        int n = reader.nextInt();
        System.out.println("Do you want to compute the sum or the product of 1, ..., " + n + "? [sum, product]");
        String choice = reader.next();
        int sum = 1;
        for (int i = 2; i <= n; i++) {
            switch (choice) {
                case "sume":
                    sum += i;
                    break;
                case "product":
                    sum *= i;
                    break;
            }
        }
    }

    public void elementary7() {
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 12; j++) {
                int product = i * j;
                String padding = "";
                if (product < 10) padding += " ";
                if (product < 100) padding += " ";
                System.out.print(padding + product + " ");
            }
            System.out.println();
        }
    }

    public void elementary8() {
        ArrayList<Integer> previousNumbers = new ArrayList<>();
        System.out.println(1);
        for (int i = 2; i < 100; i++) {
            boolean prime = true;
            for (int prev : previousNumbers) {
                if (i % prev == 0) {
                    prime = false;
                }
            }
            if (prime) {
                System.out.println(i);
                previousNumbers.add(i);
            }
        }
    }

    public void elementary9() {
        Random random = new Random();
        int secretNumber = random.nextInt(101);
        int guessed = -1;

        Scanner reader = new Scanner(System.in);
        while (guessed != secretNumber) {
            if (guessed == -1) {
                System.out.println("Guess the secret number! (Between 0 and 100)");
            } else if (guessed > secretNumber) {
                System.out.println("Nope, your number is too big!");
            } else if (guessed < secretNumber) {
                System.out.println("Nope, your number is too small!");
            }
            guessed = reader.nextInt();
        }
        System.out.println("Congratulations, you have guessed the correct number!");
    }

    public void elementary10() {
        int year = 2016; // this year is a leap year
        int years = 0;
        do {
            System.out.println(year + " is a leap year!");
            year += 4;
            years++;
        } while (years < 20);
    }

    public static void elementary18(String[] msg) {
        int longest = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[i].length() > longest)
                longest = msg[i].length();
        }
        String smth = "";
        for (int j = 0; j < longest + 4; j++) {
            smth += "*";
        }
        System.out.println(smth);
        for (int i = 0; i < msg.length; i++) {
            boolean a = true;
            while(msg[i].length() < longest){
                if(a)
                    msg[i] += " ";
                else
                    msg[i] = " " + msg[i];
                a = !a;
            }
            System.out.println("* " + msg[i] + " *");
        }
        System.out.println(smth);
    }


    public int listsStrings1(int[] list) {
        if (list.length == 0) {
            throw new IllegalArgumentException("Your list must contain at least one element!");
        }
        int largest = list[0];
        for (int i : list) {
            if (i > largest) {
                largest = i;
            }
        }
        return largest;
    }

    public int listStrings1(ArrayList<Integer> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("Your list must contain at least one element!");
        }
        int max = list.remove(0);
        while (list.size() > 0) {
            int i = list.remove(0);
            if (i > max) max = i;
        }
        return max;
    }

    public int[] listsStrings2(int[] list) {
        for (int i = 0; i < list.length / 2; i++) {
            int temp = list[i];
            list[i] = list[list.length - 1 - i];
            list[list.length - 1 - i] = temp;
        }
        return list;
    }

    public ArrayList<Integer> listsStrings2(ArrayList<Integer> list) {
        for (int i = 0; i < list.size()/ 2; i++) {
            int temp = list.get(i);
            list.set(0, list.get(list.size() - 1 - i));
            list.set(list.get(list.size() - 1 - i), temp);
        }
        return list;
    }
}
