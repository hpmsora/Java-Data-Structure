/////////////////////////////////////////////////////////
//
// Won Yong Ha
//
// CSCI-C 343
// Week 9
//
// Start: Mar 10 2016
// End:   Mar 10 2016
//
/////////////////////////////////////////////////////////

class BinarySearch <E extends Comparable<?super E>>
{
    public int binary(E[] item, E target) {
	int l = -1;
	int r = item.length;
	int i;
	while (l + 1 != r) {
	    i = (l + r) / 2;
	    if (target.compareTo(item[i]) == 0) return i;
	    else if(target.compareTo(item[i]) < 0) r = i;
        else l = i;
	}
	return item.length;
    }

    public static void main(String args[]) {
    //First example: Integer
	BinarySearch<Integer> example1_1 = new BinarySearch<Integer>();
	Integer[] example1  = {1, 3, 5, 7, 9};

    //Second example: String
	BinarySearch<String> example2_1 = new BinarySearch<String>();
	String[] example2 = {"aa", "bb", "cc", "dd", "ee"};

    //Print value
	System.out.println(example1_1.binary(example1, 3));
	System.out.println(example2_1.binary(example2, "bb"));
    }
}