package Program;

import java.text.DecimalFormat;
import java.util.ArrayList;

/* Author: Stephen Mester C20483376
 * IDE: Eclipse IDE
 * Language: Java SE 17.0.2
 * 
 * Class Description: Class to train and test NaiveBayes algorithm
*/

public class NaiveBayesTraining extends NaiveBayesLogic

{
	//Attributes
	ArrayList<NaiveBayesLogic> NaiveBayes = new ArrayList<>();
	ArrayList<FileHandler> Files = new ArrayList<>();
	ArrayList<String> Data = new ArrayList<>();
	ArrayList<String> test = new ArrayList<>();
	ArrayList<Double> probYes = new ArrayList<>();
	ArrayList<Double> probNo = new ArrayList<>();
	
	double accuracy = 0.0;
	//
	
	//Constuctor to call naivebayesLogic and to calculate the probabilities given the training data called fileName
	public NaiveBayesTraining(int numAttributes, String fileName, int resultCol, String yesVal, String noVal)
	{
		
		super(numAttributes, fileName, resultCol);
		
		probYes.addAll(super.GetAllProbabilities(yesVal));
		probNo.addAll(super.GetAllProbabilities(noVal)); 
		
	}
	
	//Methods
	
	//Method to test the probability data by giving it a different file from the training data called fileName
	public ArrayList<String> guessSegment (String fileName)
	{
		//Calls seperate objects for naivebayeslogic and filehandler but only changing the fileName
		FileHandler file = new FileHandler(fileName);
		NaiveBayesLogic logic = new NaiveBayesLogic(super.numAttributes, fileName, super.resultCol);
		
		//templist to store all the results
		ArrayList<String> tempList = new ArrayList<>();

		String guess;
		String actual;
		double correct = 0;
		
		//For loop to iterate though every line in fileName
		for (int i = 0; i < file.getSize(); i++)
		{
			
			//For each iteration calls guess and stores value into templist
			tempList.add(logic.guess(file.getrow(i), probYes, probNo));
			
			//Also stores guess and actual value to check accuracy
			guess = logic.guess(file.getrow(i), probYes, probNo);
			actual = logic.Entrepreneur.get(i);

			//Values striped to ensure consistency
			guess.strip();
			actual.strip();
			
			//itterates correct if values match
			if (guess.equals(actual))
			{
				correct += 1;
			}

		}
		
		//calculates accuracy by dividing amount system got correct compared to the total amount
		accuracy = correct/(file.getSize());
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println(fileName+" Accuracy: "+df.format(accuracy*100)+"%");
		
		//Returns temp list
		return tempList;
	}
	
	//Method to get accuracy value
	public double getAccuracy()
	{
		return accuracy;
	}
	
}
