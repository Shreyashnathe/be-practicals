Assignment 2: Customer Churn Prediction Using Neural Network
1. Aim
To develop a Machine Learning model that predicts whether a bank customer will leave the bank or continue using its services.

Dataset: churn_modelling.csv

The target column is Exited:
- 0 – Customer stays with the bank.
- 1 – Customer leaves the bank.
Algorithm used: Artificial Neural Network using MLPClassifier.
2. Code explanation block by block
Block 1: Import library
import pandas as pd



- Pandas is used for data handling and analysis.
- pd is the alias (short name) for Pandas.
Block 2: Load dataset
ds = pd.read_csv("churn_modelling.csv")



- read_csv() loads the CSV file.
- ds stores the dataset in a Pandas DataFrame.
Important term – DataFrame: A tabular structure containing rows and columns.
Block 3: Data preprocessing
ds.drop(["RowNumber", "CustomerId", "Surname"], axis=1, inplace=True)ds["Gender"] = ds["Gender"].map({"Male": 0, "Female": 1})ds = pd.get_dummies(ds, columns=["Geography"], dtype=int)



This block cleans the dataset and converts categorical values into numerical values.
- drop() removes unnecessary columns such as customer ID and surname.
- axis=1 means column.
- inplace=True modifies the original dataset.
- map() converts Gender into numbers:
  - Male = 0
  - Female = 1
- pd.get_dummies() converts Geography into separate binary columns.
For example, Geography values such as France, Germany, and Spain become separate columns containing 0 or 1.

Important terms:
- Categorical data: Data represented by categories, such as gender or country.
- Encoding: Converting categorical values into numerical form.
- One-hot encoding: Creating separate binary columns for categories.
Block 4: Separate features and target
X = ds.drop("Exited", axis=1)y = ds["Exited"]



- X contains input features such as age, balance, gender, and credit score.
- y contains the target variable Exited.
The model uses X to predict y.

Important terms:
- Feature = Input variable.
- Target = Output to predict.
Block 5: Split dataset
from sklearn.model_selection import train_test_splitX_train, X_test, y_train, y_test = train_test_split(    X, y, test_size=0.2, random_state=42, stratify=y)



Divides the dataset into:
- 80% training data.
- 20% testing data.
Important parameters:
- test_size=0.2: Uses 20% data for testing.
- random_state=42: Makes the split reproducible.
- stratify=y: Maintains the same proportion of churn and non-churn classes in both sets.
Block 6: Normalize data
from sklearn.preprocessing import StandardScalersc = StandardScaler()X_train = sc.fit_transform(X_train)X_test = sc.transform(X_test)



StandardScaler brings numerical features to a similar scale.
- fit_transform() calculates mean and standard deviation from training data and scales it.
- transform() scales testing data using the same values.
Formula:
\[
Z=\frac{X-\text{Mean}}{\text{Standard Deviation}}
\]

Why? Neural networks work better when input features have comparable scales.
Block 7: Build Neural Network model
Main algorithm
from sklearn.neural_network import MLPClassifiermodel = MLPClassifier(    hidden_layer_sizes=(16, 8),    max_iter=300,    random_state=42)model.fit(X_train, y_train)



MLPClassifier stands for Multi-Layer Perceptron Classifier. It is a type of Artificial Neural Network used for classification.

Important parameters:
- hidden_layer_sizes=(16, 8): Creates two hidden layers. The first has 16 neurons and the second has 8 neurons.
- max_iter=300: Allows up to 300 training iterations.
- random_state=42: Makes random initialization reproducible.
- fit(): Trains the neural network using training data.
Important terms:
- Neuron: Basic processing unit of a neural network.
- Input layer: Receives input features.
- Hidden layer: Learns patterns from input data.
- Output layer: Produces the final prediction.
- Epoch/iteration: A training update cycle; here max_iter sets the maximum number of iterations.


Block 8: Prediction and evaluation
from sklearn.metrics import accuracy_score, confusion_matrixpred = model.predict(X_test)print("Accuracy:", accuracy_score(y_test, pred))print("Confusion Matrix:")print(confusion_matrix(y_test, pred))



- predict() predicts whether each customer will leave or stay.
- accuracy_score() calculates the percentage of correct predictions.
- confusion_matrix() shows correct and incorrect predictions for both classes.
Confusion matrix:
	Predicted 0	Predicted 1
Actual 0	True Negative	False Positive
Actual 1	False Negative	True Positive

- True Positive: Correctly predicted customer leaving.
- True Negative: Correctly predicted customer staying.
- False Positive: Predicted leaving, but customer stayed.
- False Negative: Predicted staying, but customer left.
3. Important viva questions
Q1. What is customer churn?

Customer churn means a customer stops using a company's or bank's services.

Q2. Which algorithm is used?

MLPClassifier, which is a Multi-Layer Perceptron neural network.

Q3. Why do we use encoding?

To convert categorical values like Male, Female, and Geography into numerical values that the model can process.

Q4. What is a hidden layer?

A layer between the input and output layers that learns patterns from data.

Q5. Why use StandardScaler?

To bring input features to a similar scale.

Q6. Why use stratify=y?

To maintain the class distribution in training and testing datasets.

Q7. What is a confusion matrix?

A table that compares actual and predicted classes to show correct and incorrect predictions.
4. Complete workflow
Load Dataset → Preprocessing → Encoding → Separate X and y → Train-Test Split → Standard Scaling → Build Neural Network → Prediction → Evaluation

Conclusion: We developed a customer churn prediction model using an Artificial Neural Network. The model learns customer patterns from historical data and predicts whether a customer is likely to leave the bank.

Note: The notebook contains the accuracy calculation and confusion matrix code, but does not include their actual output values. Therefore, no numerical accuracy is assumed here.