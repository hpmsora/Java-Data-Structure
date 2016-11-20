import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


public class MyCard implements Card {
	String[] carddeck = new String[52];
	
	public void initialize() {
		int count = 0;
		String num = "";
		for(int i = 0; i <= 3; i++) {
			for(int j = 1; j <= 13; j++) {
				if(j == 1)
					num = "A";
				else if(j == 11)
					num = "J";
				else if(j == 12)
					num = "Q";
				else if(j == 13)
					num = "K";
				else
					num = Integer.toString(j);
				
				if(i == 0) {
					carddeck[count] = num + "S";
					count++;
				}
				else if(i == 1) {
					carddeck[count] = num + "C";
					count++;
				}
				else if(i == 2) {
					carddeck[count] = num + "H";
					count++;
				}
				else {
					carddeck[count] = num + "D";
					count++;
				}
			}
		}
	}
	
	public String drawCard(){
		Random num = new Random();
		int n = num.nextInt(52);
		return carddeck[n];
	}
	
	public static void main(String args[]) {
		Card carding = new MyCard();
		carding.initialize();
		String k = "d";
		String[] drawedcard = new String[999];
		int x = 0;
		Scanner scan = new Scanner(System.in);
		while (!(k.equals("q"))){
			System.out.println("d: Draw, q: Quit");
			k = scan.nextLine();
			if (k.equals("d")){
				String y = carding.drawCard();
				drawedcard[x]= y;
				x++;
				System.out.println(y);
			}
		}
		String[] drawedcarding = new String[x];
		drawedcarding=Arrays.copyOfRange(drawedcard, 0, x);
		System.out.println(Arrays.toString(drawedcarding));
	}
}