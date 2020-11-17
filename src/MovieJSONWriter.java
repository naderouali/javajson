import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class MovieJSONWriter {

	public static void write() throws FileNotFoundException {

		Movie mov = createMovie();

		JsonObjectBuilder movBuilder = Json.createObjectBuilder();
		JsonObjectBuilder directorBuilder = Json.createObjectBuilder();
		JsonArrayBuilder actorsBuilder = Json.createArrayBuilder();12

		
		directorBuilder.add("first_name", mov.getDirector().getFirst_name())
			.add("last_name", mov.getDirector().getLast_name());
		
		for(int i = 0 ; i < mov.getActors().length ; i++) {
			actorsBuilder.add(
					Json.createObjectBuilder().add(
							"first_name", mov.getActors()[i].getFirst_name()
							).add("last_name", mov.getActors()[i].getLast_name())
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
		/**
		 * We can get JsonWriter from JsonWriterFactory also
		JsonWriterFactory factory = Json.createWriterFactory(null);
		jsonWriter = factory.createWriter(os);
		*/
		jsonWriter.writeObject(movJsonObject);
		jsonWriter.close();
	}
	

	public static Movie createMovie() {

		Movie mov = new Movie();
		mov.setTitle("The Social Network");
		mov.setSummary("2003 night");
		mov.setYear(2010);
		mov.setDirector(new Person("Fincher","david"));

		Person[] actors = new Person[] {
				new Person("Eisenberg","Jesse"),
				new Person("Mara","Rooney")
		};
		
		mov.setActors(actors);

		return mov;
	}

}
