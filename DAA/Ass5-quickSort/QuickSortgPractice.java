//  Analyze Quick Sort using deterministic and randomized variants.

import java.util.*;

public class QuickSortgPractice {
  static Random random = new Random();

  static int partition(int[] a, int low, int high){
    int pivot = a[high];
    int i = low-1;
    for(int j=low; j<high; j++){
      if(a[j] <=pivot){
        i++;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
      }
    }
    int temp = a[i+1];
    a[i+1] = a[high];
    a[high] = temp;
    return i+1;
  }

  static void deterministicQS(int[] a, int low, int high){
    if(low < high){
      int p = partition(a, low, high);
      deterministicQS(a, low, p-1);
      deterministicQS(a, p+1, high);
    }
  }

  static void randomizedQS(int[] a, int low, int high){
    if(low < high){
      int randomIndex = low + random.nextInt(high-low+1);
      int temp = a[randomIndex];
      a[randomIndex] = a[high];
      a[high] = temp;
      int p = partition(a, low, high);
      randomizedQS(a, low, p-1);
      randomizedQS(a, p+1, high);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int choice;
    do { 
      System.out.println("\n1. Compare randomized and deterministic Quick sort.\n2. Exit.");
      System.out.print("Enter Choice :");
      choice = sc.nextInt();
      switch (choice) {
          case 1:
              System.out.print("Enter number of Elements :");
              int n = sc.nextInt();
              if(n<=0){
                System.out.println("Elements must be Positive");
                break;
              }
              int[] answer = new int[n];
              for(int i=0; i<n; i++){
                System.out.print("Element "+(i+1)+" : ");
                answer[i] = sc.nextInt();
              }
              int[] a = Arrays.copyOf(answer, n);
              int[] b = Arrays.copyOf(answer, n);
              long start = System.nanoTime();
              deterministicQS(a, 0, n-1);
              long deterministicTime = System.nanoTime() - start;
              start = System.nanoTime();
              randomizedQS(b, 0, n-1);
              long randomizedTime = System.nanoTime() - start;
              System.out.println("Deterministic Sorted :"+Arrays.toString(a));
              System.out.println("Randomized Sorted :"+Arrays.toString(b));
              System.out.println("Deterministic Time :"+deterministicTime);
              System.out.println("Randomized Time :"+randomizedTime);
              break;
          case 2:
              System.out.println("Exited program");
              break;
          default:
              System.out.println("Invalid Input");
      }
    } while (choice !=2);   
  }
}
