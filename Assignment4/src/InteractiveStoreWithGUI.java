import Assignment4.InteractiveStoreClass;
import Assignment4.Product;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class InteractiveStoreWithGUI extends InteractiveStoreClass
{
	// CLASS VARIABLES
	static private JFrame frame;
	static private JFrame getID;
	//static private JFrame itemUI;
	// COLORS
	static private Color mainBlue;
	static private Color mainLightGrey;
	static private Color mainDarkGrey;
	static private Color mainDarkBlue;
	
	// CONSTRUCTOR
	InteractiveStoreWithGUI()
	{
		// SET COLORS
		mainBlue = new Color(40, 120, 190);
		mainLightGrey = new Color(180, 190, 200);
		mainDarkGrey = new Color(90, 95, 100);
		mainDarkBlue = new Color (6, 24, 69);
		
		// MAIN FRAME
		frame = new JFrame("Interactive Store");
		frame.setSize(1000,800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// GET USER NAME
		getID = new JFrame("Input User ID");
		getID.setSize(500,400);
		getID.setLocationRelativeTo(null);
		getID.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// MAIN METHOD
	public static void main(String args[]) 
	{
		Scanner input = new Scanner(System.in);
		int choice = chooseGUI(input);
		// IF RETURNS 0, RUN CONSOLE STORE	
		if (choice == 0)
		{
			InteractiveStoreClass IS = new InteractiveStoreClass();
			IS.storeMain();
		}
		// IF RETURNS 1, RUN GUI STORE
		else if (choice == 1)
		{
			InteractiveStoreWithGUI ISG = new InteractiveStoreWithGUI();
			getUserNameFromGui(ISG);
		}
	}
	
	public static int chooseGUI(Scanner input)
	{
		String choice = "";
		boolean done = false;
		
		System.out.println("Please input 0 for console interface or 1 for GUI interface:");
		
		// INPUT VALIDATION
		while (!done)
		{
			try
			{
				choice = input.nextLine();
				if (Integer.parseInt(choice) == 0 || Integer.parseInt(choice) == 1)
				{
					// LEAVE BECUASE INPUT WAS VALIDATED
					done = true;
					continue;
				}
				else 
				{
					// INPUT WAS AN INTEGER BUT NOT 0 OR 1
					System.out.println("Please only input 0 for console or 1 for GUI.");
				}
			}catch(Exception e)
			{
				// INPUT WAS NOT AN INTEGER
				System.out.println("Please only input 0 for console or 1 for GUI.");
				continue;
			}
		}
		
		if (Integer.parseInt(choice) == 0)
		{
			return 0;
		}
		else 
		{
			return 1;
		}
	}
	
	// USER NAME
	public static void getUserNameFromGui(InteractiveStoreWithGUI ISG)
	{
		//JPanel cent = new JPanel();
		JLabel prompt = new JLabel("Please input your User ID:");
		JTextField txtIn = new JTextField("Type your User ID Here");
		JLabel update = new JLabel("User Not Found. Please try again.");
		getID.setLayout(new BorderLayout());
		
		// DISPLAY PROMPT
		prompt.setForeground(mainBlue);
		prompt.setBackground(mainLightGrey);
		prompt.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 24));
		prompt.setHorizontalAlignment(JTextField.CENTER);
		prompt.setOpaque(true);
		prompt.setSize(new Dimension(500, 150));
		
		// UPDATE USER
		update.setForeground(mainBlue);
		update.setBackground(mainLightGrey);
		update.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 24));
		update.setHorizontalAlignment(JTextField.CENTER);
		update.setOpaque(true);
		update.setVisible(false);
		update.setSize(new Dimension(500, 150));
		
		// DISPLAY TEXT BOX
		txtIn.setForeground(mainDarkGrey);
		txtIn.setBackground(mainLightGrey);
		txtIn.setHorizontalAlignment(JTextField.CENTER);
		txtIn.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 24));
		txtIn.setSize(new Dimension(500, 100));
		
		
		// ADD EVENT LISTENERS
		txtIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String inputText = txtIn.getText();
        		if (getNameFromFileForGUI(inputText))
        		{
        			runGUI(ISG);
        			getID.setVisible(false);
        			return;
        		}
        		update.setVisible(true);
            }
        });
		
		// ADD TO FRAME
		getID.add(prompt, BorderLayout.NORTH);
		getID.add(txtIn, BorderLayout.CENTER);
		getID.add(update, BorderLayout.SOUTH);
		getID.setVisible(true);
	}
	public static boolean getNameFromFileForGUI(String ID)
	{
		File file = new File("Users.txt");
		Scanner input = new Scanner(System.in);
		
		// IN CASE FILE COULD NOT BE READ
		try
		{
			Scanner fromFile = new Scanner(file);
			// SEARCH FILE FOR USER DATA
			while (fromFile.hasNextLine())
			{
				if (ID.equals(fromFile.nextLine()))
				{
					customerName = fromFile.nextLine();;
					return true;
				}
				else 
				{
					fromFile.nextLine();
				}
			}
			fromFile.close();
			return false;
		} catch (FileNotFoundException e)
		{
			System.out.println("File could not be found");
			return false;
		}
	}
	
	public static void runGUI(InteractiveStoreWithGUI ISG)
	{
		// TOP TITLE
		JPanel title = new JPanel();
		Border border = BorderFactory.createLineBorder(mainDarkGrey);
		title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		title.setLayout(new GridLayout(4,1));
		for (int i = 0; i < 4; i++)	
		{
			JLabel text;
			if (i == 0)
			{
				text = new JLabel("\tHi " + ISG.customerName + "!");
				text.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 18));
				text.setBackground(mainLightGrey);
				text.setForeground(Color.BLACK);
			}
			else if (i == 1)
			{
				text = new JLabel("Welcome to The Trading Card Store!");
				text.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 32));
				text.setBackground(mainBlue);
				text.setForeground(Color.BLACK);
			}
			else if (i == 2)
			{
				text = new JLabel("*Please click on the product you would like to purchase!");
				text.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 18));
				text.setBackground(mainLightGrey);
				text.setForeground(Color.BLACK);
			}
			else
			{
				text = new JLabel("**Hover over the product name to see a description!");
				text.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 18));
				text.setBackground(mainLightGrey);
				text.setForeground(Color.BLACK);
			}
			text.setBorder(border);
			text.setOpaque(true);
			text.setHorizontalAlignment(JTextField.CENTER);
			title.add(text);
		}
		
		// INVENTORY
		JPanel inv = new JPanel();
		//inv.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		inv.setLayout(new GridLayout(5,3, 10, 10));
		inv.setBorder(new TitledBorder("Catalog"));
		for (Product I : Inventory)
		{
			JLabel t1;
			ImageIcon img;
			Image imge;
			JLabel t2 = new JLabel(String.format("$%.2f", I.getPrice()));
			t2.setHorizontalAlignment(JTextField.CENTER);
			t2.setBackground(mainLightGrey);
			t2.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 24));
			t2.setOpaque(true);
			t2.setBorder(border);
			
			// BUTTON TO LEAD TO ITEM UI
			// NEED FIVE UNIQUE BUTTONS
			switch(I.getCode())
			{
			case "0001":
				// ICON
				img = new ImageIcon("P_Pack.png");
				imge = img.getImage();
				imge = imge.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(imge);
				t1 = new JLabel(img);
				t1.setBorder(border);
				inv.add(t1);
				// BUTTON
				JButton jbut_0001 = new JButton(I.getName());
				// ALLOWS USER TO HOVER OVER PRODUCT NAME TO SEE A DESCRIPTION
				jbut_0001.setToolTipText(I.getDescription());
				jbut_0001.setForeground(Color.WHITE);
				jbut_0001.setBackground(mainBlue);
				jbut_0001.setBorder(border);
				jbut_0001.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
				// ADD EVENT LISTENERS
				jbut_0001.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	itemUI(ISG, jbut_0001);
		            }
		        });
				inv.add(jbut_0001);
				break;
			case "0010":
				// ICON
				img = new ImageIcon("P_Box.png");
				imge = img.getImage();
				imge = imge.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(imge);
				t1 = new JLabel(img);
				t1.setBorder(border);
				inv.add(t1);
				// BUTTON
				JButton jbut_0010 = new JButton(I.getName());
				// ALLOWS USER TO HOVER OVER PRODUCT NAME TO SEE A DESCRIPTION
				jbut_0010.setToolTipText(I.getDescription());
				jbut_0010.setForeground(Color.WHITE);
				jbut_0010.setBackground(mainBlue);
				jbut_0010.setBorder(border);
				jbut_0010.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
				// ADD EVENT LISTENERS
				jbut_0010.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	itemUI(ISG, jbut_0010);
		            }
		        });
				inv.add(jbut_0010);
				break;
			case "0011":
				// ICON
				img = new ImageIcon("OP_Pack.png");
				imge = img.getImage();
				imge = imge.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(imge);
				t1 = new JLabel(img);
				t1.setBorder(border);
				inv.add(t1);
				// BUTTON
				JButton jbut_0011 = new JButton(I.getName());
				// ALLOWS USER TO HOVER OVER PRODUCT NAME TO SEE A DESCRIPTION
				jbut_0011.setToolTipText(I.getDescription());
				jbut_0011.setForeground(Color.WHITE);
				jbut_0011.setBackground(mainBlue);
				jbut_0011.setBorder(border);
				jbut_0011.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
				// ADD EVENT LISTENERS
				jbut_0011.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	itemUI(ISG, jbut_0011);
		            }
		        });
				inv.add(jbut_0011);
				break;
			case "0100":
				// ICON
				img = new ImageIcon("OP_Box.png");
				imge = img.getImage();
				imge = imge.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(imge);
				t1 = new JLabel(img);
				t1.setBorder(border);
				inv.add(t1);
				// BUTTON
				JButton jbut_0100 = new JButton(I.getName());
				// ALLOWS USER TO HOVER OVER PRODUCT NAME TO SEE A DESCRIPTION
				jbut_0100.setToolTipText(I.getDescription());
				jbut_0100.setForeground(Color.WHITE);
				jbut_0100.setBackground(mainBlue);
				jbut_0100.setBorder(border);
				jbut_0100.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
				// ADD EVENT LISTENERS
				jbut_0100.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	itemUI(ISG, jbut_0100);
		            }
		        });
				inv.add(jbut_0100);
				break;
			case "0101":
				// ICON
				img = new ImageIcon("OP_Case.png");
				imge = img.getImage();
				imge = imge.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(imge);
				t1 = new JLabel(img);
				t1.setBorder(border);
				inv.add(t1);
				// BUTTON
				JButton jbut_0101 = new JButton(I.getName());
				// ALLOWS USER TO HOVER OVER PRODUCT NAME TO SEE A DESCRIPTION
				jbut_0101.setToolTipText(I.getDescription());
				jbut_0101.setForeground(Color.WHITE);
				jbut_0101.setBackground(mainBlue);
				jbut_0101.setBorder(border);
				jbut_0101.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
				// ADD EVENT LISTENERS
				jbut_0101.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	itemUI(ISG, jbut_0101);
		            }
		        });
				inv.add(jbut_0101);
				break;
			}
			inv.add(t2);
		}
		
		// OTHER OPTIONS
		JPanel checkOut = new JPanel();
		checkOut.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		checkOut.setLayout(new GridLayout(2,2));
		// BUTTON1
		JButton jbut2 = new JButton("Pay in full");
		jbut2.setBackground(mainDarkGrey);
		jbut2.setForeground(Color.WHITE);
		jbut2.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		checkOut.add(jbut2);
		
		JButton jbut3 = new JButton("Pay in installments");
		jbut3.setBackground(mainDarkGrey);
		jbut3.setForeground(Color.WHITE);
		jbut3.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		checkOut.add(jbut3);
		
		JButton jbut4 = new JButton("View your Receipt");
		jbut4.setBackground(mainDarkGrey);
		jbut4.setForeground(Color.WHITE);
		jbut4.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		checkOut.add(jbut4);
		
		JButton jbut5 = new JButton("Save Receipt to .txt");
		jbut5.setBackground(mainDarkGrey);
		jbut5.setForeground(Color.WHITE);
		jbut5.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		checkOut.add(jbut5);
		
		// ACTION LISTENERS
		jbut2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	jbut2.setText("Thank You!");
            	jbut3.setText("Thank you!");
            	jbut2.setEnabled(false);
            	jbut3.setEnabled(false);
            	payInFull = true;		
            }
        });
		jbut3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	jbut2.setText("Thank You!");
            	jbut3.setText("Thank you!");
            	jbut2.setEnabled(false);
            	jbut3.setEnabled(false);
            	payInFull = false;
            }
        });
		jbut4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (payInFull )
            	{
            		
            	}
            	displayReceiptOnGUI();
            }
        });
		jbut5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(printReceiptToFileFromGUI())
            	{
            		jbut5.setText("Saved!");
            		jbut5.setEnabled(false);
            		
            		return;
            	}
            	else
            	{
            		displayPrintError();
            	}
            }
        });
		
		// ADD EVERYTHING TO FRAME AND SET TO VISIBLE
		frame.setLayout(new BorderLayout(15,15));
		frame.setBackground(mainDarkBlue);
		frame.add(title, BorderLayout.NORTH);
		frame.add(inv, BorderLayout.CENTER);
		frame.add(checkOut, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public static void itemUI(InteractiveStoreWithGUI ISG, JButton jbut)
	{
		// ITEM UI
		// NEW FOR EACH EVENT FROM THE 5 BUTTONS THAT IS CALLED
		JFrame itemUI = new JFrame("Item Selection");
		itemUI.setSize(500,400);
		itemUI.setLocationRelativeTo(null);
		itemUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		itemUI.setLayout(new BorderLayout());
		
		JPanel item = new JPanel();
		item.setLayout(new GridLayout(1,2));
		JPanel cent = new JPanel();
		cent.setLayout(new GridLayout(2,1));
		JLabel name = new JLabel(jbut.getText());
		JLabel code;
		JLabel price;
		JLabel prompt = new JLabel("How many would you like to buy?");
		JTextField text = new JTextField("Input # here");
		
		// PRODUCT
		for (Product I: Inventory)
		{
			String temp = jbut.getText();
			if (temp.equals(I.getName()))
			{
				name.setToolTipText(I.getDescription());
				name.setBackground(mainBlue);
				name.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 24));
				name.setOpaque(true);
				name.setHorizontalAlignment(JTextField.CENTER);
				
				code = new JLabel(I.getCode());
				code.setHorizontalAlignment(JTextField.CENTER);
				code.setBackground(mainLightGrey);
				code.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 24));
				code.setOpaque(true);
				
				price = new JLabel(String.format("$%.2f", I.getPrice()));
				price.setHorizontalAlignment(JTextField.CENTER);
				price.setBackground(mainDarkGrey);
				price.setForeground(Color.WHITE);
				price.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 24));
				price.setOpaque(true);
				
				
				item.add(code);
				item.add(price);
				break;
			}
		}
		
		// CHOICES / PROMPT
		prompt.setHorizontalAlignment(JTextField.CENTER);
		prompt.setBackground(mainDarkGrey);
		prompt.setForeground(Color.WHITE);
		prompt.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 24));
		prompt.setOpaque(true);
		
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setBackground(mainLightGrey);
		text.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 24));
		text.setOpaque(true);
		
		
		// EVENTS
		text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String inputText = text.getText();
            	try
            	{
            		int in = Integer.parseInt(inputText);
            		// if is number and above 0
            		if (in >= 0)
            		{
            			for (Product I : Inventory)
                		{
                			String temp = jbut.getText();
                			if (temp.equals(I.getName()))
                			{
                				I.addCount(in);
                				itemUI.setVisible(false);
                				text.setText("Input # here");
                				return;
                			}
                		}
            		}
            		else
            		{
            			prompt.setText("Invalid input, please try again");
            		}
            	} catch (Exception E)
            	{
            		// if not number, display error message
            		prompt.setText("Invalid input, please try again");
            	}
            }
        });
		
		
		// ADD EVERYTHING TO THE LAYOUT
		itemUI.add(item, BorderLayout.NORTH);
		cent.add(name);
		cent.add(prompt);
		itemUI.add(cent, BorderLayout.CENTER);
		itemUI.add(text, BorderLayout.SOUTH);
		itemUI.setVisible(true);
	}
	
 	public static void displayReceiptOnGUI()
	{
		JFrame receipt = new JFrame("Receipt");
		JPanel organizer = new JPanel();
		double subtotal = 0;
		
		// SETTING UP JFRAME
		receipt.setSize(700,800);
		receipt.setLocationRelativeTo(null);
		receipt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// CALCULATE NUMBER OF LINES
		int count = 0;
			
		// ADD LINES TO FRAME
		organizer.add(receipt(String.format("Hi %s!", customerName),0));
		organizer.add(receipt(String.format(" "),0));
		organizer.add(receipt(String.format("*~~-~~*~~-~~*"),0));
		organizer.add(receipt(String.format("Thank you for shopping at"),0));
		organizer.add(receipt(String.format("The Trading Card Store"),0));
		organizer.add(receipt(String.format(" "),0));
		count += 7;
		for (Product I : Inventory)
		{
			if (I.getCount() > 0 )
			{
				organizer.add(receipt(String.format("%10s%s %s", " ", I.getCode(), I.getName()),1));
				organizer.add(receipt(String.format("%d x %.2f%10s", I.getCount(), I.getPrice(), " "),2));
				count += 2;
				if (I.getCount() >= 10)
				{
					organizer.add(receipt(String.format("10%% disc. for >= 10 items%10s", " "),2));
					organizer.add(receipt(String.format("$%.2f%10s", I.getCount()*I.getPrice() - get2Decimals(I.getCount()*I.getPrice()*0.1), " "),2));
					subtotal += I.getCount()*I.getPrice() - get2Decimals(I.getCount()*I.getPrice()*0.1);
					count += 2;
				}
				else
				{
					organizer.add(receipt(String.format("$%.2f%10s", I.getCount() * I.getPrice(), " "),2));
					subtotal += I.getCount()*I.getPrice();
					count += 1;
				}
			}
		}
		organizer.add(receipt(String.format(" "),0));
		if (payInFull)
		{
			organizer.add(receipt(String.format("5%% Discount for payment in full."),0));
			organizer.add(receipt(String.format("%-20s$%.2f", "Previous Subtotal:", subtotal),0));
			organizer.add(receipt(String.format("%-20s$%.2f", "Savings:", get2Decimals(subtotal*0.05)),0));
			organizer.add(receipt(String.format(" "),0));
			subtotal -= get2Decimals(subtotal*0.05);
			count += 4;
		}
		organizer.add(receipt(String.format("%-20s$%.2f", "Subtotal:", subtotal),0));
		organizer.add(receipt(String.format("%-20s$%.2f", "Tax", get2Decimals(subtotal*0.06)),0));
		subtotal += get2Decimals(subtotal*0.06);
		organizer.add(receipt(String.format("%-20s$%.2f", "Final Total:", subtotal),0));
		organizer.add(receipt(String.format(" "),0));
		organizer.add(receipt(String.format(".~~-~~.~~-~~."),0));
		organizer.add(receipt(String.format(" "),0));
		organizer.add(receipt(String.format("Thank you for shopping at The Trading Card Store!"),0));
		organizer.add(receipt(String.format("Please come again soon!"),0));
		count += 9;
		
		// KEY CLICK TO LEAVE WINDOW
		receipt.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            	// DO NOTHING
            }
			@Override
			public void keyTyped(KeyEvent e) {
				// DO NOTHING
			}
			@Override
			public void keyReleased(KeyEvent e) {
				receipt.setVisible(false);
            	return;
			}
        });
		
		// ADD TO FRAME AND DISPLAY
		organizer.setLayout(new GridLayout(count, 1, 40, 0));
		receipt.add(organizer, BorderLayout.CENTER);
		receipt.setVisible(true);
	}

 	private static JLabel receipt(String s, int orientation)
 	{
 		JLabel textOut = new JLabel(s);
 		// SET STYLE
 		textOut.setForeground(mainDarkBlue);
 		if (orientation == 1)
 		{
 	 		textOut.setHorizontalAlignment(JTextField.LEFT);
 		}
 		else if(orientation == 2)
 		{

 	 		textOut.setHorizontalAlignment(JTextField.RIGHT);
 		}
 		else
 		{
 	 		textOut.setHorizontalAlignment(JTextField.CENTER);
 		}
 		textOut.setFont(new Font("TimesRoman", Font.PLAIN, 18));
 		
 		return textOut;
 	}
 	
 	public static boolean printReceiptToFileFromGUI()
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
				System.out.println("Your receipt was saved as " + fileName + ". Thank you!");
		    }
			else
			{
				return false;
			}
			
		} catch (IOException e)
		{
			return false;		
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
			return true;
		} catch (IOException e)
		{
			return false;
		}
 	}

 	public static void displayPrintError()
 	{
 		JFrame error = new JFrame("Error");
 		error.setSize(350, 150);
 		error.setLocationRelativeTo(null);
 		error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		
 		JPanel org = new JPanel();
 		JLabel m1 = new JLabel("There was a problem writing to the file.");
 		m1.setForeground(mainDarkBlue);
 		m1.setHorizontalAlignment(JTextField.CENTER);
 		m1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
 		
 		JLabel m2 = new JLabel("Please remove all '[Customer Name] Receipt.txt'");
 		m2.setForeground(mainDarkBlue);
 		m2.setHorizontalAlignment(JTextField.CENTER);
 		m2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
 		
 		JLabel m3 = new JLabel("files from the directory, and try again.");
 		m3.setForeground(mainDarkBlue);
 		m3.setHorizontalAlignment(JTextField.CENTER);
 		m3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
 		
 		JLabel m4 = new JLabel("Press any key to leave this window.");
 		m4.setForeground(mainDarkBlue);
 		m4.setHorizontalAlignment(JTextField.CENTER);
 		m4.setFont(new Font("TimesRoman", Font.PLAIN, 14));
 		
 		error.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            	// DO NOTHING
            }
			@Override
			public void keyTyped(KeyEvent e) {
				// DO NOTHING
			}
			@Override
			public void keyReleased(KeyEvent e) {
				error.setVisible(false);
            	return;
			}
        });
 		
 		
 		//org.setLayout(new GridLayout(3,1));
 		org.add(m1, BorderLayout.NORTH);
 		org.add(m2, BorderLayout.CENTER);
 		org.add(m3, BorderLayout.CENTER);
 		org.add(m4, BorderLayout.SOUTH);
 		error.add(org, BorderLayout.CENTER);
 		error.setVisible(true);
 	}
}



