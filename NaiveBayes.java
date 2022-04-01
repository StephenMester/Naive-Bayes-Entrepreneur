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
			int break_check = 0;
			
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
				for (int j = 2; i<Values.size(); j+=2)
				{
					//File handler takes 2 values from values array and stores into temp array
					FH.getCol(i, Values.get(j), Values.get(j+1), arr);
					
					//if to check if File handler found any values
					if (arr.isEmpty() == false)
					{
						//Local value to break out of both if and for to save computing power
						break_check = 1;
						break;
					}
					
					//if check to ensure it only breaks out of for if values are found
					if (break_check == 1)
						break;
				}
			}
			
			//If to ensure empty lists arent added to AllAttributes
			if (arr.isEmpty() == false)
			{
				AllAttributes.add(arr);
			}
			//Else statement to send debug message if values not found
			else
			{
				//number of attributes decrements by one
				numAttributes -= 1;
				System.out.println("Attribute not found");
			}
		}
	}
	
	// Method to store all the probabilities given the answer value ie probability of urban given they are entrepreneur 
	public ArrayList<Double> GetAllProbabilities (String AnswerVal)
	{
		//Temp array to return values
		ArrayList<Double> probabilityList = new ArrayList<>();
		
		//For loop to cycle through all of the attributes
		for (int i = 0; i<numAttributes; i++)
		{
			//Counter to break when both probabilities are found then reset to 0
			int counter = 0;
			
			//For loop to cycle through all the values till it find 2 that match
			for(int j = 0; j<Values.size(); j++)
			{
				
				//Initializes temporary probability var and calls GetProbability from Probability Calculator
				double probability = super.GetProbability(Values.get(j),AllAttributes.get(i),AnswerVal);
				
				//If to check that the value is present 
				if (probability != 0)
				{
					//Adds probability to the temp array and increments counter
					probabilityList.add(probability);
					counter += 1;
					
					//Breaks out of loop if coubter is 2
					if (counter == 2)
					{
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
		System.out.println(probabilityList);
		return probabilityList;
	}
	
	public ArrayList<String> calculateLikeliehood()
	{
		ArrayList<String> PersonAttributes = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		for (int i = 0; i<numAttributes; i++)
		{
			String answer;
			do
			{
				System.out.println("Enter attribute "+i);
				answer = sc.next();
				answer.strip();
				
				valid = false;
				String value1 = null;
				String value2 = null;
				
				while (value1 == null || value2 == null)
				{
					value1 = AllAttributes.get(i).get(0);
					
					for (int j = 0;j<(AllAttributes.get(i)).size();j++)
					{
	
						if(value1.equals(AllAttributes.get(i).get(j)) == false)
						{
							value2 = AllAttributes.get(i).get(j);
							break;
						}
						
					}
				}
				
				if (answer.equals(value1) || answer.equals(value2))
				{
					valid = true;
					PersonAttributes.add(answer);
					break;
				}
				
				
			}
			while (valid == false);
		}
		return PersonAttributes;
	}

}
