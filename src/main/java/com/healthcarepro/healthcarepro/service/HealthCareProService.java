package com.healthcarepro.healthcarepro.service;

import bayes.BayesClassifier;
import bayes.Classifier;
import com.healthcarepro.healthcarepro.model.Provider;
import com.healthcarepro.healthcarepro.repository.ProviderMongoRepository;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class HealthCareProService {
    @Autowired
    private ProviderMongoRepository userMongoRepository;

    public String execute() throws Exception{


        //checking for provider_data
        checkProviderData();
        return "success";
    }

    private String checkProviderData() throws  Exception{
        SparkConf conf = new SparkConf().setAppName("healthcarepro").setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        SparkSession spark = SparkSession
                .builder()
                .getOrCreate();
        String providerTrainingPath = "src/main/resources/providertrainingData.txt";
        String jsonPath = "src/main/resources/provider.json";
        String items[] = spark.read().json(jsonPath).columns();
        List<String> trainingList = jsc.textFile(providerTrainingPath).collect();
        String training[] = trainingList.get(0).split("\\s");
        final Classifier<String, String> bayes = new BayesClassifier<String, String>();
        bayes.learn("positive", Arrays.asList(training));
        String providerCheckStatus = bayes.classify(Arrays.asList(items)).getCategory();
        List<Row> values = spark.read().json(jsonPath).toJavaRDD().distinct().collect();
        Iterator iter = values.iterator();
        while (iter.hasNext()) {
           Row value = (Row) iter.next();
           String fields[] = value.schema().fieldNames();
           JSONObject obj = new JSONObject();
           for (int i = 0; i<fields.length; i++){
               obj.put(fields[i].replaceAll("[-+.^:,_]",""), ((GenericRowWithSchema) value).get(i));
           }
        }
        spark.close();
        jsc.close();
        return "success";
    }
}
