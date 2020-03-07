public class Book extends CatalogItem
	//Book is a type of catalog item 
{
    private String author;
    private int ISBN;

    Book() 
		{
			// This constructor does not have any real value but..
			// ..it is created for inheritance purposes 
		} 

    public Book(String title, double price, String author, int ISBN)
    {
        super(title,price * 0.9); //gets parents price and title. 
		//discount for book is 10%
        this.author= author;
        this.ISBN = ISBN;
    }

    public String toString()
    { //Returns the title, author, price and ISBN #
        String resultVal = String.format("Title: %25s | Author: %20s | Price: " + "%.2f | ISBN: %5d", getTitle(), getAuthor(), getPrice(), getID());
        return resultVal;
    }

    public String getAuthor() 
	{
        return this.author; //gets author information
    }

    public int getID()
	 {
        return ISBN; //gets the ISBN
    } 
}