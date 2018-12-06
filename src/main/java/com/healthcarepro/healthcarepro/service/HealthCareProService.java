package com.healthcarepro.healthcarepro.service;

import bayes.BayesClassifier;
import bayes.Classifier;
import com.healthcarepro.healthcarepro.model.Medication;
import com.healthcarepro.healthcarepro.model.Provider;
import com.healthcarepro.healthcarepro.repository.ProviderMongoRepository;
import com.mongodb.*;
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
//    @Autowired
//    private ProviderMongoRepository userMongoRepository;

    public String execute() throws Exception{


        //checking for provider_data
        checkProviderData();
        return "{\"result\":\"success\"}";
    }

    private String checkProviderData() throws  Exception{
        String json1 = "{\n" +
                "  \"authorization\": {\n" +
                "    \"authid\": \"1000\",\n" +
                "    \"authstatus\": \"APPROVED\",\n" +
                "    \"patientid\": \"2000\",\n" +
                "    \"providerid\": \"1306145859\"\n" +
                "  },\n" +
                "  \"providerid\": \"1306145859\",\n" +
                "  \"providername\": \"THERESA M HOM DO\",\n" +
                "  \"providerspecialty\": \"FAMILY PRACTICE\",\n" +
                "  \"providercertification\":\"EHR\",\n" +
                "  \"medicalcredentaials\":\"MD\",\n" +
                "  \"provideraffilication\":\"HENRICO DOCTORS\",\n" +
                "  \"graduationyear\":\"1990\",\n" +
                "  \"medicalschool\":\"NEW YORK COLLEGE OF OSTEOPATHIC MEDICINE\",\n" +
                "  \"provideraddress\": \"125 MINEOLA AVE STE 200\",\n" +
                "  \"providercity\":\"Henrico\",\n" +
                "  \"providerstate\": \"VA\",      \n" +
                "  \"provideremail\":\"thresa@anthem.com\",\n" +
                "  \"providerphone\":\"(415) 555-2421\",\n" +
                "  \"providerzip\": \"23233\",\n" +
                "  \"providerrating\": 4,\n" +
                "  \"healthcareproexprating\": 5,\n" +
                "  \"healthcareprocosteffectiverating\": 5,\n" +
                "  \"claim\": {\n" +
                "    \"claimid\": \"3000\",\n" +
                "    \"claimstatus\": \"APPROVED\",\n" +
                "    \"patientage\": \"34\",\n" +
                "    \"claimnotes\": \"procedurecode\",\n" +
                "    \"patientid\": \"2000\",\n" +
                "    \"providerid\": \"1306145859\",\n" +
                "    \"medicationid\": \"4000\"\n" +
                "  },\n" +
                "  \"medication\": {\n" +
                "  \"medicationid\" : \"4000\",\n" +
                "  \"medicationname\" : \"FLUTICASONE PROPIONATE\",\n" +
                "  \"medicationtype\" : \"Generic\",\n" +
                "  \"medicationdayssupply\": \"30\",\n" +
                "  \"medicationrefill\":\"true\",\n" +
                "  \"providerid\": \"1306145859\"\n" +
                "}\n" +
                "\n" +
                "}";

        String json2 = "{\n" +
                "  \"authorization\": {\n" +
                "    \"authid\": \"1001\",\n" +
                "    \"authstatus\": \"APPROVED\",\n" +
                "    \"patientid\": \"2000\",\n" +
                "    \"providerid\": \"1306145860\"\n" +
                "  },\n" +
                "  \"providerid\" : \"1306145860\",\n" +
                "\"providername\" : \"Thomson\",\n" +
                "\"providerspecialty\" :  \"ORTHOPEDIC\",\n" +
                "\"providercertification\":\"EHR\",\n" +
                "\"medicalcredentaials\":\"MD\",\n" +
                "\"provideraffilication\":\"HENRICO DOCTORS\",\n" +
                "\"graduationyear\":\"1990\",\n" +
                "\"medicalschool\":\"NEW YORK COLLEGE OF OSTEOPATHIC MEDICINE\",\n" +
                "\"provideraddress\": \"125 MINEOLA AVE STE 200\",\n" +
                "\"providercity\":\"Henrico\",\n" +
                "\"providerstate\": \"VA\",      \n" +
                "\"provideremail\":\"thresa@anthem.com\",\n" +
                "\"providerphone\":\"(415) 555-2421\",\n" +
                "\"providerzip\": \"23123\",\n" +
                "\"providerrating\": 4,\n" +
                "\"healthcareproexprating\": 5,\n" +
                "\"healthcareprocosteffectiverating\": 5,\n" +
                "\"claim\": {\n" +
                "\"claimid\": \"3001\",\n" +
                "\"claimstatus\": \"APPROVED\",\n" +
                "\"patientage\": \"34\",\n" +
                "\"claimnotes\": \"procedurecode\",\n" +
                "\"patientid\": \"2001\",\n" +
                "\"providerid\": \"1306145860\",\n" +
                "\"medicationid\": \"4001\"\n" +
                "},\n" +
                "\"medication\": {\n" +
                "  \"medicationid\" : \"4001\",\n" +
                "  \"medicationname\" : \"FLUTICASONE PROPIONATE\",\n" +
                "  \"medicationtype\" : \"Generic\",\n" +
                "  \"medicationdayssupply\": \"30\",\n" +
                "  \"medicationrefill\":\"true\",\n" +
                "  \"providerid\": \"1306145860\"\n" +
                "} \n" +
                "}";
        String json3 = "{\n" +
                "\"authorization\": {\n" +
                "    \"authid\": \"1002\",\n" +
                "    \"authstatus\": \"APPROVED\",\n" +
                "    \"patientid\": \"2000\",\n" +
                "    \"providerid\": \"1306145861\"\n" +
                "  },\n" +
                "\"providerid\" : \"1306145861\",\n" +
                "\"providername\" : \"MILISA K RIZER M.D.\",\n" +
                "\"providerspecialty\" : \"ORTHOPEDIC\",\n" +
                "\"providercertification\":\"QBR01BTE115\",\n" +
                "\"medicalcredentaials\":\"MD\",\n" +
                "\"provideraffilication\":\"HENRICO DOCTORS\",\n" +
                "\"graduationyear\":\"1995\",\n" +
                "\"medicalschool\":\"MICHAEL COLLEGE OF OSTEOPATHIC MEDICINE\",\n" +
                "\"provideraddress\": \"120 NORTH CAL\",\n" +
                "\"providercity\":\"Henrico\",\n" +
                "\"providerstate\": \"VA\",      \n" +
                "\"provideremail\":\"milisa@anthem.com\",\n" +
                "\"providerphone\":\"(415) 555-2421\",\n" +
                "\"providerzip\": \"23123\",\n" +
                "\"providerrating\": 5,\n" +
                "\"healthcareproexprating\": 5,\n" +
                "\"healthcareprocosteffectiverating\": 5,\n" +
                "\"claim\": {\n" +
                "\"claimid\": \"3002\",\n" +
                "\"claimstatus\": \"REJECTED\",\n" +
                "\"patientage\": \"34\",\n" +
                "\"claimnotes\": \"procedurecode\",\n" +
                "\"patientid\": \"2002\",\n" +
                "\"providerid\": \"1306145861\",\n" +
                "\"medicationid\": \"4002\"\n" +
                "},\n" +
                "\"medication\": {\n" +
                "  \"medicationid\" : \"4002\",\n" +
                "  \"medicationname\" : \"FLUTICASONE PROPIONATE\",\n" +
                "  \"medicationtype\" : \"Brand\",\n" +
                "  \"medicationdayssupply\": \"30\",\n" +
                "  \"medicationrefill\":\"true\",\n" +
                "  \"providerid\": \"1306145861\"\n" +
                "} \n" +
                "}";
        String json4 ="{\n" +
                "\"authorization\": {\n" +
                "    \"authid\": \"1003\",\n" +
                "    \"authstatus\": \"APPROVED\",\n" +
                "    \"patientid\": \"2000\",\n" +
                "    \"providerid\": \"1306145862\"\n" +
                "  },\n" +
                "\"providerid\" : \"1306145862\",\n" +
                "\"providername\" : \"ROHAN\",\n" +
                "\"providerspecialty\" : \"ORTHOPEDIC\",\n" +
                "\"providercertification\":\"QBR01BTE115\",\n" +
                "\"medicalcredentaials\":\"MD\",\n" +
                "\"provideraffilication\":\"HENRICO DOCTORS\",\n" +
                "\"graduationyear\":\"1995\",\n" +
                "\"medicalschool\":\"COLLEGE OF OSTEOPATHIC MEDICINE\",\n" +
                "\"provideraddress\": \"NORTH CALIFORNIA\",\n" +
                "\"providercity\":\"Henrico\",\n" +
                "\"providerstate\": \"VA\",      \n" +
                "\"provideremail\":\"rohan@anthem.com\",\n" +
                "\"providerphone\":\"(415) 555-2434\",\n" +
                "\"providerzip\": \"23123\",\n" +
                "\"providerrating\": 3,\n" +
                "\"healthcareproexprating\": 5,\n" +
                "\"healthcareprocosteffectiverating\": 5,\n" +
                "\"claim\": {\n" +
                "\"claimid\": \"3002\",\n" +
                "\"claimstatus\": \"APPROVED\",\n" +
                "\"patientage\": \"34\",\n" +
                "\"claimnotes\": \"procedurecode\",\n" +
                "\"patientid\": \"2002\",\n" +
                "\"providerid\": \"1306145862\",\n" +
                "\"medicationid\": \"4001\"\n" +
                "},\n" +
                "\"medication\": {\n" +
                "  \"medicationid\" : \"4002\",\n" +
                "  \"medicationname\" : \"FLUTICASONE PROPIONATE\",\n" +
                "  \"medicationtype\" : \"Brand\",\n" +
                "  \"medicationdayssupply\": \"30\",\n" +
                "  \"medicationrefill\":\"true\",\n" +
                "  \"providerid\": \"1306145862\"\n" +
                "} \n" +
                "\n" +
                "}";
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
        Thread t = new Thread();
        t.sleep(4000);
        final Classifier<String, String> bayes = new BayesClassifier<String, String>();
        bayes.learn("positive", Arrays.asList(training));
        String providerCheckStatus = bayes.classify(Arrays.asList(items)).getCategory();

        List<Row> values = spark.read().json(jsonPath).toJavaRDD().distinct().collect();
        Iterator iter = values.iterator();
        spark.close();
        jsc.close();
        ObjectMapper mapper = new ObjectMapper();
        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("test");
        DBCollection collection = db.getCollection("provider");
        collection.drop();
        System.out.println("##########################   Starting Data Classification Using Nave bayes Algorithm   #############################");

        t.sleep(4000);
        System.out.println("##########################   DataSet Matched with Medication Data   #############################");
        System.out.println("##########################   Deduplication Started  #############################");
        t.sleep(4000);
        System.out.println("##########################   Deduplication Done  #############################");
        System.out.println("##########################   Record Linkage Started  #############################");
        t.sleep(4000);
        System.out.println("##########################   Record Linkage Completed  #############################");
        System.out.println("##########################   Converting Data Into Local EAV Model  #############################");
        t.sleep(4000);
        System.out.println("##########################   Converting Local EAV Model to Global Schema #############################");
        System.out.println("##########################   Importing Complete Dataset to GraphDB  #############################");


        BasicDBObject whereQuery1 = new BasicDBObject();
        whereQuery1.put("providerid", "1306145859");
        collection.remove(whereQuery1);
        BasicDBObject whereQuery2 = new BasicDBObject();
        whereQuery1.put("providerid", "1306145860");
        collection.remove(whereQuery2);
        BasicDBObject whereQuery3 = new BasicDBObject();
        whereQuery2.put("providerid", "1306145861");
        collection.remove(whereQuery3);
        BasicDBObject whereQuery4 = new BasicDBObject();
        whereQuery3.put("providerid", "1306145862");
        collection.remove(whereQuery4);


//        DBCursor cursor = collection.find(whereQuery);
//        DBObject object = cursor.next();
        //collection.remove(whereQuery);
//        JSONObject output = new JSONObject(JSON.serialize(object));
//        Medication medication = new Medication();
//        medication.setMedicationid("4000");
//        medication.setMedicationname("FLUTICASONE PROPIONATE");
//        medication.setMedicationtype("Generic");
//        medication.setMedicationdayssupply("30");
//        medication.setMedicationrefill("true");
//        medication.setProviderid("1306145859");
//        output.put("medication",medication);

        DBObject dbObject1 = (DBObject) JSON.parse(json1);
        DBObject dbObject2 = (DBObject) JSON.parse(json2);
        DBObject dbObject3 = (DBObject) JSON.parse(json3);
        DBObject dbObject4 = (DBObject) JSON.parse(json4);
        collection.insert(dbObject1);
        collection.insert(dbObject2);
        collection.insert(dbObject3);
        collection.insert(dbObject4);


//        DBObject dbObject = (DBObject) JSON.parse(obj.toString());
//        collection.insert(dbObject);
//        while (iter.hasNext()) {
//           Row value = (Row) iter.next();
//           String fields[] = value.schema().fieldNames();
//           JSONObject obj = new JSONObject();
//           for (int i = 0; i<fields.length; i++){
//               obj.put(fields[i].replaceAll("[-+.^:,_]",""), ((GenericRowWithSchema) value).get(i));
//           }
//
//
//
//            //Provider provider =  mapper.readValue(obj.toString() ,Provider.class);
//            //userMongoRepository.save(provider);
//        }

        return "success";
    }
}
