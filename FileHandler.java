/* 
File: FileHandler.java
Authors: Kenny Collins, Anthony Rojas, Scott Schnieders, Rakan Al rasheed
Date: 3/2/2024
Description: This file is responsible for reading train/test files, writing results, and loading weights from file.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    String trainPath = "train/";
    String testPath = "test/";

    // Method to read input data from file
    public InputData readInputData(String filePath) throws FileNotFoundException {
        // Open file for reading
        File trainData = new File(trainPath + filePath);
        Scanner kb = new Scanner(trainData);

        // Read dimensions, output size, and number of pairs
        int numDimensions = kb.nextInt();
        int outputSize = kb.nextInt();
        int numPairs = kb.nextInt();

        // Initialize ArrayLists to store data
        ArrayList<String> charList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> trainingSet = new ArrayList<>();
        ArrayList<ArrayList<Integer>> targetSet = new ArrayList<>();

        // Read pairs of data
        for (int i = 0; i < numPairs; i++) {
            // Initialize lists for training and target data
            ArrayList<Integer> target = new ArrayList<>();
            ArrayList<Integer> training = new ArrayList<>();

            // Read training data
            for (int j = 0; j < numDimensions; j++) {
                int value = kb.nextInt();
                training.add(value);
            }
            trainingSet.add(training);

            // Read target data
            for (int j = 0; j < outputSize; j++) {
                int value = kb.nextInt();
                target.add(value);
            }
            targetSet.add(target);

            // Read character (e.g., "A1")
            String character = kb.next();
            charList.add(character);
        }

        // Close scanner
        kb.close();

        // Return an object containing all the data read
        return new InputData(numDimensions, outputSize, numPairs, charList, trainingSet, targetSet);
    }

    // Method to write results to file
    public void writeResults(String filePath, ArrayList<Integer> results) {
        // TODO: Implement method to write results to file
        throw new UnsupportedOperationException("Unimplemented method 'writeResults'");
    }

    // Class to hold the data read from the input file
    static class InputData {
        int numDimensions;
        int outputSize;
        int numPairs;
        ArrayList<String> charList;
        ArrayList<ArrayList<Integer>> trainingSet;
        ArrayList<ArrayList<Integer>> targetSet;

        // Constructor
        public InputData(int numDimensions, int outputSize, int numPairs,
                         ArrayList<String> charList, ArrayList<ArrayList<Integer>> trainingSet,
                         ArrayList<ArrayList<Integer>> targetSet) {
            this.numDimensions = numDimensions;
            this.outputSize = outputSize;
            this.numPairs = numPairs;
            this.charList = charList;
            this.trainingSet = trainingSet;
            this.targetSet = targetSet;
        }
    }
}
