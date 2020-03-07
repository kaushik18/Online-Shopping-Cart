abstract public class CatalogItem implements Comparable<CatalogItem>{
    private String title; //book title
    private double price; // book price

    CatalogItem () 
	{
		//default constructor
	}

    CatalogItem (String title, double price)
	{
        //this ensures current classes references and not its parents'
		this.title = title;
        this.price = price;
    }

    abstract public String toString(); 
	//Returns all the data in a format

    abstract public int getID(); 
	//Returns either its ISBN or its dvdcode

    public String getTitle() 
	{
        return this.title; //gets title
    }

    public double getPrice() 
	{
        return this.price; //gets price
    }

    public int compareTo(CatalogItem compareItem) 
	{
        double compare_value = compareItem.getPrice();
        return (int)Math.floor(this.price - compare_value);
		//returns new price, discount accounted for.
    }
}
