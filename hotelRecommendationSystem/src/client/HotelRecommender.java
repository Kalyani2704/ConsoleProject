package client;
import java.lang.reflect.InaccessibleObjectException;
import weka.core.WekaPackageManager;
import config.PathHelper;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class HotelRecommender {
    public static void main(String[] args) throws Exception{
        try {
//        	WekaPackageManager.loadPackages(false);
            // Load dataset
            DataSource source = new DataSource(PathHelper.path+"hotel_dataset.arff");
            Instances data = source.getDataSet();
            // setting class attribute if the data format does not provide this information
            data.setClassIndex(data.numAttributes() - 1); // Set the class index (rating)

            // Filter
            StringToWordVector filter = new StringToWordVector();
            
            // Classifier
            SMOreg classifier = new SMOreg();

            // Build the classifier
            FilteredClassifier fc = new FilteredClassifier();
            fc.setFilter(filter);
            fc.setClassifier(classifier);
            fc.buildClassifier(data);
            
         // Evaluate the classifier
            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(fc, data, 10, new java.util.Random(1));
            
            System.out.println(eval.toSummaryString("\nResults\n======\n", false));
            System.out.println("Correlation coefficient: " + eval.correlationCoefficient());

            // Make predictions
            for (int i = 0; i < data.numInstances(); i++) {
                Instance instance = data.instance(i);
                double rating = fc.classifyInstance(instance);
                System.out.println("Predicted rating for instance " + i + ": " + rating);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


