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
    public void train(String trainingDataFile, int weightInit, int maxEpochs, String weightSettingsFile, double alpha,
            double theta, double threshold) throws FileNotFoundException {
        /*
         * When i was working on passing the training data from the file,
         * I forgot I can't return multiple values in a function like python.
         * I built a static class to access the data to keep this file clean.
         * So the function below returns that class. 
         */
        FileHandler.InputData inputData = fileHandler.readInputData(trainingDataFile);
        int numDimensions = inputData.numDimensions;
        int outputSize = inputData.outputSize;
        int numPairs = inputData.numPairs;
        ArrayList<String> charList = inputData.charList;
        ArrayList<ArrayList<Integer>> trainingSet = inputData.trainingSet;
        ArrayList<ArrayList<Integer>> targetSet = inputData.targetSet;
        

        
    }

    public String getEpochs() {
        throw new UnsupportedOperationException("Unimplemented method 'getEpochs'");
    }

    public void test(String testingDataFile, String resultsFile) {
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }

    public void loadWeights(String testingDataFile) {
        throw new UnsupportedOperationException("Unimplemented method 'loadWeights'");
    }
    
}


// public class Perceptron {
//     public ArrayList<Double> weights;
//     public ArrayList<Double> biases;
//     public boolean stoppingCondition;

//     public Perceptron(int inputSize, int outputSize) {
//         weights = new ArrayList<>(inputSize);
//         biases = new ArrayList<>(outputSize);
//         initializeWeightsAndBiases();
//         stoppingCondition = false;
//     }

//     public void initializeWeightsAndBiases() {
//         for (int j = 0; j < biases.size(); j++) {
//             biases.add(0.0);
//         }
//     }

//     public void train(ArrayList<ArrayList<Integer> trainingSet, ArrayList<Integer> targetSet, double theta) {
//         while (!stoppingCondition) {
//             stoppingCondition = true;  

//             for (int k = 0; k < trainingSet.size(); k++) {
//                 ArrayList<Integer> input = trainingSet.get(k);
//                 int target = targetSet.get(k);

//                 //  set activation of each input unit
//                 ArrayList<Double> activations = new ArrayList<>(input);

//                 // compute activation of each output unit
//                 for (int j = 0; j < biases.size(); j++) {
//                     double y_in_j = biases.get(j);

//                     for (int i = 0; i < weights.size(); i++) {
//                         y_in_j += input.get(i) * weights.get(i);
//                     }

//                     // activation function
//                     int y_j;
//                     if (y_in_j > theta) {
//                         y_j = 1;
//                     } else if (-theta <= y_in_j && y_in_j <= theta) {
//                         y_j = 0;
//                     } else {
//                         y_j = -1;
//                     }

//                     // update biases and weights
//                     if (target != y_j) {
//                         biases.set(j, biases.get(j) + target);
//                         for (int i = 0; i < weights.size(); i++) {
//                             weights.set(i, weights.get(i) + target * input.get(i));
//                         }
//                         stoppingCondition = false;  
//                     }
//                 }
//             }
//         }
//     }
// }



