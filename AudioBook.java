public class AudioBook extends Book
	//audiobook has properties of book + its own properties. Book's subclass
{
    private double runningTime; //counted in minutes

    public AudioBook(String title, double price, String author, int ISBN, double runningTime) 
	{
        //Divided by 0.9 to cut off discount in Book.
        super(title, price * 0.5 / 0.9, author, ISBN);
        this.runningTime = runningTime;
    }

    public double getPrice() 
	{ 
		//Audiobook has a 10% discount
        return super.getPrice();
    }

    public double getRunningTime()
    {
        return this.runningTime; //get runningtime
    }

    @Override
    public String toString() 
	{ 
		//Appends runningtime to the end of the toString in Book
        String result = String.format(" | RunningTime: %.2f", getRunningTime());
        return super.toString() + result;
    }
}
