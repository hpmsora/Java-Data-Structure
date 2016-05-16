public interface TweetCollectionOperator {
    
    //Constructor
    public void TweetCollection();
    
    //Returning current Tweet
    public Tweet getTweet();
    
    //Returning size of List of Tweets
    public int sizeOfTweet();

    //Adding new Tweet to List of Tweets
    public void addTweet(Tweet tw);
    
    //Deleting indexed Tweet from List of Tweets
    public void deleteTweet(int i);

    //Change the current positon to next position
    public void next();

    //Assigned the position
    public void assignCurr(int pos);

    //Make current position to 0
    public void goStartPoint();
}