import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.bson.Document;
import org.bson.json.JsonReader;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



public class MovieJSONReader {

	public static final String JSON_FILE="X:/movie.json";
	
	public static void read() throws IOException{
		
//		InputStream fis = new FileInputStream(JSON_FILE);
//		
//		//create JsonReader object
//		JsonReader jsonReader = Json.createReader(fis);
//		
//		//get JsonObject from JsonReader
//		JsonObject jsonObject = jsonReader.readObject();
//		
//		//we can close IO resource and JsonReader now
//		jsonReader.close();
//		fis.close();
		
		
		
		
		
		//connecting with server
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("server connection established!");
		
		//connecting with database
		MongoDatabase dbs = mongoClient.getDatabase("javajsondb");
		System.out.println("Connection to database established too!");
		System.out.println("Database Name is = "+dbs.getName());
		
		//read collection
		MongoCollection<Document> coll = dbs.getCollection("Movies");
		System.out.println("Retrieving from collection "+coll.getNamespace());
		
		//coll.find().forEach((Block<? super Document>) coll);
//		coll.find(
//			    new Document("title", new Document("title", coll)
//			          .append("summary", coll))
//			          .append("year", coll)).forEach((Block<? super Document>) coll);
		
		
		FindIterable<Document> firstDocument = coll.find();   //first().toJson();

		MongoCursor<Document> cursor = firstDocument.iterator();
		
		while (cursor.hasNext()) {
			
		      Document document = cursor.next();
		      JsonObject jsonObject = Json.createReader(new StringReader(document.toJson())).readObject();
		      
		      Movie mov = new Movie(null, null, 0, null, null);
				
				mov.setTitle(jsonObject.getString("title"));
				mov.setSummary(jsonObject.getString("summary"));
				mov.setYear(jsonObject.getInt("year"));
				
				//reading arrays of actors from json
				JsonArray jsonArray = jsonObject.getJsonArray("actors");
				Person[] actors = new Person[jsonArray.size()];
				int index = 0;
				for(JsonValue value : jsonArray){
					actors[index++] = new Person(value.asJsonObject().getString("last_name"),value.asJsonObject().getString("first_name"));
				}
				mov.setActors(actors);
				
				JsonObject innerJsonObject = jsonObject.getJsonObject("director");
				Person director = new Person(innerJsonObject.getString("last_name"), innerJsonObject.getString("first_name"));
				mov.setDirector(director);
						
				//print movie information
				System.out.println(mov);
				
		}
		
		
	}
	
}
