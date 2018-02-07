# Issue Analysis

The Issue Analysis module provides two main functionalities: 
* **Issue report classification:** A binary and a multiclass classifier are integrated. The binary classifier can distinguish bugs and non-bugs. The multiclass classifier supports the types *bug*, *improvement* and *feature request*.
* **Bug report quality estimation:** For a given bug report, a score can be calculated based on its content.

## Integration
This project can be added as Maven dependency. A snapshot repository is provided. In your project's pom add:
 ```xml
 <repositories>
     <repository>
         <id>issue-analysis-mvn-repo</id>
         <url>https://raw.github.com/curtys/issue-analysis/mvn-repo/</url>
         <snapshots>
             <enabled>true</enabled>
             <updatePolicy>always</updatePolicy>
         </snapshots>
     </repository>
 </repositories>
 
<dependencies>
    <dependency>
        <groupId>ch.unibe.scg.curtys</groupId>
        <artifactId>issue-analysis</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
 ```
 Source and Javadocs can also be downloaded from the Maven repsitory.
 
 ## Usage
 
 Two tools are provided to analyse issues:
 * Issue type classifier
 * Quality estimator
 
 ### Classifier
 There are two issue classifiers: A binary classifier, which predicts if the 
 issue in question is a bug or not, and a multi-class classifier with finer 
 type distinction. Use the binary classifier if you just want to know whether 
 or not the issue in question represents a bug.
 ```java
// instantiate a binary classifier
Classifier binaryClassifier = new BinaryClassifier();

// or a multiclass classifier
Classifier multiclassClassifier = new MulticlassClassifier();

Prediction prediction = binaryClassifier.query(issue);

// get the label with the highest probability
String type = prediction.getBestClassLabel();

// get the probability of a given label
float probability = prediction.probabilityOf(label);

// get all class labels
Set<String> labelSet = prediction.labels();

// get a map with all labels and their probabilities
Map<String, Float> propabilitiesMap = prediction.getProbabilities();
 ```
 
 ### Quality Estimator
 Use the quality estimation to calculate a score for a bug report:
 ```java
QualityEstimator estimator = new QualityEstimator();

// calculate the score
int score = estimator.score(issue);

// get a map with the features as keys and a value 
// of either 1 (if feature is present) or 0 (otherwise)
Map<String, Integer> featureMap = 
		estimator.activationFeatures(vector);
 ```

## License
MIT