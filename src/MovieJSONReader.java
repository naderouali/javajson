import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;



public class MovieJSONReader {

	public static final String JSON_FILE="X:/movie.json";
	
	public static void read() throws IOException{
		
		InputStream fis = new FileInputStream(JSON_FILE);
		
		//create JsonReader object
		JsonReader jsonReader = Json.createReader(fis);
		
		/**
		 * We can create JsonReader from Factory also
		JsonReaderFactory factory = Json.createReaderFactory(null);
		jsonReader = factory.createReader(fis);
		*/
		
		//get JsonObject from JsonReader
		JsonObject jsonObject = jsonReader.readObject();
		
		//we can close IO resource and JsonReader now
		jsonReader.close();
		fis.close();
		
		//Retrieve data from JsonObject and create Movie bean
		Movie mov = new Movie();
		
		mov.setTitle(jsonObject.getString("title"));
		mov.setSummary(jsonObject.getString("summary"));
		mov.setYear(jsonObject.getInt("year"));
		
		//reading arrays of directors from json
		JsonArray jsonArray = jsonObject.getJsonArray("actors");
		Person[] actors = new Person[jsonArray.size()];
		int index = 0;
		for(JsonValue value : jsonArray){
			actors[index++] = new Person(value.asJsonObject().getString("last_name"),value.asJsonObject().getString("first_name"));
		}
		mov.setActors(actors);
		
		//reading inner object of a director from json object
		JsonObject innerJsonObject = jsonObject.getJsonObject("director");
		Person director = new Person(innerJsonObject.getString("last_name"), innerJsonObject.getString("first_name"));
		mov.setDirector(director);
		

		//reading arrays of actors from json
//		JsonArray jsonArray = jsonObject.getJsonArray("actors");
//		String[] actors = new String[jsonArray.size()];
//		int index = 0;
//		for(JsonValue value : jsonArray){
//				actors[index++] = String.parseString(value.toString());
//			}
//		mov.setActors(actors);
				
		//reading inner object of actors from json object
//		JsonObject innerJsonObject = jsonObject.getJsonObject("actor");
//		Actor actor = new Actor();
//		actor.setFirst_name(innerJsonObject.getString("first_name"));
//		actor.setLast_name(innerJsonObject.getString("last_name"));
//		mov.setActor(actors);
				
		
		//print movie bean information
		System.out.println(mov);
		
	}

}
