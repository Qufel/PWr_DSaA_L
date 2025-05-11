package dsaa.lab08;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document implements IWithName{
	private static final int MODVALUE=100000000;
	private static final int[] SEQUCENCE = {7, 11, 13, 17, 19};
	public String name;
	public BST<Link> link;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
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
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringInOrder();		
		return retStr;
	}

	public String toStringPreOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPreOrder();
		return retStr;
	}

	public String toStringPostOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPostOrder();
		return retStr;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		char[] chars = name.toCharArray();
		int hash = chars[0];
		for (int i = 0; i < chars.length - 1; i++) {
			hash = (hash * SEQUCENCE[i % 5] + chars[i + 1]) % MODVALUE;
		}
		return hash;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Document))
			return false;

		return name.equals(((Document) o).name);
	}
}
