package dsaa.lab02;

import java.util.Iterator;
import java.util.Scanner;

public class Document{
	public String name;
	public OneWayLinkedList<Link> links;
	public Document(String name, Scanner scan) {
		this.name = name;
		this.links=new OneWayLinkedList<>();
		load(scan);
	}
	public void load(Scanner scan) {
		String line = scan.nextLine();

		// Ask for input until "eod" command
		while (!line.equals("eod")) {

			String[] words = line.split(" "); // Separate each line into words

			// Loop through all words in a line
			for (String word : words) {

				// Check if a word is correct link
				if (correctLink(word)) {

					// Extract an identifier and add it to the list
					String identifier = word.substring("link=".length()).toLowerCase();
					links.add(new Link(identifier));

				}
			}

			line = scan.nextLine();
		}
	}
	// accepted only small letters, Capital letter, digits nad '_' (but not on the beginning)
	private static boolean correctLink(String link) {
		link = link.toLowerCase(); // Convert to lower case
		return link.matches("link=[a-z][a-z0-9_]*");
	}
	
	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append("Document: ").append(name);

		Iterator<Link> it = links.iterator();

		while (it.hasNext()) {
			Link link = it.next();
			str.append("\n").append(link.ref);
		}

		return str.toString();
	}

}
