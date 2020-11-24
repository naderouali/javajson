import java.util.Arrays;

public class Person {

	public String last_name, first_name;

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public Person(String last_name, String first_name) {
		super();
		this.last_name = last_name;
		this.first_name = first_name;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" First name : "+getFirst_name()+", ");
		sb.append("Last name : "+getLast_name()+"\n");
		return sb.toString();
	}

}
