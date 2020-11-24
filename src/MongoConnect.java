import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnect {

	String name;
	int age;

	public void Movie(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
//	public static Movie addMovie() {
//		String name;
//		int age;
//		Scanner sc = new Scanner(System.in);
//
//			System.out.println("Name : ");
//			name=sc.nextLine();
//			System.out.println("Age : ");
//			age=sc.nextInt();
//			sc.nextLine();
//			
//		return new Movie(name,age);
//	}
	
		
	public static void main(String[] args) {
		
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
		Document doc1 = new Document("name", "mohseeeeen")
				.append("age", 24);
		coll.insertOne(doc1);
		System.out.println("Document inserted..");
		
		//read a document from database
//		FindIterable<Document> cursor = coll.find();
//		while (((Iterator<DBObject>) cursor).hasNext())
//		{
//			System.out.println(((Iterator<DBObject>) cursor).next());
//		}
		
	}

}
