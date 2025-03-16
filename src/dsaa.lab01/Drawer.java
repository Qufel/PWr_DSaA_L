package dsaa.lab01;

public class Drawer {

	public static void drawLine(int n, char ch) {

		for (int i = 0; i < n; i++) {
			System.out.print(ch);
		}

	}

	// Draw line for pyramid and christmas tree
	private static void drawPyramidLine(int n, int row) {

		int maxChars = 2 * n - 1; // Max count of chars in row

		int blocksCount = 2 * row - 1; // Count of "blocks" in each row
		int emptySpace = maxChars - blocksCount; // Count of empty spaces

		drawLine(emptySpace / 2, '.');
		drawLine(blocksCount, 'X');
		drawLine(emptySpace / 2, '.');


		System.out.println(); // Print /n

	}

	public static void drawPyramid(int n) {
		for (int i = 1; i <= n; i++) {
			drawPyramidLine(n, i); // Draws each row of a pyramid
		}
	}

	public static void drawChristmassTree(int n) {

		// Draw each pyramid in Christmas tree
		for (int i = 1; i <= n; i++) {
			// Draw each row of pyramid with size of the largest row of Christmas tree
			for (int j = 1; j <= i; j++) {
				drawPyramidLine(n, j);
			}
		}

	}

	public static void drawSquare(int n) {

		drawLine(n, 'X');
		System.out.println();

		for (int i = 1; i <= n - 2; i++) {

			drawLine(1, 'X');
			drawLine(n - 2, '.');
			drawLine(1, 'X');

			System.out.println();

		}

		drawLine(n, 'X');
		System.out.println();

	}

}
