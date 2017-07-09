package ch.unibe.scg.curtys.classifier;

import ch.unibe.scg.curtys.vectorization.issue.Issue;
import ch.unibe.scg.curtys.vectorization.label.LabelMapper;
import ch.unibe.scg.curtys.vectorization.label.SimpleLabels;

/**
 * Multiclass classifier for issues. This classifier predicts all class labels in {@link SimpleLabels}.
 * Use {@link BinaryClassifier} instead, if you only need to know the
 * probability of the {@link Issue} representing a bug or not.
 *
 * @author curtys
 */
public class MulticlassClassifier extends AbstractClassifier {

	private final static String MODEL_PATH = "/model/nnmutliclass.zip";

	/**
	 * Creates an instance of a multi-class classifier, predicting the labels in {@link SimpleLabels}.
	 */
	public MulticlassClassifier() {
		super();
	}

	@Override
	protected LabelMapper labelSource() {
		return new SimpleLabels();
	}

	@Override
	protected String getModelFilePath() {
		return MODEL_PATH;
	}
}
