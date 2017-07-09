package ch.unibe.scg.curtys.classifier;

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
class ClassifierTest {

	private static Issue issue;

	@BeforeAll
	static void setUpClass() throws Exception {
		Path p = Paths.get(Classifier.class.getResource("/json/HTTPCLIENT-3.json").getPath());
		issue = JsonIO.mapIssue(p);
	}

	@Test
	void queryBinary() {
		Classifier classifier = new BinaryClassifier();
		Prediction prediction = classifier.query(issue);
		assertNotNull(prediction);
		assertEquals("BUG", prediction.getBestClassLabel());
	}

	@Test
	void queryMulticlass() {
		Classifier classifier = new MulticlassClassifier();
		Prediction prediction = classifier.query(issue);
		assertNotNull(prediction);
		assertEquals("BUG", prediction.getBestClassLabel());
	}

}