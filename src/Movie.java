import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class Movie {

	private static String title;
	private static String summary;
	private static int year;
	private static Person director;
	private static Person[] actors;
	
	public String setTitle;
	public String setSummary;
	public int seYear;
	public String setDirector;
	public String setActors;
	
	
	



	public Movie(String title, String summary, int year, Person director, Person[] actors) {
		super();
		this.title = title;
		this.summary = summary;
		this.year = year;
		this.director = director;
		this.actors = actors;
	}



	public static Person getDirector() {
		return director;
	}



	public void setDirector(Person director) {
		this.director = director;
	}



	public static Person[] getActors() {
		return actors;
	}



	public void setActors(Person[] actors) {
		this.actors = actors;
	}

	
	public static String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public static String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public static int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}





	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("***** Movie details *****\n");
		sb.append("Title="+getTitle()+"\n");
		sb.append("Summary="+getSummary()+"\n");
		sb.append("Year="+getYear()+"\n");
		sb.append("Director="+getDirector());
		sb.append("Actors="+Arrays.toString(getActors())+"\n");
		sb.append("\n*****************************");
		return sb.toString();
	}



	public static void movieJSONReader() {
		
		try {
			MovieJSONReader.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public static void movieJSONWriter() {
		// TODO Auto-generated method stub
		
		try {
			MovieJSONWriter.write();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
