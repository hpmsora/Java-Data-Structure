/////////////////////////////////////////////////////////////////////////
//
// Won Yong Ha
//
// CSCI-C 343
// HW8
//
// Start: 26 March 2016
// End: 26 March 2016
//
//////////////////////////////////////////////////////////////////////////

//import Transpose
package transpose;

//Import
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class transpose {
    
    //get
    public static void listing(String[] str, HashSet<String> hs, String x) {
	for (int i = 0; i < str.length;i++) {
	    String y = str[i];
	    if (hs.contains(y)) {
		if (str[i] != x) {
		    str[i] = str[i-1];
		    str[i-1] = str[i];
		}	
	    }
	    else {
		str[i+1]= y;
	    }
	    
	}
	
    }
    
    public static void printing(String[] str) {
	for (int i = 0; i < str.length; i++)
	    System.out.print(str[i] + " ");
	System.out.print("\n");
    }
    
    public static void main (String[] args){

	String[] strArr = new String[999];
	int num = 0;
	//Read File
	try {
	    Scanner file = new Scanner(new File("file.txt"));
	    while (file.hasNextLine()) {
		Scanner scan = new Scanner(file.nextLine());
		while(scan.hasNextLine()) {
		    String a = scan.next();
		    strArr[num] = a;
		    num++;
		}
	    }
	}
        catch(FileNotFoundException e) {
            System.out.println("N/A");
        }
	
	//Declare HashSet
	HashSet<String> hs = new HashSet<>();
	try {
	    Scanner file = new Scanner(new File("file.txt"));

	    while (file.hasNextLine()){
		String y = file.nextLine();
		hs.add(y);
	    }
	}
	catch(FileNotFoundException e) {
	    System.out.println("N/A");
	}

	//String[] strArr = {A, B, C, D, E};
	Scanner scan = new Scanner(System.in);

	System.out.println("Input the character");
	String str = scan.nextLine();

	listing(strArr, hs, str);
	printing(strArr);
    }
}
