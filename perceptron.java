<<<<<<< HEAD
import java.util.ArrayList;

public class Perceptron {
    public ArrayList<Double> weights;
    public ArrayList<Double> biases;
    public boolean stoppingCondition;

    public Perceptron(int inputSize, int outputSize) {
        weights = new ArrayList<>(inputSize);
        biases = new ArrayList<>(outputSize);
        initializeWeightsAndBiases();
        stoppingCondition = false;
    }

    public void initializeWeightsAndBiases() {
        for (int j = 0; j < biases.size(); j++) {
            biases.add(0.0);
        }
    }

    public void train(ArrayList<ArrayList<Integer> trainingSet, ArrayList<Integer> targetSet, double theta) {
        while (!stoppingCondition) {
            stoppingCondition = true;  

            for (int k = 0; k < trainingSet.size(); k++) {
                ArrayList<Integer> input = trainingSet.get(k);
                int target = targetSet.get(k);

                //  set activation of each input unit
                ArrayList<Double> activations = new ArrayList<>(input);

                // compute activation of each output unit
                for (int j = 0; j < biases.size(); j++) {
                    double y_in_j = biases.get(j);

                    for (int i = 0; i < weights.size(); i++) {
                        y_in_j += input.get(i) * weights.get(i);
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
                    if (target != y_j) {
                        biases.set(j, biases.get(j) + target);
                        for (int i = 0; i < weights.size(); i++) {
                            weights.set(i, weights.get(i) + target * input.get(i));
                        }
                        stoppingCondition = false;  
                    }
                }
            }
        }
    }
}
=======
public class perceptron {

    public void train(String trainingDataFile, int weightInit, int maxEpochs, String weightSettingsFile, double alpha,
            double theta, double threshold) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'train'");
    }

    public String getEpochs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEpochs'");
    }

    public void test(String testingDataFile, String resultsFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }

    public void loadWeights(String testingDataFile) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadWeights'");
    }
    
}
>>>>>>> 5d0ecaf924ee352489762b9dc7747c565f022495
