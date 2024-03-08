/* 
File: Perceptron.java
Authors: Kenny Collins, Anthony Rojas, Scott Schnieders, Rakan Al rasheed
Date: 3/2/2024
Description: Responsible for training and testing the perceptron. Utilizes FileHandler for modularity.
*/

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;

public class perceptron {
    Random random = new Random();
    FileHandler fileHandler = new FileHandler();

    int numEpochs = 0;
    public ArrayList<double[]> weights; // A 2d array where the arraylist is for each output node and the array is the weights
    public ArrayList<Double> biases;
    public boolean stoppingCondition;
    int numDimensions;
    int outputSize;
    int numPairs;
    long duration;

    // Train method: called from main class
    public void train(String trainingDataFile, int weightInit, int maxEpochs, String weightSettingsFile, double alpha,
            double theta, double threshold) throws FileNotFoundException {
        FileHandler.InputData inputData = fileHandler.readInputData(trainingDataFile, fileHandler.trainPath);
        numDimensions = inputData.numDimensions;
        outputSize = inputData.outputSize;
        numPairs = inputData.numPairs;
        weights = new ArrayList<>(outputSize);
        biases = new ArrayList<>(outputSize);
        initializeWeightsAndBiases(weightInit);
        stoppingCondition = false;
        ArrayList<String> charList = inputData.charList;
        ArrayList<ArrayList<Integer>> trainingSet = inputData.trainingSet;
        ArrayList<ArrayList<Integer>> targetSet = inputData.targetSet;

        // Logging Time for Validating hyperparameters
        long startTime = System.nanoTime();
        int skipped = 0;
        while (!stoppingCondition && numEpochs < maxEpochs) {
            numEpochs++;
            stoppingCondition = true;

            for (int k = 0; k < trainingSet.size(); k++) {
                ArrayList<Integer> input = trainingSet.get(k);
                ArrayList<Integer> targets = targetSet.get(k);  // Get the target vector for this input
            
                //  set activation of each input unit
                ArrayList<Integer> activations = new ArrayList<>(input);
            
                // compute activation of each output unit
                for (int j = 0; j < outputSize; j++) {
                    double y_in_j = biases.get(j);
            
                    for (int i = 0; i < weights.get(j).length; i++) {
                        y_in_j += activations.get(i) * weights.get(j)[i];
                    }
            
                    // activation function
                    int y_j;
                    if (y_in_j > theta) {
                        y_j = 1;
                    } else if (-theta <= y_in_j && y_in_j <= theta) {
                        y_j = 0;
                    } else {
                        y_j = -1;
                    }
            
                    // update biases and weights
                    int target = targets.get(j);  // Get the target for this output unit
                    if (Math.abs(target - y_j) > threshold) {
                        biases.set(j, biases.get(j) + target);
                        for (int i = 0; i < weights.get(j).length; i++) {
                            weights.get(j)[i] += (alpha * target * activations.get(i));
                        }
                        stoppingCondition = false;  
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        duration = (endTime - startTime);
    }

    public void SaveWeights(String weightSettingsFile) {
        try {
            String path = "weights/" + weightSettingsFile;
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println(numDimensions + " " + outputSize + " " + numPairs);
            for (int j = 0; j < outputSize; j++) {
                //writer.println(weights.get(j).toString().replace("[", "").replace("]", "").replace(",", ""));
                for (int i = 0; i < numDimensions; i++) {
                    writer.print(weights.get(j)[i] + " ");
                }
                writer.print("\n");
            }
            writer.println(biases.toString().replace("[", "").replace("]", "").replace(",", ""));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test(String testingDataFile, String resultsFile, double theta) throws FileNotFoundException, IOException {
        // Read testing data
        FileHandler.InputData inputData = fileHandler.readInputData(testingDataFile, fileHandler.testPath);
        ArrayList<ArrayList<Integer>> testSet = inputData.trainingSet;
        ArrayList<ArrayList<Integer>> targetSet = inputData.targetSet;
        ArrayList<String> charList = inputData.charList;

        if(numDimensions == inputData.numDimensions && outputSize == inputData.outputSize) {
            // Pass resultsFile to the testing method
            testPerceptron(testSet, targetSet, theta, resultsFile, charList);
        } else {
            throw new IOException("Dimensions don't line up");
        }
    }

    public void testPerceptron(ArrayList<ArrayList<Integer>> testSet, ArrayList<ArrayList<Integer>> targetSet, double theta, String resultsFile,  ArrayList<String> charList) throws IOException {
        int correctPredictions = 0;
        int totalSamples = testSet.size();
        String path = "results/" + resultsFile;

        // Open the file writer
        PrintWriter writer = new PrintWriter(path, "UTF-8");
    
        for (int k = 0; k < totalSamples; k++) {
            ArrayList<Integer> input = testSet.get(k);
            ArrayList<Integer> targets = targetSet.get(k);
            ArrayList<Integer> output = new ArrayList<>();
    
            // Compute the activation of each output unit
            int undecidedCount = 0;
            boolean undecided = false;
            int decision = 0;
            for (int j = 0; j < outputSize; j++) {
                double y_in_j = biases.get(j);
                for (int i = 0; i < numDimensions; i++) {
                    y_in_j += input.get(i) * weights.get(j)[i];
                }
    
                // Activation function
                int y_j = (y_in_j > theta) ? 1 : (y_in_j < -theta) ? -1 : 0;
                output.add(y_j);
    
                // Check for undecided condition
                if (y_j == 1) {
                    decision = j;
                    undecidedCount += 1;  // Already found a +1, this makes it undecided
                }
            }
            if(undecidedCount > 1 || undecidedCount == 0) {
                undecided = true;
            }

            if(!undecided && (k % outputSize) == decision) {
                correctPredictions++;
            }
    
            // Write actual and classified output
            writer.println("Actual Output:");
            writer.println(charList.get(k)); // Assuming charList contains the corresponding characters
            writer.println(formatOutput(targets));
            writer.println("Classified Output:");
            if (undecided) {
                writer.println("undecided");
            } else {
                String letter = charList.get(decision).substring(0, charList.get(decision).length() - 1);
                writer.println(letter); // Repeating for classified for consistency
            }
            writer.println(formatOutput(output));
        }
    
        // Calculate accuracy
        double accuracy = (double) correctPredictions / totalSamples;
    
        // Write the accuracy to the results file
        writer.println("Accuracy: " + accuracy * 100 + "%");
    
        // Close the file writer
        writer.close();
    }
    
    private String formatOutput(ArrayList<Integer> output) {
        StringBuilder sb = new StringBuilder();
        for (int value : output) {
            sb.append(value).append(" ");
        }
        return sb.toString().trim();
    }
    
    // Get method used for UI and results
    public String getEpochs() {
        return Integer.toString(numEpochs);
    }

    // Finished Implementation
    public void initializeWeightsAndBiases(int weightInit) {
        if (weightInit == 0) {
            for (int j = 0; j < outputSize; j++) {
                biases.add(0.0);
                weights.add(new double[numDimensions]);
                for (int i = 0; i < numDimensions; i++) {
                    weights.get(j)[i] = 0.0;
                }
            }
        } else {
            for (int j = 0; j < outputSize; j++) {
                biases.add(random.nextDouble() - 0.5);
                weights.add(new double[numDimensions]);
                for (int i = 0; i < numDimensions; i++) {
                    weights.get(j)[i] = random.nextDouble() - 0.5;
                }
            }
        }
    }
    
    // Loads weights for testing
    public void loadWeights(String weightSettingsFileTest) throws IOException {
        String fullPath = fileHandler.weightPath + weightSettingsFileTest;
        File file = new File(fullPath);
        
        if (!file.exists()) {
            throw new FileNotFoundException("The file " + fullPath + " does not exist.");
        }
        
        if (file.isDirectory()) {
            throw new FileNotFoundException(fullPath + " is a directory, not a file.");
        }
    
        BufferedReader reader = new BufferedReader(new FileReader(fullPath));
    
        // Initialize or clear weights and biases lists
        weights = weights == null ? new ArrayList<>() : weights;
        biases = biases == null ? new ArrayList<>() : biases;
        weights.clear();
        biases.clear();
    
        // First line: dimensions and pairs
        String line = reader.readLine();
        if (line == null) throw new IOException("Unexpected end of file while reading dimensions.");
        String[] dimensions = line.split(" ");
        numDimensions = Integer.parseInt(dimensions[0]);
        outputSize = Integer.parseInt(dimensions[1]);
        numPairs = Integer.parseInt(dimensions[2]);
        // duration = Long.parseLong(dimensions[3]);
        if (dimensions.length < 3) throw new IOException("Dimensions line does not contain enough values.");
    
        // Assuming weights are on the next line
        for (int j = 0; j < outputSize; j++) {
            line = reader.readLine();
            if (line == null) throw new IOException("Unexpected end of file while reading weights.");
            String[] weightValues = line.trim().split(" ");
            weights.add(new double[numDimensions]);
            for (int i = 0; i < numDimensions; i++) {
                weights.get(j)[i] = Double.parseDouble(weightValues[i]);
            }
        }

        // Assuming biases are on the next line
        line = reader.readLine();
        if (line == null) throw new IOException("Unexpected end of file while reading biases.");
        String[] biasValues = line.trim().split(" ");
        for (String value : biasValues) {
            biases.add(Double.parseDouble(value));
        }
    
        reader.close();
    }
    
    
    

}
