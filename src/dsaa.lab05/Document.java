package dsaa.lab05;

import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
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

	public static boolean isCorrectId(String id) {
		return id.matches("^[a-zA-Z][a-zA-Z0-9_]*$");
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the beginning)

	/**
	 * Creates a link if provided string is of correct pattern.
	 * @param link a string from which the new {@link Link} object will be created
	 * @return {@link Link} {@code link}
	 */
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
	public int[] getWeights() {

		if (link.isEmpty())
			return new int[0];

		int[] results = new int[link.size()];

		for (int i = 0; i < link.size(); i++) {
			results[i] = link.get(i).weight;
		}

		return results;
	}

	public static void showArray(int[] arr) {
		String[] strArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = String.valueOf(arr[i]);
		}
		System.out.println(String.join(" ", strArr));
	}

	void bubbleSort(int[] arr) {
		showArray(arr);
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					swap(arr, j, j - 1);
				}
			}
			showArray(arr);
		}
	}

	public void insertSort(int[] arr) {
		showArray(arr);
		int i = arr.length - 1;

		while (i > 0) {

			int j = i;

			while (j < arr.length && arr[j] < arr[j - 1]) {

				swap(arr, j, j - 1);

				j++;

			}

			i--;

			showArray(arr);
		}
	}
	public void selectSort(int[] arr) {
		showArray(arr);
		int i = arr.length - 1;

		while (i > 0) {

			int j = i;

			int maxi = i;

			while (j >= 0) {
				if (arr[j] > arr[maxi]) {
					maxi = j;
				}
				j--;
			}

			swap(arr, i, maxi);

			i--;
			showArray(arr);
		}
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
