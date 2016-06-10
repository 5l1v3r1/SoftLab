package com.bigschool;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class SparkWordCountStandAloneMode {

    // spark stand-alone mode
    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf().setAppName("App_Name")//.setMaster("local");
               .setMaster("spark://localhost:7077");//.set("spark.ui.port","18080");

        // you can set all the spark configurations here

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> textFile = sc.textFile("file:/home/hdhamee/contributions/CoderLab/Tools/BigData/Spark/RawDataProcessor/data/input.txt");
        JavaRDD<String> words = textFile.flatMap(new FlatMapFunction<String, String>() {
            public Iterable<String> call(String s) {
                return Arrays.asList(s.split(";"));
            }
        });
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
            }
        });
        JavaPairRDD<String, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer a, Integer b) {
                return a + b;
            }
        });
        counts.saveAsTextFile("file:/home/hdhamee/contributions/CoderLab/Tools/BigData/Spark/RawDataProcessor/data/input_out.txt");

    }
}


