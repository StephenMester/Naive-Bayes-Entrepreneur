package Program;

import java.util.ArrayList;

import java.util.Scanner;

/* Author: Stephen Mester C20483376
 * IDE: Eclipse IDE
 * Language: Java SE 17.0.2
 * 
 * Class Description: Class to Get all the values and calculations needed to calculate values needed to preform guesses and calculations 
*/

public class NaiveBayes extends ProbabilityCalculator
{
	//Attributes
	String fileName;
	ArrayList<ArrayList<String>>AllAttributes = new ArrayList<>();
	ArrayList<String> Values = new ArrayList<>();
	ArrayList<Double> AttributeProb = new ArrayList<>();
	int numAttributes = 0;
	int resultCol;
	////

	
	//Naive Bayes constructor simplified to 3 paramaters
	public NaiveBayes(int numAttributes, String fileName, int resultCol) 
	{
		super(fileName, resultCol);
		this.fileName = fileName;
		this.numAttributes = numAttributes;
		this.resultCol = resultCol;
		
		//Values hardcodded for now looking for solution ******************************************************8
		Values.add("Yes");
		Values.add("No");
		Values.add("Male");
		Values.add("Female");
		Values.add("Urban");
		Values.add("Rural");
		
		//For loop to initialize the frequency of all variables given
		for (int i = 0; i<numAttributes; i++)
		{
			
			//Temporary array created to store values
			ArrayList<String> arr = new ArrayList<String>();
			
			//FileHandler class called to get values of each column 
			FileHandler FH = new FileHandler(fileName);
			
			//Values by default set to Yes No to save computing power 
			FH.getCol(i, "Yes", "No", arr);
			
			//If statement for if the value isnt Yes No
			if (arr.isEmpty())
			{
				
				//For loop incrementing in pairs of 2 to check for each pair of values
				for (int j = 2; j<Values.size(); j+=2)
				{
					//File handler takes 2 values from values array and stores into temp array
					//System.out.println(Values.get(j)+Values.get(j+1));
					FH.getCol(i, Values.get(j), Values.get(j+1), arr);
					
					if (arr.isEmpty() == false)
					{
						break;
					}
					
				}
			}
			
			//If to ensure empty lists arent added to AllAttributes
			if (arr.isEmpty() == false)
			{
				AllAttributes.add(arr);
			}
		}
	}
	
	// Method to store all the probabilities given the answer value ie probability of urban given they are entrepreneur 
	public ArrayList<Double> GetAllProbabilities (String AnswerVal)
	{
		//Temp array to return values
		ArrayList<Double> probabilityList = new ArrayList<>();
		
		//For loop to cycle through all of the attributes
		for (int i = 0; i<AllAttributes.size(); i++)
		{
			//Counter to break when both probabilities are found then reset to 0
			int counter = 0;
			
			//For loop to cycle through all the values till it find 2 that match
			for(int j = 0; j<Values.size(); j++)
			{

				Double probability = super.GetProbability(Values.get(j),AllAttributes.get(i),AnswerVal);

				//Checks that probability has a value ie its a valid value
				if (probability.toString() != "NaN")
				{
					//Adds probability to the temp array and increments counter
					probabilityList.add(probability);
					counter += 1;
					
					//Breaks out of loop if coubter is 2
					if (counter == 2)
					{
						//return probabilityList;
						break;
					}
				}
				
				//Breaks out of for loop
				if (counter == 2)
				{
					break;
				}
			}	
		}

		// Returns list of all probabilities given answerval
		System.out.println("This: "+probabilityList);
		return probabilityList;
	}
}
