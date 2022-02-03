package ML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.meta.Stacking;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.Instances;


public class cluster {

	public static void main(String[] args)throws Exception {
		//load data
		Instances data = new Instances(new BufferedReader(new FileReader("data/train.arff")));
		
		// new instance of clusterer
		EM model = new EM();
		 //build the clusterer
		model.buildClusterer(data);
	
		
		System.out.println(model);
		
		double logLikelihood = ClusterEvaluation.crossValidateModel(model, data, 10, new Random(1));
		System.out.println(logLikelihood);
	}

}
