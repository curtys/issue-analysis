package ch.unibe.scg.curtys.classifier;

import ch.unibe.scg.curtys.vectorization.issue.Issue;
import ch.unibe.scg.curtys.vectorization.label.LabelMapper;
import ch.unibe.scg.curtys.vectorization.label.PseudoLabels;

/**
 * Binary classifier for issues. This classifier predicts the class labels "BUG" and "NOBUG".
 * Use this classifier instead of {@link MulticlassClassifier} if you only need to know the
 * probability of the {@link Issue} representing a bug or not.
 *
 * @author curtys
 */
public class BinaryClassifier extends AbstractClassifier {

	private final static String MODEL_PATH = "/model/nnbinary.zip";

	/**
	 * Creates an instance of a binary classifier, predicting the labels in {@link PseudoLabels}.
	 */
	public BinaryClassifier() {
		super();
	}

	@Override
	protected LabelMapper labelSource() {
		return new PseudoLabels();
	}

	@Override
	protected String getModelFilePath() {
		return MODEL_PATH;
	}

}
