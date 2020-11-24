import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MovieJSONWriter {

	//create mov
	public static void write() throws FileNotFoundException {

		Movie mov = createMovie();

		JsonObjectBuilder movBuilder = Json.createObjectBuilder();
		JsonObjectBuilder directorBuilder = Json.createObjectBuilder();
		JsonArrayBuilder actorsBuilder = Json.createArrayBuilder();

		
		directorBuilder.add("first_name", mov.getDirector().getFirst_name())
			.add("last_name", mov.getDirector().getLast_name());
		
		for(int i = 0 ; i < mov.getActors().length ; i++) {
			actorsBuilder.add(
					Json.createObjectBuilder()
						.add("first_name", mov.getActors()[i].getFirst_name())
						.add("last_name", mov.getActors()[i].getLast_name())
					);
		}
		
		movBuilder.add("title", mov.getTitle())
					.add("summary", mov.getSummary())
						.add("year", mov.getYear());
		
		movBuilder.add("director", directorBuilder);
		movBuilder.add("actors", actorsBuilder);
		
		JsonObject movJsonObject = movBuilder.build();
		
		System.out.println("Movie JSON String\n"+movJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("X:/movie.json");
		JsonWriter jsonWriter = Json.createWriter(os);
		
		jsonWriter.writeObject(movJsonObject);
		jsonWriter.close();
		
		//connecting with server
				MongoClient mongoClient = new MongoClient("localhost", 27017);
				System.out.println("server connection established!");
				
				//connecting with database
				MongoDatabase dbs = mongoClient.getDatabase("javajsondb");
				System.out.println("Connection to database established too!");
				System.out.println("Database Name is = "+dbs.getName());
				
				//create collection
				MongoCollection<Document> coll = dbs.getCollection("Movies");
				System.out.println("Collection created");

				//insert a document in collection 
				Document doc1 = new Document("title", Movie.getTitle())
						.append("summary", Movie.getSummary())
						.append("year", Movie.getYear())
						.append("director", new Document("first_name",Movie.getDirector().getFirst_name()).append("last_name",Movie.getDirector().getLast_name()));
				
				Document[] actors = new Document[Movie.getActors().length];
				
				for (int i = 0; i< Movie.getActors().length ; i++ )
					{
						actors[i] = new Document("first_name",Movie.getActors()[i].getFirst_name()).append("last_name",Movie.getActors()[i].getLast_name());
					}
						
				doc1.append("actors",Arrays.asList(actors));
				
				coll.insertOne(doc1);
				System.out.println("Document inserted..");
	}

	public static Movie createMovie() {
		String title, summary, first, last, firstActor, lastActor;
		int year;
		Person director;
		Scanner sc = new Scanner(System.in);
		
			System.out.println("title : ");
			title=sc.nextLine();
			System.out.println("summary : ");
			summary=sc.nextLine();
			System.out.println("year : ");
			year=sc.nextInt();
			sc.nextLine();
			
			System.out.println("director : ");
			System.out.println("director first name : ");
			first=sc.nextLine();
			System.out.println("director last name : ");
			last=sc.nextLine();
			director = new Person(first, last);

			
			System.out.println("how many actors u want to save : ");
			int n=sc.nextInt();
			sc.nextLine();
			
			Person[] actors = new Person[n];
			
			
			for (int i = 0; i<n; i++ )
			{
				System.out.println("actor first name : ");
				firstActor=sc.nextLine();
				System.out.println("actor last name : ");
				lastActor=sc.nextLine();
				
				actors[i] = new Person(lastActor,firstActor);
				
			}
			
			
			
		return new Movie(title,summary,year,director,actors);
	}

}
