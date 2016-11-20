import java.util.Scanner;
import java.util.Random;

/** Array-based list implementation */
class TextBook<E> implements List<E> {
    private static final int defaultSize = 10; // Default size
    private int maxSize; // Maximum size of list
    private int listSize; // Current # of list items
    private int curr; // Position of current element
    private E[] listArray; // Array holding list elements
    /** Constructors */
    /** Create a list with the default capacity. */
    TextBook() { this(defaultSize); }
    /** Create a new list object.
	@param size Max # of elements list can contain. */
    @SuppressWarnings("unchecked") // Generic array allocation
	TextBook(int size) {
	maxSize = size;
	listSize = curr = 0;
	listArray = (E[])new Object[size]; // Create listArray
    }
    public void clear() // Reinitialize the list
    { listSize = curr = 0; } // Simply reinitialize values
    /** Insert "it" at current position */
    public void insert(E it) {
	assert listSize < maxSize : "List capacity exceeded";
	for (int i=listSize; i>curr; i--) // Shift elements up
	    listArray[i] = listArray[i-1]; // to make room
	listArray[curr] = it;
	listSize++; // Increment list size
    }
    /** Append "it" to list */
    public void append(E it) {
	assert listSize < maxSize : "List capacity exceeded";
	listArray[listSize++] = it;
    }
    /** Remove and return the current element */
    public E remove() {
	if ((curr<0) || (curr>=listSize)) // No current element
	    return null;
	E it = listArray[curr]; // Copy the element
	for(int i=curr; i<listSize-1; i++) // Shift them down
	    listArray[i] = listArray[i+1];
	listSize--; // Decrement size
	return it;
    }
    public void moveToStart() { curr = 0; } // Set to front
    public void moveToEnd() { curr = listSize; } // Set at end
    public void prev() { if (curr != 0) curr--; } // Back up
    public void next() { if (curr < listSize) curr++; }
    /** @return List size */
    public int length() { return listSize; }
    /** @return Current position */
    public int currPos() { return curr; }
    /** Set current list position to "pos" */
    public void moveToPos(int pos) {
	assert (pos>=0) && (pos<=listSize) : "Pos out of range";
	curr = pos;
    }
    /** @return Current element */
    public E getValue() {
	assert (curr>=0) && (curr<listSize) :
	"No current element";
	return listArray[curr];
    }
    public static void main(String args[]) {
	//Decalre a imported function	
	Scanner scan = new Scanner(System.in);
	Random random = new Random();

	//Receving number
	// for size of the list
	System.out.println("How many variables?");
	int size = scan.nextInt();

	//Defult value for option
	int option = -1;

	//Declare a List
	List intList = new TextBook(size);

	//While loop for questions
	while(option != 0) {
	    System.out.println("Input the option number:");
	    System.out.println("Option 1: Inputting integer variables munually.");
	    System.out.println("Option 2: Inputting random integer.");
	    System.out.println("Option 3: Printing the list.");
	    System.out.println("Option 4: Deleting input ineger on the list");
	    System.out.println("option 0: Quit");
	    option = scan.nextInt();
	    
	    //Functions
	    if(option == 1) {
		intList.clear();
		//System.out.println(intList.currPos());
		intList.moveToStart();
		for(int i = 0; i < size; i++) {
		    System.out.print((i + 1) + "'s number: ");
		    int x = scan.nextInt();
		    intList.insert(x);
		    intList.next();
		}
		intList.moveToStart();
		System.out.print("\n");
	    }
	    else if(option == 2) {
		intList.clear();
		intList.moveToStart();
		for(int i = 0; i < size; i++) {
		    int x = random.nextInt(size);
		    intList.insert(x);
		    intList.next();
		}
		intList.moveToStart();
		System.out.print("\n");
	    }
	    else if(option == 3) {
		//System.out.println("option 3");
		intList.moveToStart();
		for(int i = 0; i < size; i++) {
		    if(i % 5 == 0)
			System.out.print("\n");
		    System.out.print(intList.getValue() + "\t");
		    //if(i < size - 1)
		    intList.next();
		}
		System.out.print("\n");
		System.out.print("\n");
	    }
	    //------------------------------------------------
	    //Deleting session
	    else if(option == 4) {
		intList.moveToStart();
		System.out.println("Number to delete:");
		int x = scan.nextInt();
		for(int i = 0; i < size; i++) {
		    if((int)intList.getValue() == x) {
			intList.remove();
		    }
		    intList.next();
		}
		System.out.print("\n");
	    }
	    //-----------------------------------------------
	    else
		System.out.println("Invalid option.");
	}
	System.out.println("Program End");
    }
}