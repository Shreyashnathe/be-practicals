Assignment 1: Email Spam Detection Using KNN and SVM
1. Aim
To build a Machine Learning model that classifies emails into two categories:
- Spam (1): Unwanted or promotional emails.
- Non-spam (0): Genuine emails.
We use two algorithms: K-Nearest Neighbors (KNN) and Support Vector Machine (SVM), and compare their performance.
2. Code explanation block by block
Block 1: Import library
import pandas as pd

- Pandas is used for data handling and analysis.
- pd is the short name (alias) for Pandas.
Block 2: Load dataset
ds = pd.read_csv("emails.csv")ds.head()ds.isnull().sum()

- read_csv() loads the CSV dataset.
- ds stores the dataset as a DataFrame.
- head() displays the first five rows.
- isnull().sum() checks the number of missing values in each column.
Important terms: DataFrame, dataset, missing values.
Block 3: Data preprocessing
ds.drop("Email No.", axis=1, inplace=True)X = ds.drop("Prediction", axis=1)y = ds["Prediction"]


- drop() removes unnecessary columns.
- axis=1 means column.
- inplace=True modifies the original dataset.
- X stores input features (word frequencies).
- y stores the target/output (spam or non-spam).
Important terms:
- Feature: Input used for prediction.
- Target: Output we want to predict.
- Preprocessing: Preparing data before training.
Block 4: Train-test split
from sklearn.model_selection import train_test_splitX_train, X_test, y_train, y_test = train_test_split(    X, y, test_size=0.2, random_state=42)


Divides the dataset into:
- 80% training data.
- 20% testing data.
random_state=42 ensures the same split every time.

Why? Training data teaches the model, while testing data checks its performance on unseen emails.
Block 5: Feature scaling
from sklearn.preprocessing import StandardScalersc = StandardScaler()X_train = sc.fit_transform(X_train)X_test = sc.transform(X_test)


Feature scaling brings numerical values to a similar scale.
- StandardScaler() creates the scaling object.
- fit_transform() calculates mean and standard deviation from training data and scales it.
- transform() scales testing data using the same values.
Formula:

Why? KNN uses distance calculations, so features with larger values should not dominate.
Block 6: KNN Classification
from sklearn.neighbors import KNeighborsClassifierknn = KNeighborsClassifier(n_neighbors=5)knn.fit(X_train, y_train)knn_pred = knn.predict(X_test)


KNN (K-Nearest Neighbors) classifies an email based on its nearest data points.

Working:
1. Calculate distances between data points.
2. Select the 5 nearest neighbors.
3. Check their classes.
4. Assign the majority class.
Important terms:
- n_neighbors=5: Considers five nearest points.
- fit(): Trains the model.
- predict(): Predicts the output.
Block 7: SVM Classification
from sklearn.svm import SVCsvm = SVC(kernel="linear")svm.fit(X_train, y_train)svm_pred = svm.predict(X_test)


SVM (Support Vector Machine) finds the best decision boundary to separate spam and non-spam emails.

Important terms:
- Hyperplane: Boundary separating two classes.
- Support vectors: Data points closest to the boundary.
- Linear kernel: Creates a linear decision boundary.
fit() trains the model, and predict() classifies the testing emails.
Block 8: Model evaluation
from sklearn.metrics import accuracy_score, classification_reportprint("KNN Accuracy:", accuracy_score(y_test, knn_pred))print(classification_report(y_test, knn_pred))print("SVM Accuracy:", accuracy_score(y_test, svm_pred))print(classification_report(y_test, svm_pred))


This block compares the performance of both models.
Metric	Meaning
Accuracy	Overall percentage of correct predictions
Precision	How many predicted spam emails were actually spam
Recall	How many actual spam emails were correctly detected
F1-score	Balance between precision and recall

3. Final results
Algorithm	Accuracy
KNN	84.54%
SVM	94.69%

Best performing model: SVM
SVM performs better because it achieves higher accuracy on this dataset.
4. Important viva questions

Q1. What is Machine Learning?
Machine Learning allows computers to learn patterns from data and make predictions.

Q2. Which type of learning is used?
Supervised learning because the dataset contains labelled outputs.

Q3. What is KNN?
KNN classifies data based on the majority class of its nearest neighbors.

Q4. What is SVM?
SVM finds an optimal boundary to separate different classes.

Q5. Why is StandardScaler used?
To bring features to a similar scale.

Q6. What is the difference between fit() and predict()?
fit() trains the model, while predict() generates predictions.

Q7. Which model gives better results?
SVM, with 94.69% accuracy.


5. Complete workflow
Load Dataset → Check Missing Values → Preprocessing → Split Data → Feature Scaling → Train KNN and SVM → Evaluate Models → Compare Results

Conclusion: We successfully implemented email spam detection using KNN and SVM. After comparing both algorithms, SVM achieved better performance with approximately 94.69% accuracy.