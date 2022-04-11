package Program;

import java.util.ArrayList;

/* Author: Stephen Mester C20483376
 * IDE: Eclipse IDE
 * Language: Java SE 17.0.2
 * 
 * Class Description: Class to preform high level logic for naive bayes using values calculated in previous classes 
*/

public class NaiveBayesLogic extends NaiveBayes 
{
	
	//Attributes
	ArrayList<Double> prob1 = new ArrayList<>();
	ArrayList<Double> prob2 = new ArrayList<>();
	
	ArrayList<String> person = new ArrayList<>();
	
	String Value1 = "Yes";
	String Value2 = "No";
	////

	//Constructor to call NaiveBayes
	public NaiveBayesLogic(int numAttributes, String fileName, int resultCol)
	{
		super(numAttributes, fileName, resultCol);
	}
	
	//Methods
	
	//Method to guess the probability of a person being a euntrepeneure or not
	public String guess (ArrayList<String> person, ArrayList<Double> probYes, ArrayList<Double> probNo)
	{
		//Local attributes
		int increment = 0;
		int counter = 0;
		Double likelyhood1 = 1.0;
		Double likelyhood2 = 1.0;
		/////
		
		//For loop to iterate through person -1 to avoid factoring the answer column
		for (int i = 0; i<person.size()-1; i++)
		{
			increment = 0;
			//for loop to go through every 2nd value from a value pair
			for (int j = 1; j < Values.size(); j+=2)
			{
				//if to find if the value is a 2nd value in the value pair and if so sets increment to 1
				if (person.get(i).equals(super.Values.get(j)))
				{
					increment = 1;
					break;
				}
			}

			//calculates likelyhood of yes and no using 2 probability lists and adding on the increment and counter to get the correct probability
			likelyhood1 = likelyhood1 * probYes.get((i+counter)+increment);
			likelyhood2 = likelyhood2 * probNo.get((i+counter)+increment);
			
			//counter to increment probYes and probNo by 2 inside the same loop
			counter+=1;

		}
			
		//Lastly multiplies the likelyhood by the overall likelyhood of yes and no
		likelyhood1 = likelyhood1 * super.GetPriorProbability(Value1);
		likelyhood2 = likelyhood2 * super.GetPriorProbability(Value2);
		
		//if else to get which value is more likely
		if (likelyhood1 > likelyhood2)
		{
			return Value1;
		}
		else if (likelyhood2 > likelyhood1)
		{
			return Value2;
		}
		else
		{
			System.out.println("Equally likely");
			return "neither";
		}

	}
	
}
