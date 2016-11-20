import java.util.Random;

public class RandomDNA {
    public static void main (String args[]) {
	String DNA = "";
	String[] DNAletters = {"A", "T", "C", "G"};
	for (int i = 1; i<21; i++) {
	    Random rnd = new Random();
	    DNA = DNA + DNAletters[rnd.nextInt(4)];
	}
	System.out.println(DNA);
    }
}
