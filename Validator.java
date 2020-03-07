import java.util.Scanner;

public class Validator implements Acceptable
	// We are ensuring our main input validation in this class
{
    private static Scanner input = new Scanner(System.in);
    private static Validator check = new Validator();

    public boolean isNonEmptyString(String x)
    {
        return x.isEmpty(); //if string is empty
    }
	
    public boolean isPositiveInput(double y) 
	{
        return y >= 0; //ensure positive output
    }
	
    public boolean isPositiveInput(int z) 
	{
        return z >= 0; //ensure positive output
    }

    public static String getString(String message) 
	{ 
		//Purpose of this method is to ensure user enters a nonempty string
        String x;

        do
	    {
            System.out.print(message);
            x = input.nextLine();
            if (x.isEmpty())
                System.out.println("Please enter a non-empty string");
        } while (check.isNonEmptyString(x));
       
	    return x;
    }

    public static double getDouble(String message) 
	{ // Purpose of this method is to return a positive double value which we will use for input validation
        double y;
        do {
            System.out.print(message);
            while (!input.hasNextDouble()) 
			{
                System.out.print(message);
                input.nextLine();
                if(!input.hasNextDouble())
                    System.out.println("Please ONLY enter a valid double.");
            }
            y = input.nextDouble();
            input.nextLine();
        } while (!check.isPositiveInput(y));
        
		return y;
    }

    public static int getInt(String message) 
	{ //Purpose of this method is to ensure user inputs an integer and not a string nor double for options etc.
        int z;
        do {
            System.out.print(message);
            while (!input.hasNextInt()) 
			{
                System.out.print(message);
                input.nextLine();
                if(!input.hasNextInt())
                    System.out.println("Please ONLY enter a valid integer.");
            }
            z = input.nextInt();
            input.nextLine();
        } while (!check.isPositiveInput(z));
        
		return z;
    }
}
