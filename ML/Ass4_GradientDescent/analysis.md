Assignment: Implement Gradient Descent Algorithm
1. Aim
To implement the Gradient Descent Algorithm to find the local minimum of a function.

In this assignment, we find the minimum value of: y=(x+3)^2

Starting from \(x=2\).
2. Explanation of the code
Block 1: Import library
import matplotlib.pyplot as plt



- matplotlib.pyplot is used to draw graphs.
- plt is the short name used to access its functions.
Block 2: Define the function
def f(x):    return (x+3)**2



- Defines the mathematical function \(y=(x+3)^2\).
- **2 means square.
- The minimum value of this function occurs at \(x=-3\), where \(y=0\).
Block 3: Define the derivative
def derivative(x):    return 2*(x+3)



- Calculates the derivative of the function.
- The derivative tells us the slope of the function at a particular point.
- Gradient Descent uses this slope to decide which direction to move.
Block 4: Initialize values
x = 2learning_rate = 0.1iterations = 50points = [x]



- x = 2: Starting point of the algorithm.
- learning_rate = 0.1: Controls the size of each step.
- iterations = 50: Algorithm runs for 50 iterations.
- points: Stores the values of x at every step.
Block 5: Apply Gradient Descent
for i in range(iterations):    x = x - learning_rate * derivative(x)    points.append(x)



This is the main part of the algorithm.

The Gradient Descent formula is:
\[
x_{\text{new}}=x_{\text{old}}-\alpha f'(x)
\]

Where:
- \(x\) = current position.
- \(\alpha\) = learning rate.
- \(f'(x)\) = derivative of the function.
The loop repeatedly updates x and moves it towards the minimum value. Each new x value is stored in points.
Block 6: Generate graph values
x_values = range(-6, 4)y_values = [f(i) for i in x_values]


- Creates x values from -6 to 3.
- Calculates the corresponding y values using the function.
- These values are used to draw the curve.
Block 7: Plot the graph
plt.plot(x_values, y_values)plt.scatter(points, [f(i) for i in points], color="red")plt.scatter(points[0], f(points[0]), color="green")plt.scatter(points[-1], f(points[-1]), color="blue")plt.xlabel("x")plt.ylabel("y")plt.title("Gradient Descent Function")plt.grid()plt.show()

- plt.plot() draws the function curve.
- Red points show the steps taken by Gradient Descent.
- Green point represents the starting point.
- Blue point represents the final point.
- xlabel() and ylabel() name the axes.
- title() adds the graph title.
- grid() displays grid lines.
- show() displays the graph.

3. Expected result

datascience.stackexchange.com

The algorithm starts at \(x=2\) and gradually moves towards \(x=-3\).
- Minimum x value: approximately -3
- Minimum y value: approximately 0
After 50 iterations, the algorithm gets very close to the minimum.


4. Important viva questions

Q1. What is Gradient Descent?
Gradient Descent is an optimization algorithm used to find the minimum value of a function by repeatedly updating its parameters.

Q2. What is the learning rate?
It determines how large a step the algorithm takes during each update.

Q3. What happens if the learning rate is too high?
The algorithm may overshoot the minimum and fail to converge.

Q4. What is a derivative?
A derivative represents the slope or rate of change of a function.

Q5. What is the purpose of iterations?
Iterations determine how many times the algorithm updates the value of x.

Q6. What is the minimum point in this assignment?
For \(y=(x+3)^2\), the minimum point is \((-3,0)\).

Q7. Why do we subtract the derivative?
We subtract it to move in the direction of decreasing function values.


5. Algorithm workflow
1. Define the function and its derivative.
2. Initialize starting x, learning rate, and iterations.
3. Calculate the derivative.
4. Update x using the Gradient Descent formula.
5. Repeat until the iterations are completed.
6. Plot the function and the optimization steps.
6. Conclusion
Gradient Descent successfully moves from the starting point \(x=2\) towards the minimum point \(x=-3\). It is widely used in Machine Learning to minimize loss or cost functions during model training.