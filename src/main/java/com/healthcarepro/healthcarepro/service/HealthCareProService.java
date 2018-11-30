package com.healthcarepro.healthcarepro.service;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HealthCareProService {
    public String execute() {
        SparkConf sparkConf = new SparkConf().setAppName("JavaNaiveBayesExample").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        // $example on$
        String path = "src/main/resources/data/provider.json";
        JavaRDD<String> inputData = jsc.textFile(path);
        // JavaRDD<LabeledPoint> inputData = MLUtils.loadLibSVMFile(jsc.sc(), path).toJavaRDD();
        System.out.println(inputData);
        for(String line:inputData.collect()){
            System.out.println(line);
        }
//        JavaRDD<LabeledPoint> training = tmp[0]; // training set
//        JavaRDD<LabeledPoint> test = tmp[1]; // test set
//        NaiveBayesModel model = NaiveBayes.train(training.rdd(), 1.0);
//        JavaPairRDD<Double, Double> predictionAndLabel =
//                test.mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));
//        double accuracy =
//                predictionAndLabel.filter(pl -> pl._1().equals(pl._2())).count() / (double) test.count();
//
//        // Save and load model
//        model.save(jsc.sc(), "target/tmp/myNaiveBayesModel");
//        NaiveBayesModel sameModel = NaiveBayesModel.load(jsc.sc(), "target/tmp/myNaiveBayesModel");
//        // $example off$


        return "success";
    }
}
