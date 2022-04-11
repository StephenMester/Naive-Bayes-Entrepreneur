package Program;

import java.util.ArrayList;

public class Control 
{

	static ArrayList<String> Test;
	public static void main(String[] args) 
	{
		/*NaiveBayes nb = new NaiveBayes(5, "MLdata.csv", 5);
		nb.GetAllProbabilities("No");
		Test = nb.calculateLikeliehood();
		System.out.println(Test);
		Test = nb.calculateLikeliehood();
		System.out.println(Test);*/
		
		//NaiveBayesLogic nbl = new NaiveBayesLogic(5, "MLdata.csv", 5);
		//Test = nbl.calculateLikeliehood();
		NaiveBayesTraining nbt = new NaiveBayesTraining(5, "MLDataMedium.csv", 5, "Yes", "No");
		nbt.guessSegment("MLdata3.csv");
		//NaiveBayesLogic nbl = new NaiveBayesLogic(5,"MLDataSmall.csv",5);
	}

}
