/////////////////////////////////////////////////
// Won Yong Ha
//
// C343
// HW6
//
// Start: Feb 26 2016
// End: Feb 27 2016
/////////////////////////////////////////////////
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Scanner;

class dist_edit {
  
  //DNA
  String dna1;
  String dna2;
  
  public dist_edit(String x) throws IOException {
    
    //Default
    BufferedReader read = new BufferedReader(new FileReader("sample.txt"));
    
    //Read file
    if (!x.equals("0")) {
      String name = x + ".txt";
      read = new BufferedReader(new FileReader(name));
    }
    
    //Declare variables
    String var;
    
    //First line
    var = read.readLine();
    dna1 = var;
    
    //Second line
    var = read.readLine();
    dna2 = var;
    
    //Printing Sequence
    System.out.println(dna1);
    System.out.println(dna2);
    
    //int res = cal_distance(dna1, dna2);
    //System.out.println(res);
  }
  
  public int cal_distance() {

    //initialization
    int m = dna1.length();
    int n = dna2.length();

    //String to ArrayList
    char[] a = dna1.toCharArray();
    char[] b = dna2.toCharArray();
    
    //difference check
    int c;
    
    // "d" for list of distance
    int[][] d = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) d[i][0] = i;
    for (int j = 0; j <= n; j++) d[0][j] = j;
    
    //fill the DP table
    for(int i = 1; i <= m; i ++) {
      for(int j = 1; j <= n; j ++) {
        //i must be reduce 1
        if (a[i - 1] == b[j - 1]) c = 0;
        else c = 1;            
        d[i][j] = Math.min (Math.min(d[i-1][j] + 1, d[i][j-1] + 1), d[i-1][j-1] + c);
      }
    }
    int distance = d[m][n];

  return distance;
  }
  
  //main------------------------------------------------------------------------------
  public static void main (String args[]) throws IOException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Input the text file name");
    System.out.println("Press '0' to defult text file");
    String x = scan.nextLine();
    
    //System.out.println(x);
    
    dist_edit sample = new dist_edit(x);

    int res = 0;
    res = sample.cal_distance();

    System.out.println(res);
  }
}
