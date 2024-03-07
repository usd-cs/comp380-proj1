/* 
File: FileHandler.java
Authors: Kenny Collins, Anthony Rojas, Scott Schnieders, Rakan Al rasheed
Date: 3/2/2024
Description: This file is responsible for reading train/test files, writing results, and loading weights from file.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    String trainPath = "train/";
    String testPath = "test/";
    String weightPath = "weights/";
    String resultPath = "results/";

    // read input data from file, can be used for training or testing
    public InputData readInputData(String filePath, String directory) throws FileNotFoundException {
        File trainData = new File(directory + filePath);
        Scanner kb = new Scanner(trainData);

        int numDimensions = kb.nextInt();
        int outputSize = kb.nextInt();
        int numPairs = kb.nextInt();
        ArrayList<String> charList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> trainingSet = new ArrayList<>();
        ArrayList<ArrayList<Integer>> targetSet = new ArrayList<>();

        // looping through data pairs
        for (int i = 0; i < numPairs; i++) {
            ArrayList<Integer> target = new ArrayList<>();
            ArrayList<Integer> training = new ArrayList<>();

            // training data
            for (int j = 0; j < numDimensions; j++) {
                int value = kb.nextInt();
                training.add(value);
            }
            trainingSet.add(training);

            // target data
            for (int j = 0; j < outputSize; j++) {
                int value = kb.nextInt();
                target.add(value);
            }
            targetSet.add(target);

            // read characters for debugging
            String character = kb.next();
            charList.add(character);
        }

        kb.close();

        // returning multiple values

    
        return new InputData(numDimensions, outputSize, numPairs, charList, trainingSet, targetSet);
    }

    // Save and append to csv file for analysis
    public void addResultsToCSV(String testingDataFile, int randomized, int numEpochs, double LearningRate, double Threshold, double Theta,
                        String TestFile, String ResultsFile, double Accuracy) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(testingDataFile).append(",");
        sb.append(randomized).append(",");
        sb.append(numEpochs).append(",");
        sb.append(LearningRate).append(",");
        sb.append(Threshold).append(",");
        sb.append(Theta).append(",");
        sb.append(TestFile).append(",");
        sb.append(ResultsFile).append(",");
        sb.append(Accuracy).append("\n");

        FileWriter pw = new FileWriter("/results/results.csv", true);
        pw.write(sb.toString());
        pw.close();
    }

    // Class to hold the data read from the input file
    static class InputData {
        int numDimensions;
        int outputSize;
        int numPairs;
        ArrayList<String> charList;
        ArrayList<ArrayList<Integer>> trainingSet;
        ArrayList<ArrayList<Integer>> targetSet;

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
