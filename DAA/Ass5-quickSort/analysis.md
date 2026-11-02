1. What is Quick Sort?
Quick Sort is a sorting algorithm based on the Divide and Conquer technique.

It works by:
1. Selecting an element called the pivot.
2. Placing the pivot in its correct sorted position.
3. Moving smaller elements to the left of the pivot.
4. Moving greater elements to the right of the pivot.
5. Repeating the same process for the left and right subarrays.
For example:
Original Array:
[8, 3, 1, 7, 0, 10, 2]

Choose pivot = 2

After partition:
[1, 0, 2, 7, 3, 10, 8]
       ↑
   Pivot at correct position


Now we separately sort the left and right parts.

2. What are Deterministic and Randomized Quick Sort?
Your program implements two variants of Quick Sort.
A. Deterministic Quick Sort
In deterministic Quick Sort, we always select the last element as the pivot.
int pivot = a[high];


For example:
Array: [5, 3, 8, 2, 7]

Pivot = 7 (last element)


The pivot selection is fixed and predictable.

Disadvantage: If the array is already sorted or reverse sorted, choosing the last element can produce highly unbalanced partitions.
B. Randomized Quick Sort
In randomized Quick Sort, we randomly select a pivot from the array.
int randomIndex = low + random.nextInt(high - low + 1);


For example:
Array: [5, 3, 8, 2, 7]

Random pivot = 3


The selected random element is swapped with the last element, and then the same partition method is used.

Advantage: Random pivot selection reduces the likelihood of repeatedly getting highly unbalanced partitions.

3. Complete Code Explanation
Part 1: Import packages and Random object
import java.util.*;

