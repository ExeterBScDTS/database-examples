package uk.ac.ex.student.bob;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

import org.bson.Document;
import uk.ac.ex.student.bob.PotholeReport;

public class NoSqlDemoTest {

  static final int QTY = 20;
  MongoCollection<Document> collection;

  @Before
	public void init() {
      MongoClient mongoClient = MongoClients.create();
      MongoDatabase database = mongoClient.getDatabase("test");
      collection = database.getCollection("testCollection");
      collection.createIndex(Indexes.geo2dsphere("location"));
		  collection.drop();

      for (int i = 0; i < QTY; i++) {
        PotholeReport sample_report = new 
            PotholeReport.Builder(-73.92502,40.8279556).build();
            collection.insertOne(sample_report.toDocument());
		  }
	}
 
	@Test
	public void countTest() {
    long count = 0;
    count = collection.countDocuments();
		assertEquals(QTY, count);
	}
  }