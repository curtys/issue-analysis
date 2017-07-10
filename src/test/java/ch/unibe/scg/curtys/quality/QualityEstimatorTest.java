package ch.unibe.scg.curtys.quality;

import ch.unibe.scg.curtys.vectorization.io.JsonIO;
import ch.unibe.scg.curtys.vectorization.issue.Issue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author curtys
 */
class QualityEstimatorTest {

	private static QualityEstimator estimator;
	private static Issue issue;

	@BeforeAll
	static void setUpClass() throws Exception {
		estimator = new QualityEstimator();
		Path p = Paths.get(QualityEstimator.class.getResource("/json/HTTPCLIENT-3.json").getPath());
		issue = JsonIO.mapIssue(p);

	}

	@Test
	void score() {
		assertNotNull(estimator.score(issue));
	}

	@Test
	void score1() {
		assertEquals(5, estimator.score(1));
	}

	@Test
	void usefulness() {
		assertNotNull(estimator.usefulness(issue));
	}

}