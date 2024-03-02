/* 
File: main.java
Authors: Kenny Collins, Anthony Rojas, Scott Schnieders, Rakan Al rasheed
Date: 3/2/2024
Description: This is the driver file of our program. It will prompt the user for perceptron hyperparamaters then train it.
 */

import java.util.Scanner;
import java.util.Random;

public class main {
    public static void mainMethod(String[] args) {
        // Prompt user for hyperparameters
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to my first neural network - A Perceptron Net!");


        // Prompt for training or testing
        System.out.println("Enter 1 to train using a training data file, enter 2 to use a trained weight settings\r\n" + //
                    "data file");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1){
            System.out.println("Enter the training data file name:");
            String trainingDataFile = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Enter 0 to initialize weights to 0, enter 1 to initailize weights to random values between -0.5 and 0.5");
            int weightInit = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter the maximum number of training epochs:");
            int maxEpochs = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter a file name to save the trained weight settings:");
            String weightSettingsFile = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Enter the learning rate alpha from 0 to 1 but not including 0:");
            double alpha = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the threshold theta:");
            double theta = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the threshold to be used for measuring weight changes:");
            double threshold = scanner.nextDouble();
            scanner.nextLine();

            scanner.close();
        }
    }
}