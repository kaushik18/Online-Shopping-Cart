import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

// Same as assignment 2
// This time we will be using arrayLists because we don't know how many items the user wants to add
// ArrayLists help us not limit the options we can give to the user on how many items they can purchase

public class CustomerSection 
{
    public static void run(ArrayList<CatalogItem> bookscatalog, ArrayList<CatalogItem>  dvdscatalog) 
	{
        Scanner input = new Scanner(System.in);
        int userchoice; //scanner userchoice

        ArrayList<CatalogItem> BookCart = new ArrayList<>(); //array list to hold books
        ArrayList<CatalogItem> DvdCart = new ArrayList<>(); //array list to hold dvds

        System.out.println("**Welcome to the Comets Books and DVDs Store**\n\n");
        do {
            boolean isAdded = false, isDeleted = false;
			 //For adding and deleting
			 //input validation
            
			int ISBN, dvdcode; 
			//For adding or deleting
			
            displayCustomerMenu(); //displays the customer menu
            //Finds a valid userchoice between 1 and 9
            do {
                userchoice = Validator.getInt("Enter your userchoice:");
				
                if (userchoice <= 0 || userchoice > 9) 
				{
                    System.out.println("Please enter a value between 1 and 9");
                }
            } while (userchoice <= 0 || userchoice > 9);
            
			switch (userchoice) {
                case 1:
                    //This will display all the books in the catalog
                    displayArrayLists(bookscatalog, "Books");
                    break;

                case 2:
                    //This will display all dvds in the catalog 
                    displayArrayLists(dvdscatalog, "Dvds");
                    break;

                case 3:
                    //Tries to add a book by ISBN to the cart
					// Will prompt/warn user if ISBN doesn't exist
                    isAdded = false;
                    if (!bookscatalog.isEmpty()) 
					{
                        displayArrayLists(bookscatalog, "Books");
                        ISBN = Validator.getInt("Enter the ISBN of the " + "book: ");
                        isAdded = addItemtoCatalog(bookscatalog, BookCart, ISBN);
						
                        if (!isAdded)
                            System.out.println("That ISBN does not exist");
                    }
                    else
                        System.out.println("The books catalog is empty");

                    break;

                case 4:
                    //Tries to add a DVD by dvdcode
					// Will prompt/warn user if dvdcode doesn't exist
                    isAdded = false;
                    if (!bookscatalog.isEmpty())
				   {
                        displayArrayLists(dvdscatalog, "Dvds");
                        dvdcode = Validator.getInt("Enter the dvdcode of the" + " Dvd: ");
                        isAdded = addItemtoCatalog(dvdscatalog, DvdCart, dvdcode);
						
                        if (!isAdded)
                            System.out.println("That dvdcode does not exist");
                    }
                    else
                        System.out.println("The dvds catalog is empty.");
                    break;

                case 5:
                    //Tries to delete a book by ISBN
					//If ISBN of book doesn't exist, it will warn the user
                    if(!BookCart.isEmpty()) 
					{
                        isDeleted = false;
                        displayArrayLists(BookCart, "Books");
                        ISBN = Validator.getInt("Enter the ISBN of the " + "book: ");
                        isDeleted = deleteItemFromCatalog(BookCart, ISBN);
						
                        if (!isDeleted)
                            System.out.println("That ISBN was not in the cart.");
                    }
                    else
                        System.out.println("The book cart is empty.");
                    break;

                case 6:
                    //Tries to delete the DVD by ISBN
					// If dvdcode doesn't exist, it will warn the user
                    if(!DvdCart.isEmpty()) 
					{
                        isDeleted = false;
                        displayArrayLists(DvdCart, "Dvds");
                        dvdcode = Validator.getInt("Enter the dvdcode of the" + " Dvd: ");
                        isDeleted = deleteItemFromCatalog(DvdCart, dvdcode);
						
                        if (!isDeleted)
                            System.out.println("That dvdccode was not in the cart" + ".");
                    }
                    else
                        System.out.println("The dvd cart is empty");
                    break;

                case 7:
                    //Displays all arrays and the total with tax included.
                    displayArrayLists(BookCart, "Books");
                    displayArrayLists(DvdCart, "Dvds");
                    System.out.printf("Your total(including tax): %.2f\n", (getTotal(BookCart) + getTotal(DvdCart)) );
                    break;

                case 8:
                    //Displays/gives total including tax and clear the cart.
                    displayArrayLists(BookCart, "Books");
                    displayArrayLists(DvdCart, "Dvds");
                    System.out.printf("Your total(including tax): %.2f\n", (getTotal(BookCart) + getTotal(DvdCart)) );
                    clearArrayLists(BookCart);
                    clearArrayLists(DvdCart);
                    break;

                case 9:
                    //Clear the shopping cart then exit to the main menu catalog that has three options A, B, and C
                    clearArrayLists(BookCart);
                    clearArrayLists(DvdCart);
                    System.out.println("Thanks for shopping with us!");
                    break;
            }
			
            System.out.println();
        } while (userchoice != 9); //executes these only when userChoice if not 9.
    }

