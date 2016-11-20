import java.util.Scanner;
import java.lang.Math;

class HW2_ADT implements ADT_Ab {

    //Declare Global Variable

    // size of talbe
    static int ln = 0;
    static int rw = 0;

    public boolean isEmpty(int[][] exarr) {
	for(int i = 0; i < ln; i++) {
	    for(int j = 0; j < rw; j++) {
		if(exarr[i][j] != 0)
		    return false;
	    }
	}
	return true;
    }

    public int[][] randomFill(int[][] exarr) {
	for(int i = 0; i < ln; i++) {
	    for(int j = 0; j < rw; j++) {
		exarr[i][j] = (int)(Math.random() * (ln * rw) + 1);
	    }
	}
	return exarr;
    }

    public void printing(int[][] exarr) {
	for(int i = 0; i < ln; i++) {
	    for(int j = 0; j < rw; j++) {
		System.out.print(exarr[i][j] + "\t");
	    }
	    System.out.print("\n");
	}
    }

    public int[][] deleting(int[][] exarr, int del) {
	for(int i = 0; i < ln; i++) {
	    for(int j = 0; j < rw; j++) {
		if(exarr[i][j] == del) {
		    exarr[i][j] = 0;
		}
	    }
	}
	return exarr;
    }

    public static void main(String args[]) {
	ADT_Ab adt = new HW2_ADT();
	
	Scanner scan = new Scanner(System.in);
	System.out.println("This is making 2D array list.");
	
	//Getting size of 2D array
	System.out.println("How many lines?");
	ln = scan.nextInt();
      System.out.println("How many rows?");
      rw = scan.nextInt();
      
      //initializing array
      int[][] arr = new int[ln][rw];
      //System.out.println(ln + " " + rw);
      
      //Command Loop
      int option = 5;
      while(option != 0) {
	  System.out.println("Options:");
	  System.out.println(" 1 : Checking empty of not");
	  System.out.println(" 2 : Random re-initializing");
	  System.out.println(" 3 : Print array");
	  System.out.println(" 4 : Deleting certain number in array");
	  System.out.println(" 0 : Quit");
	  
	  option = scan.nextInt();
	  
	  //Starting command
	  if(option == 1)
	      System.out.println(adt.isEmpty(arr));
	  if(option == 2) {
	      adt.randomFill(arr);
	      adt.printing(arr);
	  }
	  if(option == 3)
	      adt.printing(arr);
	  if(option == 4) {
	      System.out.println("Number delete:");
	      int x = scan.nextInt();
	      arr = adt.deleting(arr,x);
	      adt.printing(arr);
	  }
	  if(option != 1 && option != 2 && option != 3 && option != 4 && option == 0)
	      System.out.println("Envalid option, try again");
      }
      /*
      //Checking empty?
      System.out.println(isEmpty(arr));
      
      //Random filling
      randomFill(arr);
      
      //print the array
      printing(arr);
      
      //Checking empty?
      System.out.println(isEmpty(arr));
      
      //Deleting number
      System.out.println("Number delete: ");
      int x = scan.nextInt();
      arr = deleting(arr, x);

      printing(arr);
      */
    }
}
