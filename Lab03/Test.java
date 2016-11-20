public class Test {

    public static void main(String args[]) {

	Tweet test1 = new Tweet("C343, Ahhh so tired!", "woha");
	Tweet test2 = new Tweet("I dont want to go to class!!!", "computer");
	Tweet test3 = new Tweet("Ahh this is my first class!!", "T1");
	System.out.println(test1.GetUser());
	System.out.println(test1.GetContent());
	System.out.println(test2.GetUser());
	System.out.println(test2.GetContent());
	System.out.println(test3.GetUser());
	System.out.println(test3.GetContent());
    }
}
