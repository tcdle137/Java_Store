package Assignment4;

public class Product 
{
	// PRIVATE VARIABLE DECLARATION 
	private String code;
	private String name;
	private String description;
	private double price;
	private int count;
	
	// CONSTRUCTOR
	public Product()
	{
		// DOES NOTHING SO THE STRING VALUES CAN BE CHANGED LATER
	}
	public Product(String inCode, String inName, String inDes, double inPrice)
	{
		code = inCode;
		name = inName;
		description = inDes;
		price = inPrice;
		count = 0;
	}
	
	// ADD TO COUNT
	public void addCount(int input)
	{
		count += input;
	}
	
	// GET FUNCTIONS
	public String getCode()
	{
		return code;
	}
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public double getPrice()
	{
		return price;
	}
	public int getCount()
	{
		return count;
	}
	
	// SET FUNCTIONS
	public void setCode(String input)
	{
		code = input;
	}
	public void setName(String input)
	{
		name = input;
	}
	public void setDescription(String input)
	{
		description = input;
	}
	public void setPrice(double input)
	{
		price = input;
	}
}
