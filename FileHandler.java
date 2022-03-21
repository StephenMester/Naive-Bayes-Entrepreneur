package Program;

import java.io.*;
import java.util.*;


/* Author: Stephen Mester C20483376
 * IDE: Eclipse IDE
 * Language: Java SE 17.0.2
 * 
 * Class Description: Class to open sort and categorise data from a CSV file
 * to be used in a Naive Bayes machine learning program
*/

public class FileHandler 
{

	//Attributes 
	BufferedReader reader;
	String fileName;
	String delimiter;
	///
	
	
	//Constructor class
	//Constructor to get the name of the file for methods as well as to apply the correct delimiter depending on its suffix
	public FileHandler(String fileName)
	{	
		this.fileName = fileName;
		
		//If else statements to automatically set the delimiter if the file ends in a comonly used file suffix
		if (fileName.endsWith(".csv") == true)
		{
			this.delimiter = ",";
		}
		
		else if (fileName.endsWith(".tsv") == true)
		{
			this.delimiter = "/t";
		}
		
		//If no suffix is found delimiter is by default set to new line
		else
		{
			this.delimiter = "/n";
		}
		
	}
	
	//Methods
	
	/*Method to open a file and find a specific column of data which then gets cleaned to only accept 2 desired String values ([yes,no])
	  These two values are then stored into an ArrayList stored inside colList.
	  This method works for any delimiter needed for your specific file ["/n",",","/t"]
	  Make sure that a BufferedReader object is present outside of the method for the program to work*/ 
	public void getCol(int ColNumb, String Value1, String Value2, ArrayList<String> colList)
	{
		String line = "";
		//Try catch finally to make sure file is openable if openable finally to ensure file is closed after the operation
		try 
		{
			reader = new BufferedReader(new FileReader(fileName));
			
			//While loop to go through the file line by line till it reaches the end
			while((line = reader.readLine()) != null)
			{
				//For each line the row is split by the delimiter
				String[] row = line.split(delimiter);
				
				//Adds all desired values to the ArrayList inserted
				if(row[ColNumb].equals(Value1)||row[ColNumb].equals(Value2))
				{
					colList.add(row[ColNumb]);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Try catch to stop an error if the file failed to open in the first place
			try
			{
				reader.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
	}
	
	//Function to set a custom delimiter in the case that the file has a delimiter such as [:, ;, " or anything alse
	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}
}
