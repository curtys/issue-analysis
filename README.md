# Integration
This project can be added as Maven dependency. A snapshot repository is provided. In your project's pom add:
 ```
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
 
 # Usage
 
 Two tools are provided to analyse issues:
 * Issue type classifier
 * Quality estimator
 
 ## Classifier
 There are two issue classifiers: A binary classifier, which predicts if the 
 issue in question is a bug or not, and a multi-class classifier with finer 
 type distinction. Use the binary classifier if you just want to know whether 
 or not the issue in question represents a bug.
 ```
 Classifier classifier = new BinaryClassifier();
 Prediction prediction = classifier.query(issue);
 /* get the label with the highest probability */
 String type = prediction.getBestClassLabel();
 ```
 
 ## Quality Estimator
 ```
 ScoreEstimator estimator = new QualityEstimator();
 int score = estimator.score(issue);
 ```
