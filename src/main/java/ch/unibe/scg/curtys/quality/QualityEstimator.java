package ch.unibe.scg.curtys.quality;

import ch.unibe.scg.curtys.bugreportquality.configuration.Configuration;
import ch.unibe.scg.curtys.bugreportquality.configuration.ConfigurationException;
import ch.unibe.scg.curtys.bugreportquality.configuration.ConfigurationLoader;
import ch.unibe.scg.curtys.bugreportquality.network.BayesianNetwork;
import ch.unibe.scg.curtys.bugreportquality.score.ScoreMapping;
import ch.unibe.scg.curtys.vectorization.Vector;
import ch.unibe.scg.curtys.vectorization.VectorizationEngine;
import ch.unibe.scg.curtys.vectorization.issue.Issue;

import java.io.IOException;

/**
 * An estimator for the quality of issues. The Quality scores reach from 1-5,
 * where higher is better.
 * @author curtys
 */
public class QualityEstimator implements ScoreEstimator {

	private static BayesianNetwork network;
	private static ScoreMapping mapper;

	/**
	 * Creates an instance of a issue quality estimator, able to give scores from 1-5.
	 */
	public QualityEstimator() {
		Configuration config = null;

		try {
			config = ConfigurationLoader.load("bayesiannetwork/config.yaml");
			network = BayesianNetwork.create(config);
			mapper = ScoreMapping.create(config);
		} catch (IOException | ConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns an estimation of the given issues score.
	 * The score is integer representation of the issues quality from 1-5,
	 * where higher is better.
	 * @param issue the {@link Issue} in question
	 * @return int score value from 1-5
	 */
	@Override
	public int score(Issue issue) {
		return score(usefulness(issue));
	}

	/**
	 * Returns the integer score representation (1-5, higher is better)
	 * to which the given probability is mapped.
	 * @param usefulness probability
	 * @return int score value from 1-5
	 */
	@Override
	public int score(double usefulness) {
		return mapper.score(usefulness);
	}

	@Override
	public double usefulness(Issue issue) {
		int[] vec = createVector(issue);
		return network.query(vec, "quality");
	}

	private int[] createVector(Issue issue) {
		VectorizationEngine engine = VectorizationEngine.builder()
				.verbose(false).integrateLabels(false).useDefaults()
				.build();

		Vector vec = engine.vectorize(issue);
		return vec.getElements();
	}

}
