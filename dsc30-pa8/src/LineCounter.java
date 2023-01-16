/*
 * NAME: Zehui Zhang
 * PID: A16151490
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A line counter implementation
 * 
 * @author Zehui Zhang
 * @since 2021-02-25
 */
public class LineCounter {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 10;
    private static final int FULL_PERCENTAGE = 100;

    /**
     * Method to print the filename to the console
     * 
     * @param filename filename to print
     */
    public static void printFileName(String filename) {
        System.out.println("\n" + filename + ":");
    }

    /**
     * Method to print the statistics to the console
     * 
     * @param compareFileName name of the file being compared
     * @param percentage      similarity percentage
     */
    public static void printStatistics(String compareFileName, int percentage) {
        System.out.println(percentage + "% of lines are also in " + compareFileName);
    }

    /**
     * Main method.
     * 
     * @param args names of the files to compare
     */
    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Invalid number of arguments passed");
            return;
        }

        int numArgs = args.length;

        // Create a hash table for every file
        HashTable[] tableList = new HashTable[numArgs];

        // Preprocessing: Read every file and create a HashTable

        for (int i = 0; i < numArgs; i++) {
            HashTable h = readFile(args[i]);
            tableList[i] = h;
        }

        // Find similarities across files
        for (int i = 0; i < numArgs; i++) {
            printFileName(args[i]);
            for (int j = 0; j < numArgs; j++) {
                if (i != j) {
                    int totalLine = 0;
                    int overlap = 0;
                    File file = new File(args[i]);
                    Scanner scanner;
                    try {
                        // scan through the whole file and compare
                        scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (tableList[j].lookup(line)) {
                                overlap++;
                            }
                            totalLine++;
                        }
                        scanner.close();
                        // compute and print stats
                        int percentage = overlap * FULL_PERCENTAGE / totalLine; 
                        printStatistics(args[j], percentage);
                    } catch (FileNotFoundException e) {
                        //Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            
        }
    }
    /**
     * read file helper function
     * 
     * @param filename
     * @return a hash table populated with given file
     */
    public static HashTable readFile(String fileName) {
        HashTable h = new HashTable(MIN_INIT_CAPACITY);
        File file = new File(fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                h.insert(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            //Auto-generated catch block
            e.printStackTrace();
        }
        return h;
    }
}
