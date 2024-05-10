/**
 *
 * @author Robert McVey
 * this program creates and loops the ladder word game 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LadderGame {

    public static void main(String[] args) throws IOException {
        // as it reads this variable will be used to break the main while loop
        boolean break_condition = false;
        // a text determiner for a loop
        String continuity;

        // the graph to be used
        StringGraph g = new StringGraph();

        // an empty array list to populate with the words
        ArrayList<String> word_list = new ArrayList<String>();

        // reads in the txt file required for the program to function and then adds the
        // words to the word_list arrraylist
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                word_list.add(line);
            }
        }

        // increments through each item in the "word_list" and adds it to the graph
        for (int i = 0; i < word_list.size(); i++) {
            g.addVertex(word_list.get(i));
        }

        // adds the subsequent edges to adjacent words
        // Adds subsequent edges to adjacent words
        for (int i = 0; i < word_list.size(); i++) {
            if (i != word_list.size() - 1) {
                for (int j = 0; j < word_list.size() - 1; j++) { // Increment j here
                    if (differenceBy1(word_list.get(i), word_list.get(j)) && !word_list.get(i).equals(word_list.get(j)) ) {
                        if (!g.edgeExists(word_list.get(i), word_list.get(j))) {
                            g.addEdge(word_list.get(i), word_list.get(j));
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(g.getEdges()));

        // the core while loop where user interaction will start
        while (break_condition == false) {
            // prompt user and then check input to see if it is in valid format
            Scanner keyboard = new Scanner(System.in);
            System.out.println("=================================");
            System.out.println("Enter the 1st five letter Word");
            String word_1 = keyboard.nextLine();

            // the format was found to be invalid force rentry from user
            while (word_1.length() != 5 || isAlpha(word_1) == false) {
                System.out.println("=================================");
                System.out.println("That was not a 5 letter word");
                System.out.println("Enter the 1st five letter Word");
                word_1 = keyboard.nextLine();
                System.out.println("=================================");

            }
            // window formating
            System.out.println("=================================");
            System.out.println("=================================");

            System.out.println("Enter the 2nd five letter Word");
            String word_2 = keyboard.nextLine();

            // the format was found to be invalid force rentry from user
            while (word_2.length() != 5 || isAlpha(word_2) == false) {
                System.out.println("=================================");
                System.out.println("That was not a 5 letter word");
                System.out.println("Enter the 2nd five letter Word");
                word_2 = keyboard.nextLine();
                System.out.println("=================================");
            }
            System.out.println("=================================");
            // convert to lowercase to force fix mismatch cases
            word_1 = word_1.toLowerCase();
            word_2 = word_2.toLowerCase();

            // word 1 is the same as word 2
            if (word_1.equals(word_2)) {
                System.out.println("there is no lader from a word to itself");
                // prompt user to continue
                System.out.println("=================================");
                System.out.println("Do you wish to continue? y/n");
                continuity = keyboard.nextLine();
                while (!continuity.equals("n") && !continuity.equals("y")) {
                    System.out.println("=================================");
                    System.out.println("invalid input detected please refer to the letters y or n");
                    System.out.println("Do you wish to continue? y/n");
                    continuity = keyboard.nextLine();

                }
                // this breaks the while loop and ends the program
                if (continuity.equals("n")) {
                    break_condition = true;
                }
                continue;
            }

            // determine and print out the word ladder
            String[] result = g.shortestPath(word_1, word_2);

            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }

            // prompt user to continue
            System.out.println("=================================");
            System.out.println("Do you wish to continue? y/n");
            continuity = keyboard.nextLine();
            while (!continuity.equals("n") && !continuity.equals("y")) {
                System.out.println("=================================");
                System.out.println("invalid input detected please refer to the letters y or n");
                System.out.println("Do you wish to continue? y/n");
                continuity = keyboard.nextLine();

            }
            // this breaks the while loop and ends the program
            if (continuity.equals("n")) {
                System.out.println("=================================");
                System.out.println("Exited");
                System.out.println("=================================");
             
             
                break_condition = true;
            }
        }
    }

    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean differenceBy1(String root, String traversalWord) {
        if (root == null || traversalWord == null) {
            throw new IllegalArgumentException("Input strings cannot be null.");
        }
    
        // Check if the lengths differ by more than 1
        if (Math.abs(root.length() - traversalWord.length()) > 1) {
            return false;
        }
    
        int diffCount = 0;
        int minLength = Math.min(root.length(), traversalWord.length());
    
        for (int i = 0; i < minLength; i++) {
            if (root.charAt(i) != traversalWord.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false; // More than one differing character
                }
            }
        }
    
        // If the lengths are equal, check if the last character differs
        if (root.length() == traversalWord.length()) {
            return diffCount == 1;
        }
    
        // If one string is longer by 1 character, it's still valid
        return true;
    }

}
