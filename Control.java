package Program;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Control 
{

	static ArrayList<String> Test;
	public static void main(String[] args) 
	{
		double accuracy = 0.0;
		/*NaiveBayes nb = new NaiveBayes(5, "MLdata.csv", 5);
		nb.GetAllProbabilities("No");
		Test = nb.calculateLikeliehood();
		System.out.println(Test);
		Test = nb.calculateLikeliehood();
		System.out.println(Test);*/
		
		//NaiveBayesLogic nbl = new NaiveBayesLogic(5, "MLdata.csv", 5);
		//Test = nbl.calculateLikeliehood();
		NaiveBayesTraining nbt1 = new NaiveBayesTraining(5, "TrainingData1.csv", 5, "Yes", "No");
		nbt1.guessSegment("TestData1.csv");
		NaiveBayesTraining nbt2 = new NaiveBayesTraining(5, "TrainingData2.csv", 5, "Yes", "No");
		nbt2.guessSegment("TestData2.csv");
		NaiveBayesTraining nbt3 = new NaiveBayesTraining(5, "TrainingData3.csv", 5, "Yes", "No");
		nbt3.guessSegment("TestData3.csv");
		NaiveBayesTraining nbt4 = new NaiveBayesTraining(5, "TrainingData4.csv", 5, "Yes", "No");
		nbt4.guessSegment("TestData4.csv");
		
		accuracy = (nbt1.getAccuracy() + nbt2.getAccuracy() + nbt3.getAccuracy() + nbt4.getAccuracy())/4;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("NaiveBayes Accuracy: "+df.format(accuracy*100)+"%");
		
		GUI g = new GUI("Euntrepreneure test");



	}
}
