# Priority

1. Figure out parsing the data
2. Create Data!
3. Build perceptron
4. Output training data like epochs, accuracy, and hyperparameters to csv with unique training id
5. finish building main for input
6. Analyze using matplotlib
7. Write report

## Notes

- Create data
- should handle any input size we give it
- If there is no +1 or there are multiple +1 in a classified output, then the corresponding testing letter should be regarded as “undecided”.
- The system should use a user-specified threshold to determine if there are weight changes in the system training.
- Train your system by a training data set (for instance, the attached sample training set) and then use the same set as a testing set. Does the system classify the training samples correctly?
- Present your results in a table/curve, your analysis and concluding remarks.
- After training your system with the attached training set, test the ability your system (in terms of its classification accuracy, or percentage of correctly classified letters) to classify noisy versions of the training patterns.
- LNITest, which has low noise-interference (LNI) input patterns
- MNITest, which has medium noise-interference (MNI) input patterns
- HNITest, which has high noise-interference (HNI) input patterns by adding 6 additional wrong pixels wisely for each letter in LNITest
