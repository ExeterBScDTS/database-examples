package uk.ac.ex.student.bob;


import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.client.model.geojson.Point;
import org.bson.Document;

// To find documents we use filters.
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.near;

public class NoSqlDemo {

    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("testCollection");
        collection.createIndex(Indexes.geo2dsphere("location"));

        PotholeReport sample_report = new 
            PotholeReport.Builder(-73.92502,40.8279556).build();

        collection.insertOne(sample_report.toDocument());

        // find documents
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
     };

    // Simple find example    
    collection.find(eq("reported by", "bob")).forEach(printBlock);

    collection.find(near("location",
    new Point(new Position( -73.92502,40.8279556 )),100.0,0.0 ) ).forEach(printBlock);

    }
}