    private static void displayCustomerMenu() 
	{ 
		//This method purpose is to display the menu after every input
		// It displays the customer menu to the user
        System.out.print("Choose from the following options:\n"+
                "1 - Browse books inventory (price low to high)\n" +
                "2 - Browse DVDs inventory (price low to high)\n" +
                "3 - Add a book to the cart\n" +
                "4 - Add a DVD to the cart\n" +
                "5 - Delete a book from cart\n" +
                "6 - Delete a DVD from cart\n" +
                "7 - View cart\n" +
                "8 - Checkout\n" +
                "9 - Done Shopping\n");
    }

    private static void displayArrayLists(ArrayList<CatalogItem> arr, String type) 
		// The purpose of this method is to display the arratLists with catalog items to the user
		// ToString is used for formatting and concatnation purposes
	{
        //Displays one arraylist of catalogitems.
        System.out.println(type + ":");
        if (!arr.isEmpty()) 
		{
            for (CatalogItem i: arr)
                System.out.println(i.toString());
        }
        else
            System.out.println("This Catalog is empty.");
    }

    private static double getTotal(ArrayList<CatalogItem> arr) 
		// The purpose of this method is to calculate the total and return the total price
	{
        //Totals the total amount including tax.
        double total = 0;
        if (!arr.isEmpty()) 
		{
            total = 0;
            for (CatalogItem i: arr)
                total += i.getPrice();
            return total + (total * 0.0825);
        }
		
        return total;
    }

    private static void clearArrayLists(ArrayList<CatalogItem> arr) 
	{
        //The purpose of this method is to clear the array lists.
		// We will be using .clear method to do this for us
		// Remember that only the carts will be cleared.
        arr.clear();
    }

    private static boolean addItemtoCatalog(ArrayList<CatalogItem> catalog, ArrayList<CatalogItem> cart, int id) 
		// The purpose of this method is to add items to the customer cart
	{
        //Searches for the id to add, if it finds a matching id then it will add it to the cart and exit.
        boolean hasID = false;
        for (CatalogItem i: catalog) {
            if (i.getID() == id) {
                cart.add(i);
                hasID = true;
                break;
            }
        }
		
        return hasID;
    }

    private static boolean deleteItemFromCatalog(ArrayList<CatalogItem> cart, int id) 
		// The purpose of this item is to delete items from the customer cart
	{
        //Scans for a matching ID, if it finds one is marks it found and
        // deletes the item.
        boolean isDeleted = false;
        Iterator<CatalogItem> iter = cart.iterator();

        //iterate through the array and find the equivalent id, if there are
        // products with the same id, it will only remove one of them.
        if (!cart.isEmpty()) 
		{
            while (iter.hasNext()) 
			{
                CatalogItem i = iter.next();
                if (i.getID() == id) 
				{
                    iter.remove(); //removes items from cart
                    isDeleted = true;
                    break;
                }
            }
        }
		
        return isDeleted;
    }
}
