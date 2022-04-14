package Program;

import java.util.ArrayList;

/* Author: Stephen Mester C20483376
 * IDE: Eclipse IDE
 * Language: Java SE 17.0.2
 * 
 * Class Description: Class to calculate multiple types of probability given a file with data to be used for Naive Bayes
*/

public class ProbabilityCalculator extends FileHandler implements ProbabilityEquations
{
	//Attributes 
	//To avoid redundancy the answer list is stored inside a variable inside the class
	ArrayList<String> Entrepreneur = new ArrayList<String>();
	int resultCol;
	int yesCount;
	int noCount;
	////
	
	//Constructor that takes a file as well as an attribute ArrayList it then selects two inputted values to insert into this ArrayList
	//To do this the constructor takes the column number of the attribute as well as the column number of the answer column to calculate probability
	public ProbabilityCalculator(String fileName, int resultCol)
	{
		super(fileName);
		this.resultCol = resultCol;
		
		//Hard codded Values 
		//Sets the euntrepeneur list to contain both desired values
		super.getCol(resultCol, "Yes", "No", Entrepreneur);
		
		
	}

	//Methods
	
	//Method to find out the probability of an attribute given he/she is or isnt an entrepreneur
	public Double GetProbability(String Value, ArrayList<String> List, String EntrepreneurVal) 
	{
		
		double count = 0;
		double total = 0;
		
		//For loop to check entire list
		for(int i = 0; i < List.size(); i++)
		{
			//If statement to get the number of occurances of the attribute given the desired value of entrepreneur adds to a counter
			if (List.get(i).equals(Value) && Entrepreneur.get(i).equals(EntrepreneurVal))
			{
				count += 1;
			}
			
			//If statement to catch all instances where entrepreneur is the correct value adds to a total counter
			if (List.get(i).equals(Value))
			{
				total += 1;
			}
		}
		
		//Returns the probability of the attribute value occuring given the desired entrepreneur value
		return (count/total);
		
	}

	//Method to get prior probability
	public Double GetPriorProbability(String Value) 
	{
		double count = 0;
		
		//Searches entire list for probability of value
		for(int i = 0; i < Entrepreneur.size(); i++)
		{
			if (Entrepreneur.get(i).equals(Value))
			{
				count += 1;
			}
		}
		//Gets the probability of value occuring given the entire list
		return count/(double)Entrepreneur.size();
	}





}
