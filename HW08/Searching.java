///////////////////////////////////////////////////////////////////////
//
// Won Yong Ha
//
// CSCI-C 343
// HW8
//
// Start: March 26 2016
// End: March 26 2016
//
///////////////////////////////////////////////////////////////////////

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Searching {
    
    public static void main(String[] args) {
	Hashtable<String, String> hash = new Hashtable<String, String>();
	int lineNum = 1;
	//Searching
	try {
	    Scanner s = new Scanner(new File("docu.txt"));
	    while (s.hasNextLine()) {
		Scanner scan = new Scanner(s.nextLine());
		while(scan.hasNext()) {
		    String str = scan.next();
		    if (hash.get(str) == null) {
			hash.put(str, Integer.toString(lineNum));
		    } 
		    if (!(hash.get(str).contains(Integer.toString(lineNum)))) {
			hash.put(str, hash.get(str) + " " + Integer.toString(lineNum));
		    }
		}
		lineNum++;
	    }
	}
	//no file
	catch(FileNotFoundException e) {
	    System.out.println("N/A");
	}
	//Getting String
	Scanner scan2 = new Scanner(System.in);
	System.out.println("Input the String: ");
	String str = scan2.nextLine();
	System.out.println(hash.get(str));
    }
}
