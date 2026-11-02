1. Data Preprocessing
Meaning: Cleaning the dataset and preparing it for machine learning.

In our code:
ds = pd.read_csv("uber.csv")ds.dropna(inplace=True)

- read_csv() loads the dataset.
- dropna() removes rows containing missing values.
- inplace=True applies changes directly to the dataset.
We also remove unnecessary columns:
ds.drop(columns=["Unnamed: 0", "key"], errors="ignore", inplace=True)

These columns are not useful for predicting fares.

Extract pickup hour:
ds["pickup_datetime"] = pd.to_datetime(    ds["pickup_datetime"], utc=True)ds["hour"] = ds["pickup_datetime"].dt.hour

We convert pickup time into datetime format and extract the hour because ride demand and fares may vary at different times.


2. Identify Outliers
Meaning: Outliers are unusually high or low values compared to the rest of the dataset.
Example: If most Uber fares are between $5 and $50, but one fare is $500, it may be an outlier.

We use the IQR (Interquartile Range) method.

IQR measures the spread of the middle 50% of the data.
IQR=Q3-Q1
- Q1 = 25th percentile.
- Q3 = 75th percentile.
Outlier boundaries:
Lower=Q1-1.5(IQR)
Upper=Q3+1.5(IQR)

In our code:
Q1 = ds["fare_amount"].quantile(0.25)Q3 = ds["fare_amount"].quantile(0.75)IQR = Q3 - Q1

We calculate the boundaries and remove fares outside them.


3. Check Correlation
Meaning: Correlation tells us how strongly two numerical variables are related.

There are three types:
- Positive: Both variables increase together.
- Negative: One increases while the other decreases.
- Zero: No linear relationship.
Correlation ranges from -1 to +1.

In our code:
print(ds.corr(numeric_only=True)["fare_amount"])

This calculates the correlation of each numerical feature with fare_amount.

For example, if distance and fare have a strong positive correlation, longer rides generally have higher fares.


4. Train-Test Split
Meaning: We divide the dataset into two parts.
- Training data (80%): Used to train the model.
- Testing data (20%): Used to evaluate the model.
In our code:
X = ds.drop("fare_amount", axis=1)y = ds["fare_amount"]

- X contains input features such as coordinates, passenger count and hour.
- y contains the actual fare, which we want to predict.
X_train, X_test, y_train, y_test = train_test_split(    X, y, test_size=0.2, random_state=42)
random_state=42 ensures that we get the same train-test split every time.


5. Feature Scaling
Meaning: Converting numerical features into a similar scale so that differences in their ranges do not unnecessarily affect the model.

We use StandardScaler.
Formula:
Z=\frac{X-Mean}{StandardDeviation}

In our code:
sc = StandardScaler()
X_train_scaled = sc.fit_transform(X_train)
X_test_scaled = sc.transform(X_test)



- fit_transform() calculates the mean and standard deviation from training data and scales it.
- transform() applies the same scaling to testing data.
We use scaling for Linear Regression in this implementation. Random Forest generally does not require it.
6. Linear Regression
Meaning: Linear Regression is a supervised machine learning algorithm used to predict continuous numerical values.

It finds a mathematical relationship between input features and the output.

In our code:
lr = LinearRegression()
lr.fit(X_train_scaled, y_train)
lr_pred = lr.predict(X_test_scaled)

- LinearRegression() creates the model.
- fit() trains the model using historical data.
- predict() predicts fares using testing data.


7. Random Forest Regression
Meaning: Random Forest is a machine learning algorithm that combines multiple Decision Trees to make predictions.

How it works:
1. Creates multiple Decision Trees.
2. Each tree learns patterns from the data.
3. Every tree predicts a fare.
4. The algorithm averages their predictions.
In our code:
rf = RandomForestRegressor(    n_estimators=100,    random_state=42)rf.fit(X_train, y_train)rf_pred = rf.predict(X_test)



- n_estimators=100 means we create 100 Decision Trees.
- fit() trains the model.
- predict() generates fare predictions.
8. Model Evaluation
After training both models, we compare their performance using two metrics.

A. R² Score (Coefficient of Determination)

Measures how much variation in the actual fare is explained by the model.
- R² = 1: Perfect predictions.
- R² = 0: No improvement over predicting the mean.
- R² less than 0: Poorer than predicting the mean.
Higher R² is generally better.

B. RMSE (Root Mean Squared Error)

Measures the average magnitude of prediction errors, giving more weight to larger errors.

Formula:
\[
RMSE=\sqrt{\frac{\sum(y-\hat y)^2}{n}}
\]

Lower RMSE is better.

In our code:
from sklearn.metrics import r2_score, mean_squared_errorfor name, pred in [    ("Linear Regression", lr_pred),    ("Random Forest", rf_pred)]:    print("\n", name)    print("R2 Score:", r2_score(y_test, pred))    print("RMSE:", np.sqrt(mean_squared_error(y_test, pred)))



This calculates R² and RMSE for both models.