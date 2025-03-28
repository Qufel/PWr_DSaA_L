package dsaa.lab03;

import java.util.Iterator;
import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayUnorderedListWithHeadAndTail<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name;
		this.link=new TwoWayUnorderedListWithHeadAndTail<Link>();
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
					this.link.add(new Link(identifier));

				}
			}

			line = scan.nextLine();
		}
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String l) {
		l = l.toLowerCase(); // Convert to lower case
		return l.matches("link=[a-z][a-z0-9_]*");
	}
	
	@Override
	public String toString() {
		String refStr = "Document: " + name;
		return refStr + link.toString();
	}
	
	public String toStringReverse() {
		String retStr = "Document: " + name;
		return retStr + link.toStringReverse();
	}

}
