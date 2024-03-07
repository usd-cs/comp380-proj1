/* 
File: main.java
Authors: Kenny Collins, Anthony Rojas, Scott Schnieders, Rakan Al rasheed
Date: 3/2/2024
Description: This is the driver file of our program. It will prompt the user for perceptron hyperparamaters then train it.
 */

 // TODO: For the most part, this is almost done. Just need to return accuracy and finish fileHandler and perceptron classes.

import java.io.IOException;
import java.util.Scanner;

public class proj1 {
    public static void main(String[] args) throws IOException {
        // Prompt user for hyperparameters
        Scanner kb = new Scanner(System.in);
        boolean run = true;

        System.out.println("Welcome to my first neural network - A Perceptron Net!");

        while(run){
            // Prompt for training or testing
            System.out.println("Enter 1 to train using a training data file, enter 2 to use a trained weight settings data file");
            int choice = kb.nextInt();

            switch(choice){

                case 1: // Train
                    System.out.println("Enter the training data file name (add .txt extension):");
                    kb.nextLine();
                    String trainingDataFile = kb.nextLine();

                    System.out.println("Enter 0 to initialize weights to 0, enter 1 to initailize weights to random values between -0.5 and 0.5");
                    int weightInit = kb.nextInt();

                    System.out.println("Enter the maximum number of training epochs:");
                    int maxEpochs = kb.nextInt();

                    System.out.println("Enter a file name to save the trained weight settings (add .txt extension):");
                    kb.nextLine();
                    String weightSettingsFile = kb.nextLine();

                    System.out.println("Enter the learning rate alpha from 0 to 1 but not including 0:");
                    double alpha = kb.nextDouble();

                    System.out.println("Enter the threshold theta:");
                    double theta = kb.nextDouble();

                    System.out.println("Enter the threshold to be used for measuring weight changes:");
                    double threshold = kb.nextDouble();

                    // Train the perceptron
                    perceptron p = new perceptron();
                    p.train(trainingDataFile, weightInit, maxEpochs, weightSettingsFile, alpha, theta, threshold);
                    p.SaveWeights(weightSettingsFile);
                    System.out.println("Training converged after " + p.getEpochs() + " epochs. The trained weight settings have been saved to " + weightSettingsFile);

                    // Asking user if they wish to run again
                    System.out.println("Do you want to run the program again?(Y/N)");
                    kb.nextLine();
                    String rerunProgram = kb.nextLine();
                    if (!rerunProgram.equalsIgnoreCase("Y")) {
                        run = false;
                    }
                    break;
                
                    case 2: // Test
                        perceptron pTest = new perceptron();

                        System.out.println("Enter the trained weight settings data file name:");
                        kb.nextLine();
                        String weightSettingsFileTest = kb.nextLine();
                        pTest.loadWeights(weightSettingsFileTest);
                
                        System.out.println("Enter the testing data file name:");
                        // kb.nextLine(); // Consume newline left-over
                        String testingDataFile = kb.nextLine();            
                    
                        System.out.println("Enter a file name to save the testing/deploying results:");
                        String resultsFile = kb.nextLine(); // Get the filename to save the results
                    
                        // // Here, you need to specify the theta value for testing. Assuming you have a default or a way to obtain it.
                        System.out.println("Enter the threshold theta:");
                        double thetaTest = Double.parseDouble(kb.nextLine());
                        pTest.test(testingDataFile, resultsFile, thetaTest); // Call the test method
                    
                        // Asking user if they wish to run again
                        System.out.println("Do you want to run the program again?(Y/N)");
                        String rerun = kb.nextLine();
                        if (!rerun.equalsIgnoreCase("Y")) {
                            run = false;
                        }
                        break;
                
                default:
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    break;
            }


        }
        kb.close();
    }
}