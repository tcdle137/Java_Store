package Assignment4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InteractiveStoreClass 
{	
	// PRIVATE VARIABLE DECLARATION
	protected static ArrayList<Product> Inventory =  new ArrayList<Product>(); 
	protected static String customerName;
	protected static boolean payInFull;
	
	// CONSTRUCTOR
	public InteractiveStoreClass() 
	{
		try 
		{
			readInventoryFromFile();	
		} catch (FileNotFoundException e)
		{
			System.out.println("File was not able to be accessed.");
		}
			
	}
	
	// CONSOLE MAIN FUNCTION                                   <---------------
	public static void storeMain ()
	{
		// CREATE SCANNER INSTANCE FOR INPUT
		Scanner input = new Scanner(System.in);
		
		// GET USER NAME
		System.out.println("Welcome to\n\t\tThe Trading Card Store!!!");
		customerName = readNameFromFile();
		
		if (customerName == null)
		{
			input.close();
			return;
		}
		
		// DISPLAY MENU FOR USER
		displayInventory(input);
		
		// RECIEVE INPUT FROM USER
		retrieveUserInput(input);
		
		// DISPLAY RECIEPT
		printUserReceipt(input);
		
		input.close();
		System.out.println("Done!");
	}

	// READS INVENTORY FROM FILE
	public static void readInventoryFromFile() throws FileNotFoundException
	{
		File file = new File("Inventory.txt");
		Scanner input = new Scanner(file);
		
		// LOADING INVENTORY ARRAYLIST WITH INVENTORY ITEMS 
		while (input.hasNextLine())
		{
			Product fromFile =  new Product(input.nextLine(), input.nextLine(), input.nextLine(), Double.parseDouble(input.nextLine()));
			Inventory.add(fromFile);
		}
		
		input.close();
	}
	// READS USER'S NAME FROM FILE
	public static String readNameFromFile()
	{
		String userID;
		File file = new File("Users.txt");
		Scanner input = new Scanner(System.in);
		
		System.out.println("What is your User ID?");
		for (int i = 0; i < 5; i++)
		{
			userID = input.nextLine();
			// IN CASE FILE COULD NOT BE READ
			try
			{
				Scanner fromFile = new Scanner(file);
				// SEARCH FILE FOR USER DATA
				while (fromFile.hasNextLine())
				{
					if (userID.equals(fromFile.nextLine()))
					{
						return fromFile.nextLine();
					}
					else 
					{
						fromFile.nextLine();
					}
				}
				fromFile.close();
			} catch (FileNotFoundException e)
			{
				System.out.println("File was not found. Goodbye.");
				return null;
			}
			
			if (i == 4)
			{
				System.out.println("You have exceeded the allotted number of attempts. Goodbye.");
				return null;
			}
			
			System.out.println("That UserID was not found. Please try again.");
		}
		// BECUASE METHOD GIVE ERROR SINCE NO RETURN LINE IS GUARUNTEED
		return null;
	}
	
	public static void displayInventory(Scanner input)
	{
		// VARIABLES
		String responseStr = "";
		boolean test1 = false;
		
		// PRINT MENU
		System.out.println(  "==================================================");
		System.out.printf(   "Hello %s!\nHere are our products:\n", customerName);
		System.out.println(  "Product Code     Product Price     Product Name");
		for (Product I : Inventory)
		{
			System.out.printf("%8s          $%.2f    \t    %s\n", I.getCode(), I.getPrice(), I.getName() );
		}
		System.out.println(  "==================================================");
		
		// CHECK TO SEE IF THE USER WANTS TO VIEW ANY DESCRIPTIONS
		System.out.println("Would you like to see the descriptions of any of the products?");
		System.out.println("Please enter Y for yes and N for no:");
		responseStr = input.nextLine();
		// 	VALIDATION
		while (!responseStr.equals("Y") && !responseStr.equals("N"))
		{
			System.out.println("Please ONLY enter Y or N:");
			responseStr = input.nextLine();
		}
		// END FUNCTION IF USER IS DONE 
		if (responseStr.equals("N"))
		{
			// EXIT LOOP
			return;
		}
		while (responseStr.equals("Y"))
		{
			test1 = false;
			// DISPLAY PRODUCT DESCRIPTIONS
			System.out.println("Which product would you like to see the decription of?");
			System.out.println("Please input the Product Code of the produce you want to view the description of.");
			responseStr = input.nextLine();
			do
			{
				for (Product I : Inventory)
				{
					if (responseStr.equals(I.getCode()))
					{
						test1 = true;
						// LEAVE FOR LOOP
						break;
					}
				}
				if (test1 == false)
				{
					System.out.println("That product was not found. Please enter a Product Code:");
					responseStr = input.nextLine();
				}
			} while (!test1);
			// PRINT SPECIFIED DESCRIPTION
			for (Product I : Inventory)
			{
				if (responseStr.equals(I.getCode()))
				{
					System.out.printf("%s:\n\t%s\n",I.getName(), I.getDescription());
				}
			}
			
			// SEE IF THE USER WANTS TO SEE MORE DESCRIPTIONS
			System.out.println("\nWould you like to see the descriptions of more products?");
			System.out.println("Please enter Y for yes and N for no:");
			responseStr = input.nextLine();
			// 	VALIDATION
			while (!responseStr.equals("Y") && !responseStr.equals("N"))
			{
				System.out.println("Please ONLY enter Y or N:");
				responseStr = input.nextLine();
			}
			// END FUNCTION IF USER IS DONE 
			if (responseStr.equals("N"))
			{
				// EXIT LOOP
				return;
			}
		}
	}
	
	public static void retrieveUserInput(Scanner input)
	{
		// VARIABLES
		boolean done = false, test1 = false, found = false;
		String responseStr = "";
		
		System.out.println("==================================================");
		System.out.println("What product would you like to purchase?");
		System.out.println("Please input a Product code or -1 if you are done purchasing.");
		
		// PURCHASE LOOP
		while (!done)
		{
			// RESET VARIABLES EVERY LOOP
			test1 = false; found = false;
			
			// CHECKS IF INPUT IS AN INT, AND IF NOT SENDS ERROR MESSAGE AND LOOPS
			try 
			{
				responseStr = input.nextLine();
				if (Integer.parseInt(responseStr) == -1)
				{
					// EXITS MAIN WHILE LOOP
					done =  true;
					continue;
				}
			} catch (NumberFormatException e)
			{
				System.out.println("Invalid input, please only input numbers.");
				// CONTINUE TO NEXT ITERATION OF DO/WHILE LOOP
				continue;
			}
			
			// CHECKS TO SEE IF THE INPUT MATCHES ANY PRODUCT CODE
			for (Product I : Inventory)
			{
				if (responseStr.equals(I.getCode()))
				{
					found = true;
					// AKS FOR AMOUNT WANTED TO PURCHASE
					do 
					{
						System.out.printf("How many %s would you like to purchase?\n", I.getName());
						try
						{
							responseStr = input.nextLine();
							// IF LESS THAN 0 THEN GOES INTO IF STATEMENT
							// IF THE parseInt() FUNCTION FAILS, THEN IT WILL BE CAUGHT IN CATCH STATEMENT
							if (Integer.parseInt(responseStr) < 0 )
							{
								System.out.println("Please input an amount greater than or equal to 0.");
								continue;
							}
							
						} catch (NumberFormatException e)
						{
							System.out.println("Please input only numbers");
							continue;
						}
						
						// IF IT HAS MADE IT THIS FAR, THEN THE INPUT IS VALID
						test1 = true;
						
					} while (!test1);
					
					// ADDS THE AMOUNT INPUTTED TO THE INVENTORY COUNT
					I.addCount(Integer.parseInt(responseStr));
					
					// EXITS FOR LOOP
					break;
				}
			}
			// IF IT WAS NOT FOUND IN THE FOR LOOP, PRINT ERROR MESSAGE
			if (!found)
			{
				System.out.println("That product was not found. Please enter -1 or a valid Product Code.");
				// CONTINUE TO NEXT ITERATION OF DO/WHILE LOOP
				continue;
			}
			System.out.println("What product would you like to purchase?");
			System.out.println("Please input -1 if you are done purchasing.");
		}
		
		// PROMPT USER IF THEY WOULD LIKE TO PAY IN FULL OR IN INSTALLMENTS
		System.out.println("==================================================");
		System.out.println("Would you like to pay in full (one time payemnt) or in installments?");
		System.out.println("Please input F to pay in full, or I to pay in installments.");
		responseStr = input.nextLine();
		while (!(responseStr.equals("F") || responseStr.equals("I")))
		{
			System.out.println("Please only input F or I");
			responseStr = input.nextLine();
		}
		if (responseStr.equals("F"))
		{
			payInFull = true;
		}
		else
		{
			payInFull = false;
		}
		
	}

	public static void printUserReceipt(Scanner input)
	{
		double subtotal = 0;
		double finalTotal = 0;
		String ans;
		System.out.println("==================================================");
		System.out.println("Would you like you receipt printed to the console, or to a .txt file?");
		System.out.println("Please input C for console, T for .txt file, or B for both.");
		ans = input.nextLine();
		while (!(ans.equals("C") || ans.equals("T") || ans.equals("B")))
		{
			System.out.println("Please only input C, T, or B.");
			ans = input.nextLine();
		}
		if (ans.equals("C") || ans.equals("B"))
		{
			// IF USER WANTS CONSOLE AND TXT RECEIPT
			if (ans.equals("B"))
			{
				printReceiptToFile(input);
			}
			// MAIN RECEIPT PRINT
			System.out.println("\n==================================================\n");
			System.out.printf("Hi, %s!\nThank you for shopping at\n\tThe Trading Card Store\n\n", customerName);
			// PRINT EACH ITEM SELECTED
			for (Product I : Inventory)
			{
				if (I.getCount() > 0)
				{
					System.out.printf("%s %-30s\t%d x $%.2f\n", I.getCode(), I.getName(), I.getCount(), I.getPrice());
					// ADD >= 10 ITEM DISCOUNT
					if ( I.getCount() >= 10)
					{
						System.out.println("\t\t10% disc. for >= 10 items");
						System.out.printf("%42s$%.2f\n", "", get2Decimals( (I.getPrice() * I.getCount()) - (I.getPrice() * I.getCount() * 0.1)));
						subtotal += get2Decimals( (I.getPrice() * I.getCount()) - (I.getPrice() * I.getCount() * 0.1));
					}
					else
					{
						System.out.printf("%42s$%.2f\n", "", get2Decimals(I.getPrice() * I.getCount()));
						subtotal += get2Decimals(I.getPrice() * I.getCount());
					}
				}
			}
			// ADD FULL PAYMENT DISCOUNT
			if (payInFull)
			{
				System.out.println("\n5% Discount for payment in full.");
				System.out.printf("Previous Subtotal:\t$%.2f\n", subtotal);
				System.out.printf("Savings:\t\t$%.2f", get2Decimals(subtotal*0.05));
				subtotal = subtotal - get2Decimals((subtotal * 0.05));
			}
			// FINAL PRICE OUTPUT
			System.out.printf("\n\nSubtotal:\t$%.2f\n", subtotal);
			System.out.printf("Tax:\t\t$%.2f\n", get2Decimals(subtotal * 1.06) - subtotal);
			finalTotal = get2Decimals(subtotal * 1.06);
			System.out.printf("Final Total:\t$%.2f\n", finalTotal);
			System.out.println("\nThank you for shopping at The Trading Card Store!\n\t\tPlease come again soon!");
			System.out.println("\n==================================================\n");
			
		}
		else
		{
			printReceiptToFile(input);
		}
	}
						
	public static void printReceiptToFile(Scanner input)
	{
		// CREATES NEW FILE
		String fileName = customerName + " Receipt.txt";
		File file = new File(fileName);
		String fromConsole, line;
		double subtotal = 0;
		try
		{
			// .createNewFile NEEDS EXCEPTION
			if (file.createNewFile())
			{
				System.out.println("The receipt file was created, named: " + file.getName());
			}
			else 
			{
		        System.out.println("File already exists.");
		        System.out.println("Would you like to try again? MOVE or RENAME the file BEFORE you input Y or N.");
				System.out.println("Please input Y for yes and N for no.");
				fromConsole = input.nextLine();
				while (!(fromConsole.equals("Y") || fromConsole.equals("N")))
				{
					System.out.println("Please only input Y or N.");
					fromConsole = input.nextLine();
				}
				if(fromConsole.equals("Y"))
				{
					printReceiptToFile(input);
				}
					
		    }
			
		} catch (IOException e)
		{
			System.out.println("There was a problem creating that file.");			
		}
		
		// WRITES TO SAID FILE
		try
		{
			BufferedWriter writeFile = new BufferedWriter(new FileWriter(file.getName()));
			
			// CONTENT TO WRITE INTO FILE
			line = String.format("Hi %s!\n", customerName);
			line += String.format("%31s\n%37s\n%35s\n\n", "*~~-~~*~~-~~*", "Thank you for shopping at", "The Trading Card Store");
			//writeFile.write(line, 0, line.length()-1);
			for (Product I : Inventory)
			{
				if (I.getCount() > 0)
				{
					line = line + String.format("%s %-30s%10d x $%.2f\n", I.getCode(), I.getName(), I.getCount(), I.getPrice());
				
					// ADD >= 10 ITEM DISCOUNT
					if ( I.getCount() >= 10)
					{
						line += String.format("%50s\n%49s%.2f\n", "10% disc. for >= 10 items", "$", get2Decimals(I.getPrice() * I.getCount() - (I.getPrice() * I.getCount() * 0.1)));
						subtotal += get2Decimals(I.getPrice() * I.getCount() - (I.getPrice() * I.getCount() * 0.1));					
					}
					else
					{
						line += String.format("%49s%.2f\n", "$", get2Decimals(I.getPrice() * I.getCount()));
						subtotal += get2Decimals(I.getPrice() * I.getCount());
					}
				}
			}
			if (payInFull)
			{
				line += String.format("%s","\n5% Discount for payment in full.\n");
				line += String.format("%-20s$%.2f\n", "Previous Subtotal:", subtotal);
				line += String.format("%-20s$%.2f\n\n", "Savings:", get2Decimals(subtotal*0.05));
				subtotal -= get2Decimals(subtotal*0.05);
			}
			line += String.format("%-20s$%.2f\n", "Subtotal:", subtotal);
			line += String.format("%-20s$%.2f\n", "Tax:", get2Decimals(subtotal*0.06));
			line += String.format("%-20s$%.2f\n", "Final Total:", subtotal + get2Decimals(subtotal*0.06));
			line += String.format("\n%31s\n", ".~~-~~.~~-~~.");
			line += String.format("%49s\n%36s", "Thank you for shopping at The Trading Card Store!","Please come again soon!");
			writeFile.write(line, 0, line.length()-1);
			writeFile.close();
		} catch (IOException e)
		{
			System.out.println("There was a problem writing to that file.");
		}
	}
	
	// KEEP CONSISTENT NUMBERS
	public static double get2Decimals(double in)
	{
		int changeInt;
		double manip = in * 100;
		changeInt = (int)manip;
		manip = (double)changeInt/100;
		return manip;
	}

	protected static String getCustomerName()
	{
		return customerName;
	}
}
