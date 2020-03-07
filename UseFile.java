import java.io.*;
import java.util.ArrayList;

public class UseFile 
	//In this class we will be ensuring that we are getting the credentials saved in the text file
	// named credentials.txt in order to add items to catalog for Option A
	// We will do this as a sort of login procedure which can either fail or succeed depending on validity of
	// the crdentials. 
	// We will also be printing the information that we got from catalog section into a backup file if Option 7 is entered.
{
    public static boolean login(String username, String password) 
	{
        boolean canlogin = false;
        String getUserName = "", getPassword = ""; //Program will find
        try {
            //Open a file in called credentials.txt
            File file = new File("credentials.txt"); // reads into the file named credentials.txt
            FileReader fileReader = new FileReader(file);
           
		    BufferedReader credentials = new BufferedReader(fileReader);
            String bufferstring;

            //Loop will go through all of credentials.txt line by line, to determine if the corresponding usernames and passwords are
            // valid.
            boolean foundUserName = false;
            while ( (bufferstring = credentials.readLine()) != null  && !foundUserName) 
			{
                char[] temp = bufferstring.toCharArray();
                StringBuilder temp_s = new StringBuilder();

                //We will iterate through the line and find the username and password, set it to getUserName and getPassword
                for(char c: temp) 
				{
                    //We can ignore spaces in the loop
					// This can be considered input validation but is mostly for formatting purposes.
                    if (c != ' ' && c != ',')
				    {
                        temp_s.append(c);
                    }
                    // If there is a comma, then password is to the right of the comma. 
                    else if ( c == ',') 
					{
                        getUserName = temp_s.toString();
                        temp_s = new StringBuilder();
                    }
                }
                //Determines if the username exists.(there can not be duplicate usernames in the text file)
                if (getUserName.equals(username))
			    {
                    getPassword = temp_s.toString();
                    foundUserName = true;
                }
            }
			
            //Validates that the password is correct.
			//This information is case sensitive as is the password
            if (getPassword.equals(password) && foundUserName)
                canlogin = true;
			
            fileReader.close();
            credentials.close();
        }
		
        catch (IOException e) 
		{ //determines if file exists
            System.out.println("ERROR: " + e);
        }
		
        return canlogin;
    }

    public static void createFile(ArrayList<CatalogItem> books, ArrayList<CatalogItem> dvds, String name) 
		//Purpose of this method is to create a file that backups all items saved onto the catalog
		// It divides them into their respective types: whether item is a book, dvd etc.
	{
        try {
            //Create an output file to print line by line
            File outfile = new File(name + ".txt");
            FileOutputStream fileOutputStream = new FileOutputStream(outfile);
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            //Print all the books in the arraylist
            bufferedWriter.write("Books:\n");
            for (int i = 0; i < books.size(); ++i)
		    {
                bufferedWriter.write(books.get(i).toString() + "\n");
            }

            //Print all the dvds in the arraylist.
            bufferedWriter.write("Dvds:\n");
            for (int i = 0; i < dvds.size(); ++i) 
			{
                bufferedWriter.write(dvds.get(i).toString() + "\n");
            }
            bufferedWriter.close();
            fileOutputStream.close();
        }
		
        catch (IOException e)
	    {
            System.out.println("ERROR: " + e); //for better security in the case an exception is thrown.
        }
    }

}
