package ch.unibe.scg.curtys.classifier;

import ch.unibe.scg.curtys.vectorization.Vector;
import ch.unibe.scg.curtys.vectorization.VectorizationEngine;
import ch.unibe.scg.curtys.vectorization.issue.Issue;
import ch.unibe.scg.curtys.vectorization.label.LabelMapper;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static ch.unibe.scg.curtys.classifier.INDUtility.createVector;

/**
 * @author curtys
 */
public abstract class AbstractClassifier implements Classifier {

	private MultiLayerNetwork model;

	protected AbstractClassifier() {
		model = loadModel();
	}

	public Prediction query(Issue issue) {

		if (model == null) {
			return null;
		}

		VectorizationEngine engine = VectorizationEngine.builder()
				.verbose(false).integrateLabels(false).useDefaults()
				.build();

		Vector vec = engine.vectorize(issue);
		INDArray indArray = createVector(vec.getElements());
		INDArray output = model.output(indArray);

		return new Prediction(output, labelSource());
	}


	private MultiLayerNetwork loadModel() {
		File modelFile;
		MultiLayerNetwork model = null;
		try {
			modelFile = getModelFile();
			model = ModelSerializer.restoreMultiLayerNetwork(modelFile);
		} catch (ClassifierException | IOException e) {
			e.printStackTrace();
		}
		return model;
	}

	protected File getModelFile() throws ClassifierException {
		File f = Paths.get(this.getClass().getResource(getModelFilePath()).getPath()).toFile();
		if (!f.exists() && f.canRead()) throw new ClassifierException("Could not load model.");
		return f;
	}

	protected abstract LabelMapper labelSource();

	protected abstract String getModelFilePath();
}
