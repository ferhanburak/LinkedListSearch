package project1;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		SearchEngine searchEngine = new SearchEngine();
		Scanner scanner = new Scanner(System.in);

		boolean exit = false;

		while (!exit) {
			System.out.println("Choose an action:");
			System.out.println("1. Load");
			System.out.println("2. Search");
			System.out.println("3. Remove");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 1) {
				System.out.print("Enter path to the info file: ");
				String filePath = scanner.nextLine();
				searchEngine.load(filePath);
			} else if (choice == 2) {
				System.out.print("Enter search query: ");
				String queryString = scanner.nextLine();
				System.out.print("Enter output file path: ");
				String outputFilePath = scanner.nextLine();
				searchEngine.search(queryString, outputFilePath);
			} else if (choice == 3) {
				System.out.print("Enter document to remove: ");
				String documentToRemove = scanner.nextLine();
				System.out.print("Enter file path for removal: ");
				String removeFilePath = scanner.nextLine();
				searchEngine.remove(documentToRemove, removeFilePath);
			} else if (choice == 4) {
				exit = true;
			} else {
				System.out.println("Invalid choice, choose a valid option.");
			}
		}

		scanner.close();
	}

}
