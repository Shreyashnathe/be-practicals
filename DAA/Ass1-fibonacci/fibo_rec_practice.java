import java.util.*;

public class fibo_rec_practice{ 
  static long fibonacci(int n){
    if(n<=1) return n;
    return fibonacci(n-1) + fibonacci(n-2);
  }
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int choise;
    do{
      System.out.println("\n1.Calculate Score. \n2.Exit");
      System.out.print("Enter Choise :");
      choise = sc.nextInt();
      switch (choise) {
          case 1:
              System.out.print("Enter n :");
              int n = sc.nextInt();
              if(n<0 || n>90) System.out.println("Enter number between 0 and 90");
              else System.out.println("Fibonacci Number :" + fibonacci(n));
              break;
          case 2:
              System.err.println("Exiting...");
              break;
          default:
              System.out.println("Invalid Input");
      }
    }
    while(choise != 2);
    
  }
}