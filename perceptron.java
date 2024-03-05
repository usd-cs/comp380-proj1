/* 
File: Perceptron.java
Authors: Kenny Collins, Anthony Rojas, Scott Schnieders, Rakan Al rasheed
Date: 3/2/2024
Description: Responsible for training and testing the perceptron. Utilizes FileHandler for modularity.
*/

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Perceptron {
    Random random = new Random();
    FileHandler fileHandler = new FileHandler();

    int numEpochs = 0;
    public ArrayList<Double> weights;
    public ArrayList<Double> biases;
    public boolean stoppingCondition;
    int numDimensions;
    int outputSize;
    int numPairs;
    
    public void train(String trainingDataFile, int weightInit, int maxEpochs, String weightSettingsFile, double alpha,
            double theta, double threshold) throws FileNotFoundException {
        /*
         * When i was working on passing the training data from the file,
         * I forgot I can't return multiple values in a function like python.
         * I built a static class to access the data to keep this file clean.
         * So the function below returns that class. 
         */
        FileHandler.InputData inputData = fileHandler.readInputData(trainingDataFile);
        numDimensions = inputData.numDimensions;
        outputSize = inputData.outputSize;
        numPairs = inputData.numPairs;
        weights = new ArrayList<>(numDimensions);
        biases = new ArrayList<>(outputSize);
        initializeWeightsAndBiases(weightInit);
        stoppingCondition = false;
        ArrayList<String> charList = inputData.charList;
        ArrayList<ArrayList<Integer>> trainingSet = inputData.trainingSet;
        ArrayList<ArrayList<Integer>> targetSet = inputData.targetSet;


        while (!stoppingCondition && numEpochs < maxEpochs) {
            numEpochs++;
            stoppingCondition = true;  

            for (int k = 0; k < trainingSet.size(); k++) {
                ArrayList<Integer> input = trainingSet.get(k);
                ArrayList<Integer> targets = targetSet.get(k);  // Get the target vector for this input
            
                //  set activation of each input unit
                ArrayList<Integer> activations = new ArrayList<>(input);
            
                // compute activation of each output unit
                for (int j = 0; j < biases.size(); j++) {
                    double y_in_j = biases.get(j);
            
                    for (int i = 0; i < weights.size(); i++) {
                        y_in_j += activations.get(i) * weights.get(i);
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
                        for (int i = 0; i < weights.size(); i++) {
                            weights.set(i, weights.get(i) + alpha * (target - y_j) * activations.get(i));
                        }
                        stoppingCondition = false;  
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void SaveWeights(String weightSettingsFile) {
        // TODO
    }
    public void test(String testingDataFile, String resultsFile) {
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }

    public String getEpochs() {
        return Integer.toString(numEpochs);
    }

    // Finished Implementation
    public void initializeWeightsAndBiases(int weightInit) {
        if (weightInit == 0) {
            for (int i = 0; i < numDimensions; i++) {
                weights.add(0.0);
            }
            for (int j = 0; j < outputSize; j++) {
                biases.add(0.0);
            }
        } else {
            for (int i = 0; i < numDimensions; i++) {
                weights.add(random.nextDouble() - 0.5);
            }
            for (int j = 0; j < outputSize; j++) {
                biases.add(random.nextDouble() - 0.5);
            }
        }
    }
    

    public void loadWeights(String testingDataFile) {
        throw new UnsupportedOperationException("Unimplemented method 'loadWeights'");
    }

}