public class QuickSortAnalysis {
    static Random random = new Random();


- java.util.* imports utility classes such as Scanner and Arrays.
- Random is used to generate a random pivot index.
- static Random random creates one Random object that can be used by all methods.
Part 2: Partition method
static int partition(int[] a, int low, int high) {
    int pivot = a[high], i = low - 1;


This method places the pivot at its correct position in the array.

Parameters:
- a: Array to be sorted.
- low: Starting index.
- high: Ending index.
Here:
int pivot = a[high];


Selects the last element as the pivot.
int i = low - 1;


i tracks the position of the last element that is smaller than or equal to the pivot.

Next, the loop:
for (int j = low; j < high; j++) {
    if (a[j] <= pivot) {
        i++;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}


- j traverses the array from low to high - 1.
- If the current element is smaller than or equal to the pivot, increment i.
- Swap a[i] and a[j].
This moves smaller elements to the left side.

Finally, place the pivot:
int temp = a[i + 1];
a[i + 1] = a[high];
a[high] = temp;

return i + 1;


We swap the pivot with the element at i + 1.

Now the pivot is in its correct sorted position.

The method returns the pivot's index.
Example of partition
Consider:
Array = [5, 3, 8, 2, 7]
Pivot = 7


After partition:
[5, 3, 2, 7, 8]
         ↑
       Pivot


All elements on the left are smaller than or equal to 7, and all elements on the right are greater than 7.

Part 3: Deterministic Quick Sort
static void deterministicQuickSort(int[] a, int low, int high) {
    if (low < high) {
        int p = partition(a, low, high);

        deterministicQuickSort(a, low, p - 1);
        deterministicQuickSort(a, p + 1, high);
    }
}


This method implements deterministic Quick Sort.

Step 1: Base condition
if (low < high)


Checks whether the current subarray contains at least two elements.

If it contains zero or one element, it is already sorted.

Step 2: Partition the array
int p = partition(a, low, high);


Calls the partition method and gets the correct position of the pivot.

Step 3: Sort the left part
deterministicQuickSort(a, low, p - 1);


Recursively sorts all elements before the pivot.

Step 4: Sort the right part
deterministicQuickSort(a, p + 1, high);


Recursively sorts all elements after the pivot.

The pivot is excluded from both recursive calls because it is already in its correct position.

Part 4: Randomized Quick Sort
static void randomizedQuickSort(int[] a, int low, int high) {
    if (low < high) {


Similar to deterministic Quick Sort, but the pivot is selected randomly.

Step 1: Generate a random index
int randomIndex = low + random.nextInt(high - low + 1);


This generates an index between low and high, including both.

For example:
low = 0
high = 4

Possible random indexes = 0, 1, 2, 3, 4


Step 2: Swap random element with last element
int temp = a[randomIndex];
a[randomIndex] = a[high];
a[high] = temp;


Our partition method always selects the last element as the pivot.

Therefore, we move the randomly selected element to the last position.

Step 3: Partition the array
int p = partition(a, low, high);


Now the partition method uses the randomly selected element as the pivot.

Step 4: Recursively sort both parts
randomizedQuickSort(a, low, p - 1);
randomizedQuickSort(a, p + 1, high);


Sorts the left and right subarrays recursively.

Part 5: Main method
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int choice;


Creates a Scanner object to accept user input.

Menu:
do {
    System.out.println("\n1. Compare deterministic and randomized Quick Sort\n0. Exit");
    System.out.print("Enter choice: ");
    choice = sc.nextInt();


Displays the menu repeatedly until the user chooses 0.

Case 1:
System.out.print("Enter number of elements: ");
int n = sc.nextInt();


Accepts the number of array elements.
if (n <= 0) {
    System.out.println("Invalid number.");
    break;
}


Checks whether the input size is valid.

Take array input:
int[] original = new int[n];

System.out.println("Enter elements:");

for (int i = 0; i < n; i++)
    original[i] = sc.nextInt();


Stores the input elements in the original array.

Create two copies:
int[] a = Arrays.copyOf(original, n);
int[] b = Arrays.copyOf(original, n);


This is important.
- a is used for deterministic Quick Sort.
- b is used for randomized Quick Sort.
Both algorithms receive the same original input.

We create separate copies because sorting changes the original array.

Measure deterministic execution time:
long start = System.nanoTime();

deterministicQuickSort(a, 0, n - 1);

long deterministicTime = System.nanoTime() - start;


- System.nanoTime() records the starting time.
- The deterministic algorithm sorts the array.
- We subtract the starting time from the ending time to calculate execution time in nanoseconds.
Measure randomized execution time:
start = System.nanoTime();

randomizedQuickSort(b, 0, n - 1);

long randomizedTime = System.nanoTime() - start;


Similarly, we calculate the execution time of randomized Quick Sort.

Display results:
System.out.println("Deterministic sorted: " + Arrays.toString(a));
System.out.println("Randomized sorted:   " + Arrays.toString(b));

System.out.println("Deterministic time (ns): " + deterministicTime);
System.out.println("Randomized time (ns):   " + randomizedTime);


Prints both sorted arrays and their execution times.

Other cases:
case 0:
    System.out.println("Program ended.");
    break;

default:
    System.out.println("Invalid choice.");


- Choice 0 exits the program.
- Any other choice displays an invalid-choice message.

4. Complete Program Flow
Start

Take array input
Create two identical array copies
Deterministic Quick Sort

Choose last element as pivot


Randomized Quick Sort

Choose random pivot



Partition and recursively sort subarrays
Calculate execution times
Display sorted arrays and execution times


5. Time and Space Complexity
Case	Deterministic Quick Sort	Randomized Quick Sort
Best	\(O(n\log n)\)	\(O(n\log n)\) expected
Average	\(O(n\log n)\)	\(O(n\log n)\) expected
Worst	\(O(n^2)\)	\(O(n^2)\)
Auxiliary space	\(O(\log n)\) average	\(O(\log n)\) expected

Important: Randomized Quick Sort does not guarantee that the worst case will never happen. It makes consistently poor pivot selection less likely.

6. Important Viva Questions and Answers
Q1. What is Quick Sort?
Quick Sort is a Divide and Conquer sorting algorithm that selects a pivot, partitions the array, and recursively sorts both parts.

Q2. What is a pivot?
A pivot is an element used to divide the array into two parts: smaller elements and greater elements.

Q3. What is deterministic Quick Sort?
It selects the pivot using a fixed rule. In our program, we always select the last element.

Q4. What is randomized Quick Sort?
It randomly selects a pivot from the current subarray to reduce the chance of consistently unbalanced partitions.

Q5. What is the difference between both?
Deterministic	Randomized
Fixed pivot selection	Random pivot selection
Last element is selected in this code	Any element can be selected
Predictable pivot selection	Different pivots may be selected in different runs
Can perform poorly on certain input orders	Reduces the likelihood of poor partitions

Q6. What is the purpose of the partition method?
It places the pivot in its correct sorted position and separates smaller and greater elements.

Q7. Why do we use Arrays.copyOf()?
To create two identical arrays so that both algorithms sort the same input independently.

Q8. Why do we use System.nanoTime()?
To measure the execution time of each sorting algorithm in nanoseconds.

Q9. What is Divide and Conquer?
It is a technique in which a problem is divided into smaller subproblems, solved recursively, and combined to obtain the final result.

Q10. Can randomized Quick Sort have \(O(n^2)\) complexity?
Yes. It is possible if the random pivots repeatedly create highly unbalanced partitions, but this is unlikely over typical random pivot selections.

7. Quick Revision – Remember These Points
1. Quick Sort uses the Divide and Conquer technique.
2. Partition selects a pivot and places it in its correct position.
3. Deterministic Quick Sort selects the last element as pivot in this program.
4. Randomized Quick Sort selects a random element and swaps it with the last element before partitioning.
5. Both algorithms recursively sort the left and right subarrays.
6. The program compares their execution times using System.nanoTime().
Most important for viva
The main difference is pivot selection. Deterministic Quick Sort uses a fixed pivot, whereas Randomized Quick Sort chooses a random pivot. Both use the same partition logic and recursive sorting process.