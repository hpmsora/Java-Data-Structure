public class Tweet {
    String content;
    String usr;

    Tweet (String Content, String User) {
	content = Content;
	usr = User;
    }

    String GetUser () {
	return usr;
    }

    String GetContent() {
	return content;
    }
}
