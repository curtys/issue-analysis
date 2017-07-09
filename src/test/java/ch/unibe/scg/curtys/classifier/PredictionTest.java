package ch.unibe.scg.curtys.classifier;

import ch.unibe.scg.curtys.vectorization.label.LabelMapper;
import ch.unibe.scg.curtys.vectorization.label.SimpleLabels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author curtys
 */
class PredictionTest {

	private Prediction prediction;
	private float[] vector;
	private LabelMapper labels;

	@BeforeEach
	void setUp() {
		vector = new float[] { 0.2f, 0.5f, 0.3f};
		labels = new SimpleLabels();
		prediction = new Prediction(INDUtility.createVector(vector), labels);
	}

	@Test
	void getProbabilities() {
		Map<String, Float> expected = new HashMap<>();
		for (int i = 0; i < vector.length; i++) {
			expected.put(labels.get(i), vector[i]);
		}
		assertEquals(expected, prediction.getProbabilities());
	}

	@Test
	void probabilityOf() {
		int classLabel = 0;
		double expected = vector[classLabel];
		String label = labels.get(classLabel);
		assertEquals(expected, prediction.probabilityOf(label));
	}

	@Test
	void getBestClassLabel() {
		int classLabel = 1;
		String expected = labels.get(classLabel);
		assertEquals(expected, prediction.getBestClassLabel());
	}

	@Test
	void labels() {
		assertEquals(labels.getAll(), prediction.labels());
	}

}