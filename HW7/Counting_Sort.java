/////////////////////////////////////////////////////
//
// Won Yong Ha
//
// CSCI-C 343
// HW7
//
// Start: March 1 2016
// End:   March 3 2016
//
/////////////////////////////////////////////////////

/*---------------------------------------------------
Analysis

Since the difference of the each elements pretty
affects to this algorithm which means if the array
constains lots of defferent variable then sorting the
array would take longer. So the number of records
more likely affects on the algorithm.

In conclusion, many data(the number of records) but less
difference(the largest key value) would be best.
----------------------------------------------------*/

//import
import java.util.Scanner;
import java.util.Random;

class Counting_Sort {
  
  //Global varialbes
  static int numArr[];
  static int sizeArr;
  static int maxnumArr;
  
  //------------------------------------------------
  //findMax method
  public static int findMax() {
    int max = 0;
    for(int i = 0; i < sizeArr; i++) {
      if(max < numArr[i])
        max = numArr[i];
    }
    maxnumArr = max;
    return max;
  }
  
  //countingSort Method
  public static int[] countingSort() {
      findMax();
      int[] c = new int[maxnumArr + 1];
    for(int i = 0; i < sizeArr; i++) {
      c[numArr[i]] += 1;
    }
      /*System.out.print("(");
      for (int i = 0; i < c.length - 1; i++) {
          System.out.print(c[i] + ", ");
      }
      System.out.println(numArr[sizeArr - 1] + ")");*/
    //array of accumulated counts
    for(int i = 1; i < maxnumArr + 1; i++) {
      c[i] += c[i - 1];
    }
      /*System.out.print("(");
      for (int i = 0; i < c.length - 1; i++) {
          System.out.print(c[i] + ", ");
      }
      System.out.println(numArr[sizeArr - 1] + ")");*/
    //then, use the count information to place the items in order, saved to a new array
      int[] sorted = new int[sizeArr];
      for(int i = 0; i < sizeArr; i++) {
          int pos = c[numArr[i]] - 1;
          sorted[pos] = numArr[i];
          c[numArr[i]] = c[numArr[i]] - 1;
      }
      sorted[sizeArr - 1] = maxnumArr;
    return sorted;
  }
  
  //------------------------------------------------
  //makeSize Method
  public static void makeSize() {
    Scanner scan = new Scanner(System.in);
    
    //Getting number of elements
    System.out.println("How many elements in array?");
    int size = scan.nextInt();
    
    sizeArr = size;
    numArr = new int[size];
  }
  
  //insertManual Method
  public static void insertManual(){
    Scanner scan = new Scanner(System.in);
    for(int i = 0; i < sizeArr; i++) {
      System.out.print((i + 1) + ": ");
      int num = scan.nextInt();
      numArr[i] = num;
    }
  }
  
  //insertRandom Method
  public static void insertRandom() {
    Random random = new Random();
    for(int i = 0; i < sizeArr; i++) {
      numArr[i] = random.nextInt(sizeArr);
    }
  }
  
  //printing Method
  public static void printing() {
    System.out.print("(");
    for (int i = 0; i < sizeArr - 1; i++) {
      System.out.print(numArr[i] + ", ");
    }
    System.out.println(numArr[sizeArr - 1] + ")");
  }
  
  //Main Method
  public static void main(String args[]) {
    
    //Initialize the Array
    makeSize();
    
    //Initialize the option
    int option = 99;
    
    //Options
    while (option != 0) {
      Scanner scan = new Scanner(System.in);
      
      //Print options
      System.out.println("Insert number for option");
      System.out.println("Option 1: Resize the array");
      System.out.println("Option 2: Insert number manually");
      System.out.println("Option 3: Insert number randomly");
      System.out.println("Option 4: Print the array");
      System.out.println("Option 5: Find maximum number");
      System.out.println("Option 6: Print sorted array");
      System.out.println("Option 0: Quit");
      
      //Getting option
      option = scan.nextInt();
      
      //Options
      //Resize the array
      if(option == 1) {
        makeSize();
      }
      //Insert number manually
      else if(option == 2) {
        insertManual();
      }
      //Insert number randomly
      else if(option == 3) {
        insertRandom();
      }
      //Print the array
      else if(option == 4) {
        printing();
      }
      //Find maximum number
      else if(option == 5) {
        System.out.println("Maximum number: " + findMax());
      }
      //Print Sorted array
      else if(option == 6) {
        int[] arr = countingSort();
        //Print array
        System.out.print("(");
        for(int i = 0; i < sizeArr - 1; i++) {
          System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[sizeArr - 1] + ")");
      }
      //Out of option
      else {
        System.out.println("Invalid option");
      }
    }
  }
}
