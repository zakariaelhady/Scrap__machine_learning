package ML;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.Stacking;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.Copyable;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.core.Utils;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Copy;
import weka.filters.unsupervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemovePercentage;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;


public class Classification {

	public static void main(String[] args) throws Exception {
		/*
		 * Load the data
		 */
		DataSource source = new DataSource("offre.arff");
		Instances data = source.getDataSet();
		System.out.println(data.numInstances() + " instances loaded.");
		//System.out.println(data.toString());

		// remove id attribute
		String[] opts = new String[] { "-R", "1" };
		Remove remove = new Remove();
		remove.setOptions(opts);
		remove.setInputFormat(data);
		data = Filter.useFilter(data, remove);
		
		//split data 
		Instances[] inst=SplitData(data);
	    Instances traindata = inst[0];
	    Instances testdata =inst[1] ;
		
	    //Save dataset to a new file
//	    DatasetSaver(traindata,"train.arff");
//	    DatasetSaver(testdata,"test.arff");
		
		
		 
	    	
		/*
		 * Label selection
		 */
	    String str="domaine"; //our label
		traindata.setClassIndex(traindata.attribute(str).index());
		testdata.setClassIndex(testdata.attribute(str).index());
		// nominal to binary
 		Instances binary_traindata=nominaltobinary(traindata,str);
 		Instances binary_testdata=nominaltobinary(testdata,str);
		binary_traindata.setClassIndex(binary_traindata.attribute(str).index());
		binary_testdata.setClassIndex(binary_testdata.attribute(str).index());

	
		/*
		 * Build a decision tree
		 */
//		String[] options = new String[1];
//		options[0] = "-U";
//		J48 tree = new J48();
//		tree.setOptions(options);
//		tree.buildClassifier(binary_traindata);
		//System.out.println(tree);
//		weka.core.SerializationHelper.write("models/"+str+"/j48.model", tree);
		J48 tree=(J48)weka.core.SerializationHelper.read("models/"+str+"/j48.model");
		
	
		/*
		 * Visualize decision tree
		 */
		TreeVisualizer tv = new TreeVisualizer(null, tree.graph(),
				new PlaceNode2());
		JFrame frame = new javax.swing.JFrame("Tree Visualizer");
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(tv);
		frame.setVisible(true);
		tv.fitToScreen();
	
		
		//RandomForest
//		RandomForest randomforest=new RandomForest();
//		randomforest.buildClassifier(traindata);
		//System.out.println(randomforest);
//		weka.core.SerializationHelper.write("models/"+str+"/randomforest.model", randomforest);
		RandomForest randomforest=(RandomForest)weka.core.SerializationHelper.read("models/"+str+"/randomforest.model");
		
		//NaiveBayes
//		NaiveBayes naivebayes=new NaiveBayes();
//		naivebayes.buildClassifier(traindata);
		//System.out.println(naivebayes);
//		weka.core.SerializationHelper.write("models/"+str+"/naivebayes.model", naivebayes);
		NaiveBayes naivebayes=(NaiveBayes)weka.core.SerializationHelper.read("models/"+str+"/naivebayes.model");
		
		//AdaBoostM1
//		AdaBoostM1 adaboost=new AdaBoostM1();
//		adaboost.setClassifier(new NaiveBayes());
//		adaboost.setNumIterations(20);
//		adaboost.buildClassifier(traindata);
		//System.out.println(adaboost);
//		weka.core.SerializationHelper.write("models/"+str+"/adaboost.model",adaboost);
		AdaBoostM1 adaboost=(AdaBoostM1)weka.core.SerializationHelper.read("models/"+str+"/adaboost.model");
		
		//Stacking
//		Stacking stacker=new Stacking();
//		stacker.setMetaClassifier(new J48());
//		Classifier[] classifiers= {new J48(),new NaiveBayes(),new RandomForest()};
//		stacker.setClassifiers(classifiers);
//		stacker.buildClassifier(traindata);
//		weka.core.SerializationHelper.write("models/"+str+"/stacker.model",stacker);
		Stacking stacker=(Stacking)weka.core.SerializationHelper.read("models/"+str+"/stacker.model");

		
		
		/*
		 * Evaluation
		 */

		Classifier cl = new J48();
		Evaluation eval = new Evaluation(binary_traindata);
		eval.crossValidateModel(cl, binary_testdata, 10, new Random(1), new Object[] {});
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toMatrixString());
		
		ArrayList<Classifier>models = new ArrayList<Classifier>();
		models.add(stacker);
		models.add(adaboost);
		models.add(naivebayes);
		models.add(randomforest);
		Object[] evalModels=EvaluateModels(traindata, testdata,models,eval.pctCorrect());
		System.out.println("the best model is : "+evalModels[0]+" \t Correctly classified : "+evalModels[1]+" %");
		
		/*
		 * Classify new instance.
		 */
		String poste,localisation,domaine,contrat,entreprise,salaire,niveau_etude,competences;
		poste="Développeur java";
		localisation="Casablanca";
		domaine="Informatique / Multimédia / Internet";
		contrat="A discuter";
		entreprise="onepay";
		salaire="A discuter";
		niveau_etude="Bac plus 3";
		competences="java,javascript,angular,jee,oracle";
				
