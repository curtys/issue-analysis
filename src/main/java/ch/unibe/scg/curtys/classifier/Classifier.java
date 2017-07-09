package ch.unibe.scg.curtys.classifier;

import ch.unibe.scg.curtys.vectorization.issue.Issue;

/**
 * Interface for a {@link Issue} classifier
 *
 * @author curtys
 */
public interface Classifier {

	/**
	 * Get the class predictions of the given issue
	 * @param issue the {@link Issue} to predict the classes for
	 * @return a {@link Prediction} with the probability of each class label
	 */
	Prediction query(Issue issue);

}
