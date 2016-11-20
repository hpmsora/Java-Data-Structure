import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class DNADist {
	//Declare static variables
	static int size = 0;
	static int turning = 1;
	
	public static String GenDNA(){
		 //Declare a DNA String
		 //		For saving each random DNA sequence
		String DNA = "";
	     
	    //Assigned DNA elements
	    String[] DNAletters = {"A","T","C","G"};
	     
	    //New object from Random
	    Random rnd = new Random();
	    
	    //Looping for making sequence
	    for (int i = 1; i < (size + 1); i++) {
	    	int t = rnd.nextInt(4);
	        String newDNA = DNAletters[t];
	        DNA = DNA + newDNA;
	        }
	    
	    return DNA;
	    }
	
	public static String[] DNAStrtoArr(String str) {
		//Declare a String Array
		//		For saving DNA Sequence
		String[] ArrDNA = new String[size];
		
		//Loop
		//		For collecting each elements of the sequence
		for (int i = 0; i < str.length(); i++)
			ArrDNA[i] = str.substring(i, i + 1);
		return ArrDNA;
	}
	
	public static int CalDiff(String[] arr1, String[] arr2) {
		//Declare a Integer variable
		//		For counting difference
		int count = 0;
		
		//Loop
		//		For comparing each elements in Arrays
		for (int i = 0; i < size; i++) {
			if (!arr1[i].equals(arr2[i]))
				count++;
		}
		return count;
	}
	
	public static int CalDiffing (String DNA1, String DNA2) {
		//Declare a Integer Variable
		//		For getting Hamming Difference as final value
		int diff = 0;
		
		String[] ArrDNA1 = new String[size];
		String[] ArrDNA2 = new String[size];
		
		//Converting DNA String value to Array value
		ArrDNA1 = DNAStrtoArr(DNA1);
		ArrDNA2 = DNAStrtoArr(DNA2);
		
		//DNAStrtoArr(String) Test
		//System.out.println(Arrays.toString(ArrDNA1));
		//System.out.println(Arrays.toString(ArrDNA2));
		
		//Calculating Hamming Difference
		diff = CalDiff(ArrDNA1, ArrDNA2);
		return diff;
	}
	public static void main(String[] args) {
		//Asking How many sequence want to make
		Scanner scan = new Scanner(System.in);
		while (turning == 1) {
			//Printing the Qeustion
			System.out.println("How many elements?");
			size = scan.nextInt();
			
			//Declare a Integer variable
			//		For getting Hamming Difference as final value
			int result = 0;
			
			//Declare two String variables
			//		For saving random made DNA sequences
			String DNA1 = GenDNA();
			String DNA2 = GenDNA();
			
			//Printing two random made sequence
			System.out.println(DNA1);
			System.out.println(DNA2);
			
			//Calculating Hamming Difference
			result = CalDiffing(DNA1, DNA2);
			
			//Printing result
			System.out.println("Hamming Difference: " + result + "\n");
			System.out.println("If you want to continue\t\tpress \"1\"\nIf you want to quit\t\tpress \"0\"");
			turning = scan.nextInt();
			}
		scan.close();
		}
	}