		String[] attributs= {"poste="+poste,"localisation="+localisation,"domaine="+domaine,"contrat="+contrat,
				"entreprise="+entreprise,"salaire="+salaire,"niveau_etude="+niveau_etude,"competences="+competences};
		Instance testinst=PredictInstance(binary_traindata,attributs);
		testinst.setDataset(binary_traindata);
		
		double label = tree.classifyInstance(testinst);
		System.out.println("J48 -->"+binary_traindata.classAttribute().value((int) label));
        
		
		double[] vals = new double[traindata.numAttributes()];
		vals[0]=traindata.attribute(0).indexOfValue(poste);
		vals[1] = traindata.attribute(1).indexOfValue(localisation);
		vals[2] = traindata.attribute(2).indexOfValue(domaine);
		vals[3] = traindata.attribute(3).indexOfValue(contrat); 
		vals[4] =traindata.attribute(4).indexOfValue(entreprise);
		vals[5] = traindata.attribute(5).indexOfValue(salaire);
		vals[6] =traindata.attribute(6).indexOfValue(niveau_etude);
		vals[7] = traindata.attribute(7).indexOfValue(competences);
		
		Instance offre = new DenseInstance(1.0,vals);
		//Assosiate your instance with Instance object in this case dataRaw
		offre.setDataset(traindata); 

		double label1 = randomforest.classifyInstance(offre);
		System.out.println("RandomForest -->"+traindata.classAttribute().value((int) label1));
		
		double label2 = naivebayes.classifyInstance(offre);
		System.out.println("NaiveBayes -->"+traindata.classAttribute().value((int) label2));

		double label3 = adaboost.classifyInstance(offre);
		System.out.println("AdaBoost -->"+traindata.classAttribute().value((int) label3));
		
  		double label4 = stacker.classifyInstance(offre);
		System.out.println("Stacking -->"+traindata.classAttribute().value((int) label4));
		
		
	}
	
	
	

	public static Object[] EvaluateModels(Instances dataset,Instances testdata,ArrayList<Classifier>models,double Correct) throws Exception {
		
		String Bestmodel="";
		double pctcorrect=0;
		for(Classifier cl : models ) {
			Evaluation eval = new Evaluation(dataset);
			eval.crossValidateModel(cl, testdata, 10, new Random(1), new Object[] {});
			if(eval.pctCorrect()>pctcorrect) {
				Bestmodel=cl.getClass().getSimpleName();
				pctcorrect=eval.pctCorrect();
			}
			//System.out.println(eval.toMatrixString());
		}
		
		if(Correct>pctcorrect) {
			Bestmodel="J48";
			pctcorrect=Correct;
		}
		
		return new Object[] {Bestmodel,pctcorrect};
	}
	
	
	public static Instance PredictInstance(Instances data,String[] attributs) {
		Instances predict=data;
		
		for (int i = predict.numInstances() - 1; i >= 0; i--) {
		    predict.delete(i);
		}
		Instance inst=new DenseInstance(predict.numAttributes());
		for(int i=0;i<predict.numAttributes();i++) {
			inst.setValue(i, 0);
		}
		predict.add(inst);
		
		int index=0;
		for(int i=0;i<predict.numAttributes();i++) {
			Attribute attr=predict.attribute(i);
			for(String k : attributs) {
				if(k.equals(attr.name()) ) {
					index=attr.index();
					predict.firstInstance().setValue(index, 1);
				}
			}
		}
		return predict.firstInstance();
	}
	
	
	public static Instances[] SplitData(Instances data) throws Exception {
	
		// Remove testpercentage from data to get the train set
	    RemovePercentage rp = new RemovePercentage();
	    rp.setInputFormat(data);
	    rp.setPercentage(20);    //20% of our data will be removed
	    Instances traindata = Filter.useFilter(data, rp);
	    
	    // Remove trainpercentage from data to get the test set
	    rp = new RemovePercentage();
	    rp.setInputFormat(data);
	    rp.setPercentage(80);
	    Instances testdata = Filter.useFilter(data, rp);
	    
	    Instances[] inst= {traindata,testdata};
	    return inst;
	}
	
	
	public static void DatasetSaver(Instances data,String name) throws Exception {
		//Save dataset to a new file
		ArffSaver saver=new ArffSaver();
		saver.setInstances(data);
		saver.setFile(new File("data/"+name));
		saver.writeBatch();
	}
	
	public static Instances nominaltobinary(Instances data,String label) throws Exception {
		int index =data.attribute(label).index()+1;
		String s="";
		for(int i=1;i<data.numAttributes();i++) {
			if(i!=index) {
				s+=i+",";
			}
		}
		s=s.substring(0, s.length() - 1);
		
		NominalToBinary nominalToBinary = new NominalToBinary();
		nominalToBinary.setInputFormat(data);
		String[] op = {"-N","-R", s}; // the index(es) of the nominal feature(s) 
		nominalToBinary.setOptions(op);
		Instances newdata = Filter.useFilter(data, nominalToBinary);
		return newdata;
	}
}
