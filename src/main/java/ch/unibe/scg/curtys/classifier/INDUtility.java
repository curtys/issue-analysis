package ch.unibe.scg.curtys.classifier;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

/**
 * Utility class to create and manipulate {@link INDArray}
 * @author curtys
 */
public final class INDUtility {

	public static INDArray createVector(int[] arr) {
		double[] dArr = Arrays.stream(arr).asDoubleStream().toArray();
		return createVector(dArr);
	}

	public static INDArray createVector(double[] arr) {
		INDArray indArray = Nd4j.create(arr);
		return indArray;
	}

	public static INDArray createVector(float[] arr) {
		INDArray indArray = Nd4j.create(arr);
		return indArray;
	}
}
