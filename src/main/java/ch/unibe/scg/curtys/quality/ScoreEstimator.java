package ch.unibe.scg.curtys.quality;

import ch.unibe.scg.curtys.vectorization.issue.Issue;

/**
 * @author curtys
 */
public interface ScoreEstimator {

	/**
	 * Returns an estimation of the given issues score.
	 * The score is integer representation of the issues quality.
	 * @param issue the {@link Issue} in question
	 * @return int score value
	 */
	int score(Issue issue);

	/**
	 * Returns the integer score representation to which the given probability is mapped.
	 * @param usefulness probability
	 * @return int score value
	 */
	int score(double usefulness);

	/**
	 * Returns a probability estimation of the "usefulness" of the given issue.
	 * @param issue issue the {@link Issue} in question
	 * @return probability predicting the "usefulness" of the issue
	 */
	double usefulness(Issue issue);
}
