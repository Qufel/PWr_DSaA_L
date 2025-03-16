package dsaa.lab01;

import java.util.Scanner;

public class Document {
	public static void loadDocument(String name, Scanner scan) {
		String line = scan.nextLine();

		// Ask for input until "eod" command
		while (!line.equals("eod")) {

			String[] words = line.split(" "); // Separate each line into words

			// Loop through all words in a line
			for (String word : words) {

				// Check if a word is correct link
				if (correctLink(word)) {

					// Extract an identifier and print it
					String identifier = word.substring("link=".length()).toLowerCase();
					System.out.println(identifier);

				}
			}

			line = scan.nextLine();
		}
	}
	
	// accepted only small letters, capital letter, digits and '_' (but not on the beginning)
	public static boolean correctLink(String link) {
		link = link.toLowerCase(); // Convert to lower case
		return link.matches("link=[a-z][a-z0-9_]+");
	}

}
