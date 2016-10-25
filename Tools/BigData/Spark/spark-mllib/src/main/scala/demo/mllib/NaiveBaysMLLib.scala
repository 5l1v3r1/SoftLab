package demo.mllib

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * Sourced from: https://github.com/adahnlim/Mastering_Apache_Spark_Exam
 * 
 * @author hikmat
 */
object NaiveBaysMLLib extends App{
  val dataFile = "data/Result.csv"

  println("Hello ML Lib!" )

  val sparkConf = new SparkConf()
    .setAppName("Spark ML Lib Naive Bays demo")
    .setMaster("local") // set yarn if you want to run over hadoop

  val sparkCtx = new SparkContext(sparkConf)
  val csvData = sparkCtx.textFile(dataFile)

  val inDataRDD = csvData.map( csvLine => {
    val colData = csvLine.split(',')
    LabeledPoint(colData(0).toDouble, Vectors.dense(colData(1).split(' ').map(_.toDouble)))
  })

  // 
  val Array(trainSet,testSet) = inDataRDD.randomSplit(Array(0.7,0.3),seed= 13L)

  // 
  val nbTrained = NaiveBayes.train(trainSet)

  // 
  val nbPredict = nbTrained.predict(testSet.map(_.features))

  // 
  val predictionAndLabel = nbPredict.zip(testSet.map(_.label))

  // 
  val accuracy = 100.0 * predictionAndLabel.filter(x => x._1 == x._2).count() / testSet.count()

  println("Accuracy : " + accuracy)

}
