//CE PROJET EST REALISEE PAR NADER OUALI

import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {

		int x;
		Scanner sca = new Scanner(System.in);
		
		do {
			System.out.println("MENU");
			System.out.println("1-Create JSON");
			System.out.println("2-Read JSON");
			x = sca.nextInt();
			sca.nextLine();
		} while (x < 0 || x > 2);
		switch (x) {
		case 1:
			Movie.movieJSONWriter();
			break;
		case 2:
			Movie.movieJSONReader();
			break;
		}

	}

}