import java.text.SimpleDateFormat; //Used to get our backup file
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Date;

// Option A
// User is now a manager, otherwise he wouldn't have gotten here. 
// User can add items or take off items from catalog
// Additional option 7 - save backup of catalog items so far

public class CatalogSection 
{
    public static void run(ArrayList<CatalogItem> bookscatalog, ArrayList<CatalogItem>  dvdscatalog )
	 {
        int userchoice; //Variable to decide which option the user chooses.
        System.out.println("**Welcome to the Books and DVDs Store**" + "(Catalog Section)\n");
        do { 
            //Finds a valid number between 1 and 6 or 9 and display options
            do {
                displayCatalogOptions(); //Displays catalogsection
                userchoice = Validator.getInt("Enter your userchoice: ");
				
                if ( (userchoice <= 0 || userchoice > 7) && userchoice != 9) 
				{
                    System.out.println("This option is not acceptable"); //input validation
                }
				
            } while ( (userchoice <= 0 || userchoice > 7) && userchoice != 9); // more input validation
           
		    switch (userchoice) {
                case 1:

                    addCatalogItem(bookscatalog, userchoice); //adds book to catalog
                    break;
                
				case 2:

                    addCatalogItem(bookscatalog, userchoice); //adds audiobook to catalog
                    break;
                
				case 3:
                 
                    addCatalogItem(dvdscatalog, userchoice); //adds dvd to catalog
                    break;
                
				case 4:
                  
                    removeItemFromCatalog(bookscatalog, userchoice, "ISBN"); //remove book from catalog(including audiobooks)
                    break;
                
				case 5:
                    
                    removeItemFromCatalog(dvdscatalog, userchoice, "dvdcode"); //remove dvd from catalog
                    break;
                
				case 6:
                    
                    displayCatalogs(bookscatalog, dvdscatalog);//Display Catalog
                    break;
                
				case 7:
                   
                    if (bookscatalog.isEmpty() && dvdscatalog.isEmpty())
                        System.out.println("There are no dvds or books!");  //Check if there is actually data to backup, then continue.
                    
					else 
					{
                        //Creates data with all underscores in the following format: yyyy_MM_dd_HH_mm_ss
                        Date date = new Date();
                        String formatted_date;
                        SimpleDateFormat formatstring = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                        formatted_date = "catalog_backup_" + formatstring.format(date);

                        UseFile.createFile(bookscatalog, dvdscatalog, formatted_date); //creates file, names it correctly, and saves it to directory 
                    }
                    break;
              
			    case 9:
                   
                    System.out.println("You are exiting the Catalog Section...\n");  //Exit the store
                    break;
              
			    default:
                    //message in case someone makes it here
                    System.out.println("How did you get here? Interesting choice, but not valid.");
                    break;
            }
			
            System.out.println();
			
        } while (userchoice != 9);
      
	    Collections.sort(bookscatalog); //we will be using Collections.sort to simply our sorting for us
        Collections.sort(dvdscatalog); //prices will be sorted in ascending order for books and dvds

    }

    private static void displayCatalogOptions() 
	{ 
		// The purpose of this method is to display the catalog after before each input
		
        System.out.print("Choose from the following options:\n"+
                "1 - Add Book\n" +
                "2 - Add AudioBook\n" +
                "3 - Add DVD\n" +
                "4 - Remove Book\n" +
                "5 - Remove DVD\n" +
                "6 - Display Catalog\n" +
                "7 - Create backup file\n" +
                "9 - Exit Catalog Section\n");
    }

    private static void addCatalogItem(ArrayList<CatalogItem> catalogItems, int userchoice) 
		//This method enables us to add items to the catalog
		// Depending on the item, varuing information must be provided like we did in previous assignments.
	{
        // The title will be passed to a new catalog item object
        String title = Validator.getString("Enter the title: ");
        double price = Validator.getDouble("Enter the price: ");

        if (userchoice == 1 || userchoice == 2) 
		{ 
			//If its a book or audiobook
            int ISBN;
            do { 
				//finds a valid and unique  isnb
                ISBN = Validator.getInt("Enter the ISBN: ");
            } while (!uniqueIDExists(catalogItems, ISBN));
            //ask for and receive author
           
		    String author = Validator.getString("Enter the author: ");

            if (userchoice == 2) 
			{ 
				//If it is an audiobook
                 //Ask for runtime
                double runningtime = Validator.getDouble("Enter the runtime:" +
                        " ");
                catalogItems.add(new AudioBook(title,price,author, ISBN,
                        runningtime));
            }
            else
			{ 
				//If it is a book
                catalogItems.add(new Book(title,price,author, ISBN));
            }
        }
        else 
		{ //If it is a DVD
            int dvdcode;
            do 
			{ //Find a valid and unique dvd code
                dvdcode = Validator.getInt("Enter the dvdcode: ");
            } while (!uniqueIDExists(catalogItems, dvdcode));

            String director = Validator.getString("Enter the director: ");
            int year = Validator.getInt("Enter the year it was released: ");

            catalogItems.add(new DVD(title,price,director,year,dvdcode));
        }
    }

    private static void removeItemFromCatalog(ArrayList<CatalogItem> catalogItems, int userchoice, String type) 
		//This method lets us remove items from the catalog by ISBN or dvdcode
	{
        if (!catalogItems.isEmpty()) 
		{
            System.out.println(type + ":");
            displayCatalog(catalogItems);

            int ID = Validator.getInt("Enter the " + type + " to be removed:" + " ");
            boolean foundID = false;
            String CatalogType;
			
            if(userchoice == 4)
                CatalogType = "Book";
            else
                CatalogType = "DVD";
			
            for(int i = 0; i < catalogItems.size(); ++i)
			 {
                //Find the catalog item and remove it
                if (catalogItems.get(i).getID() == ID)
			    {
                    catalogItems.remove(i);
                    foundID = true;
                }
             }
			 
            if (!foundID) //if we do not find this ID we state that the item doesn't exist
                System.out.println("The " + CatalogType + " doesn't exist in the " + "Catalog");
        }
        else 
		{
            System.out.println("The catalog is empty, cannot remove");
        }

    }

    private static void displayCatalogs(ArrayList<CatalogItem> BookItems, ArrayList<CatalogItem> DVDItems)
		//This method will display the catalog to the user
		//It will contain all the items added to the cart so far will all necessary pertaining information
	 {
        //Displays all the contents of the Book and DVD arrays
        System.out.println("Books:");
		
        if (!BookItems.isEmpty()) {
            displayCatalog(BookItems);
        }
        else
	    { //if the book array is empty
            System.out.println("There are no books in the catalog.");
        }
        System.out.println("DVDs:");
        if (!DVDItems.isEmpty())
	    {
            displayCatalog(DVDItems);
        }
        else 
		{ // if the dvd array is empty
            System.out.println("There are no DVDs in the catalog.");
        }
    }

    private static void displayCatalog(ArrayList<CatalogItem> arr) 
	{
        for (CatalogItem i : arr) 
		{
            System.out.println(i.toString());
        }
    }

    private static boolean uniqueIDExists(ArrayList<CatalogItem> catalogItems, int ID)
		//The purpose of this method is to ensure unique id exists for each catalog item
		//This id may be ISBN, dvdcode, or any unique number/integer assigned to the items that previously don't exist
	{
        //Determine uniqueness of an ID(ISBN or dvdcode)
        boolean isUniqueID = true;
        for(CatalogItem i: catalogItems) 
		{ //Check each ID to see if it
            // already exists
            if (i.getID() == ID)
		    {
                isUniqueID = false;
                break;
            }
        }
		
        return isUniqueID;
    }
}
