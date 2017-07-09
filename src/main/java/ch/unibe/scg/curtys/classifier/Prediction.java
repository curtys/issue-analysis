package ch.unibe.scg.curtys.classifier;

import ch.unibe.scg.curtys.vectorization.label.LabelMapper;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.*;

/**
 * This class converts and stores the results from a model.
 * @author curtys
 */
public class Prediction {

	private Map<String, Float> probabilities;

	/**
	 * Constructs a Prediction instance out of the model results,
	 * containing the possibilities of all class labels.
	 * @param modelOutput the result of the model
	 * @param labels the class labels the model uses
	 */
	public Prediction(INDArray modelOutput, LabelMapper labels) {
		assert modelOutput.isVector() && modelOutput.length() == labels.size();
		this.probabilities = new LinkedHashMap<>();
		float[] data = modelOutput.data().asFloat();
		for (int i = 0; i < data.length; i++) {
			String label = labels.get(i);
			this.probabilities.put(label, data[i]);
		}
	}

	/**
	 * Returns a Map with all class labels and their probabilities.
	 * @return the Map with the class labels as keys.
	 */
	public Map<String, Float> getProbabilities() {
		return this.probabilities;
	}

	/**
	 * Returns the probability of the given class label.
	 * @param label the class label
	 * @return the probability as float value or null if there is no
	 * prediction for the given label.
	 */
	public float probabilityOf(String label) {
		return this.probabilities.get(label);
	}

	/**
	 * Returns the class label with the highest probability.
	 * @return best class label as String
	 */
	public String getBestClassLabel() {
		return Collections.max(this.probabilities.entrySet(),
				Comparator.comparingDouble(Map.Entry::getValue)).getKey();
	}

	/**
	 * Returns all class labels that were predicted.
	 * @return a Set with the class labels.
	 */
	public Set<String> labels() {
		return this.probabilities.keySet();
	}
}
