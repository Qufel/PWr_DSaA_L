package dsaa.lab07;

import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document implements IWithName{
	private static final int MODVALUE=100000000;
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name) {
		//TODO
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		String line = scan.nextLine();

		// Ask for input until "eod" command
		while (!line.equals("eod")) {

			String[] words = line.split(" "); // Separate each line into words

			// Loop through all words in a line
			for (String word : words) {

				word = word.toLowerCase();

				if (word.matches("link=([a-z][a-z0-9_]*)(\\([0-9]*\\))?")) {

					word = word.substring("link=".length());

					Link link = createLink(word);

					if (link != null) {
						this.link.add(link);
					}

				}

			}

			line = scan.nextLine();
		}
	}
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	

	public static boolean isCorrectId(String id) {
		return id.matches("^[a-zA-Z][a-zA-Z0-9_]*$");
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static Link createLink(String link) {
		Pattern pattern = Pattern.compile("([a-zA-Z][a-zA-Z0-9_]*)(\\([0-9]*\\))?");
		Matcher matcher = pattern.matcher(link);

		if (matcher.find()) {

			String refGroup = matcher.group(1);
			String weightGroup = matcher.group(0).substring(refGroup.length());

			if (weightGroup.isEmpty())
				return new Link(refGroup.toLowerCase());

			// Extract weight from parentheses
			int weight = Integer.parseInt(weightGroup.substring(1, weightGroup.length() - 1));

			return new Link(refGroup.toLowerCase(), weight);

		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Document: ").append(name).append("\n");

		int counter = 1;

		ListIterator<Link> it = link.listIterator();
		while (it.hasNext()) {

			Link link = it.next();
			sb.append(link.toString());
			counter++;

			if (counter > 10) {
				sb.append("\n");
				counter = 1;
			} else {
				sb.append(" ");
			}

		}

		return sb.toString().trim();
	}

	public String toStringReverse() {
		StringBuilder sb = new StringBuilder();

		sb.append("Document: ").append(name).append("\n");

		int counter = 1;

		ListIterator<Link> it = link.listIterator();
		while (it.hasPrevious()) {

			Link link = it.previous();
			sb.append(link.toString());
			counter++;

			if (counter > 10) {
				sb.append("\n");
				counter = 1;
			} else {
				sb.append(" ");
			}
		}

		return sb.toString().trim();
	}

	@Override
	public String getName() {
		// TODO
		return null;
	}


}

