//Name: Kaushik Nadimpalli
//Class: CS2336.503
// Term Project
/* The purpose of this assignment is to expand on the Store that we have worked on in earlier assignments, 
but this time our focus is on developing a complete system that serves multiple roles. Put simply, the program
will consist of three main options for the user. Option A enables the user to access information pertaining
to the store manager. In order to further proceed from this option, the user must enter a valid username and password,
which already exist in a file called credentials.txt. If correct credentials are entered then the user can proceed and
add items to the catalog as its the manager's role. They can add books, audiobooks, and dvds like we did in Assignment
three. While some code may have been reused from previous assignments, the logic is a little different because we are
adding another option, 7, this time to the catalog. This options executes all the saved catalog items into a text file,
named as “catalog_backup_<currentDateAndTime>.txt” where the current date and time is the time the backup file being 
created. The user can go back to main menu options by clicking option 9. With Option B, the user assumes the customer's
position and can add books(include audiooks which were previously different in catalog section). They can add a book
or dvd, or they can remove a book or dvd. They can view cart and they are given total price with new discounts calculated in.
The order of the items display for options 1 and 2 in main menu Option A is from low price to high price. Finally, Option C
exists the store and gives an exit message. */

/* Some assumptions we are making for this project is that the credentials.txt file will be saved in the same location as
our source files and directories. Also, we are assuming that we only need to utilize option 7 in main menu option B when there
are items in the catalog. Otherwise there is no logical reason to create a backup. Furthermore, most if not all of our input
validation will be done in the Validator class as per instructions. However, with rare extreme obscurities as input, we are going
to just display a fun message informaing the user to enter a valid input or asking them how in the blazes did they end up where 
they did.  */

import java.util.ArrayList; //We will be using ArrayLists to store all our items for this project

public class TermProjectCS2336 
{
    public static void main(String[] args) 
	{
        System.out.println("**Welcome to the Comets Books and DVDs Store**\n");
        ArrayList<CatalogItem> bookscatalog = new ArrayList<>(); //create arraylist for books
        ArrayList<CatalogItem>  dvdscatalog = new ArrayList<>(); //create arraylist for dvds
		
        String userChoice; //input scanner for user

        //We will continue to loop through the doWhile until the user chooses to exit the store(option C)
        do {
            displayMainMenu();
            userChoice = Validator.getString("Enter your userChoice: ");
			//The Validator class will take care of input validation for user choice in main menu
			//It will assure that the user only enters A, B, or C 
			
            switch(userChoice) {
                case "A":
                    //If option, chosen, prompts for username and password
					// Username and password must exist in the credentials.txt file which is in same directory as source files
                    String username = Validator.getString("Please enter your" + " username: ");
                    String password = Validator.getString("Please enter your" + " password: ");               
					
                    if (UseFile.login(username,password))
                        CatalogSection.run(bookscatalog, dvdscatalog);
                    //If login is successful, we can give user access to go to catalog section and run  it
                    else
                        System.out.println("Invalid username or password.\n");
						System.out.println("Please reenter the correct store manager credentials.\n");
					//If login is unsuccessful, we give the user a warning and ask tem to reenter correct credentials from text file
                    break;
               
			    case "B":
                    //Go to the shopping section
					//User is the customer
                    CustomerSection.run(bookscatalog, dvdscatalog);
                    break;
                
				case "C":
                    //Exit Stores
					//Gives an exit message
                    System.out.println("You have successfully exited the store! Please come again!");
                    break;
                
				default:
                    System.out.println("\"" + userChoice + "\" is not a valid " + "userChoice.");
					//Input validation for better security
					//states that its not a valid choice....more addon to our validation in the Validator class
            }
			
        } while (!userChoice.equals("C")); // menu won't be displayed again if user enters C because they want to exit the store
    }

    private static void displayMainMenu()
	{
        System.out.print("Please select your role:\n" + "A - store manager\n" + "B - customer\n" + "C - exit store\n");
		//Better to use print than println for formatting purposes
		//Menu will be displayed between options and also when returning to main menu from either customer or manager catalogs
    }
}
