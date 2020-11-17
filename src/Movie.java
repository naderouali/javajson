import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
public class Movie {

	private String title;
	private String summary;
	private int year;
	private Person director;
	private Person[] actors;
	
	
	public Person getDirector() {
		return director;
	}



	public void setDirector(Person director) {
		this.director = director;
	}



	public Person[] getActors() {
		return actors;
	}



	public void setActors(Person[] actors) {
		this.actors = actors;
	}

	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public int getYear() {
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
		sb.append("Directors="+getDirector());
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
