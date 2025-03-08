package cz.cuni.mff.java.hw.hashtable;


import java.util.Scanner;



public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        HashTable wordFrequency = new HashTable();


        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            String[] words = line.split("\\s+");


            for (String word : words) {

                if (!word.isEmpty()) {

                    Integer count = (Integer) wordFrequency.get(word);

                    wordFrequency.set(word, count == null ? 1 : count + 1);

                }

            }

        }


        for (String word : wordFrequency) {

            System.out.println(word + ": " + wordFrequency.get(word));

        }


        scanner.close();

    }

}
