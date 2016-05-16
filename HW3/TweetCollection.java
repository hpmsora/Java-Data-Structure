import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.net.*;

class TweetCollection implements TweetCollectionOperator {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private int curr;

    public void TweetCollection() {
	curr = 0;
    }

    //Return current position Tweet
    public Tweet getTweet() {
        return tweets.get(curr);
    }

    public int sizeOfTweet() {
	return tweets.size();
    }

    public void addTweet(Tweet tw) {
	tweets.add(tw);
    }
    
    public void deleteTweet(int i) {
	tweets.remove(i);
    }

    public void next() {
	curr++;
    }

    public void assignCurr(int pos) {
	curr = pos;
    }

    public void goStartPoint() {
	curr = 0;
    }

    public static void main(String args[]) throws IOException {

	TweetCollectionOperator Tweets = new TweetCollection();

	URL url = new URL("http://homes.soic.indiana.edu/classes/spring2016/csci/c343-yye/tweet-data-Jan16.txt");
	Scanner in = new Scanner(url.openStream()); 
 
	//From System.in (user's inputs)
	//Scanner in = new Scanner(System.in);
 
	//From a local file (e.g., tweet-data-Jan16.txt on your local machine)
	//Scanner in = new Scanner(new FileReader("tweet-data-Jan16.txt"));
 
	//Step 2: read data
	while (in.hasNext()) {
	    //nextLine() reads a line;
	    //Scanner class has other methods to allow the user to read values of various types, eg.., nextInt()
	    String str = in.nextLine();
	    //Testing
	    //System.out.println("Line= " + str);
	    //System.out.println(str.length());
	    //System.out.print("\n");

	    int strSize = str.length();
	    int wordCount = 0;
	    String word = "";
	    String ID = "";
	    String content = "";
	    for(int i = 0; i < strSize; i++) {
		//Testing
		//System.out.println(str.charAt(i));
		if((wordCount == 0) && (str.charAt(i) == ' ')) {
		    //Testing
		    //System.out.println(word);
		    ID = word;
		    wordCount++;
		    word = "";
		}
		else {
		    content = content + Character.toString(str.charAt(i));
		    word = word + Character.toString(str.charAt(i));
		}			
	    }
	    Tweet tweet = new Tweet(word, ID);
	    Tweets.addTweet(tweet);
	}
 
	//Step 3: close the scanner
	in.close();

	Scanner scan = new Scanner(System.in);

	//initializing the option
	int option = -1;
	while(option != 0) {
	    System.out.println("Choose option, input option number");
	    System.out.println("Option 1: printing all tweets");
	    System.out.println("Option 2: printing assigned tweet. You should input the order number of tweet.");
	    System.out.println("Option 3: Deleting tweet. You should input the order number of tweet.");
	    System.out.println("Option 0: Quit");
	    option = scan.nextInt();

	    if(option == 1) {
		for(int i = 0; i < Tweets.sizeOfTweet(); i++) {
		    System.out.println(Tweets.getTweet().GetUser() + " "  + Tweets.getTweet().GetContent());
		    Tweets.next();
		}
		Tweets.goStartPoint();
	    }
	    else if(option == 2) {
		System.out.println("Please enter the position number (First# = 1): (Max number: " + Tweets.sizeOfTweet() + ")");
		System.out.println("Quit? input 0");
		int i = scan.nextInt();
		if(i != 0) {
		    Tweets.assignCurr(i - 1);
		    System.out.println(Tweets.getTweet().GetUser() + " "  + Tweets.getTweet().GetContent());
		    Tweets.goStartPoint();
		}
		else
		    System.out.println("Quit");
	    }
	    else if(option == 3) {
		System.out.println("Please enter the position number(First# = 1): (Max number: " + Tweets.sizeOfTweet() + ")");
		System.out.println("Quit? input 0");
		int i = scan.nextInt();
		if(i != 0) {
		    Tweets.deleteTweet(i - 1);
		}
		else
		    System.out.println("Quit");
	    }
	    else {
		System.out.println("Invalid option");
	    }
	}
    }
}