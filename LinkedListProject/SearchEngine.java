package project1;

import java.io.*;
import java.util.Scanner;

public class SearchEngine {
	private linkkk documents = new linkkk();

	public void clearList() {
		documents.clear();
	}

	public void load(String filePath) {
		clearList();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			StringBuilder currentDocumentNode = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("<") && line.endsWith(">")) {
					if (currentDocumentNode.length() > 0) {
						documents.append(currentDocumentNode.toString());
						currentDocumentNode.setLength(0);
					}
					currentDocumentNode.append(line).append("\n");
				} else {
					currentDocumentNode.append(line).append("\n");
				}
			}

			if (currentDocumentNode.length() > 0) {
				documents.append(currentDocumentNode.toString());
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void search(String queryString, String outputFilePath) {
	    try {
	        BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFilePath));
	        String[] queryTerms = queryString.split(",");
	        linkkk<String> matchingDocuments = new linkkk<>();

	        for (String term : queryTerms) {
	            term = term.trim();
	            if (term.startsWith("!")) {
	                term = term.substring(1);
	                Node<String> currentDocumentNode = documents.head;
	                while (currentDocumentNode != null) {
	                    String document = currentDocumentNode.data;
	                    if (document.contains(term)) {
	                        matchingDocuments.delete(document);
	                    }
	                    currentDocumentNode = currentDocumentNode.next;
	                }
	            } else {
	                Node<String> currentDocumentNode = documents.head;
	                while (currentDocumentNode != null) {
	                    String document = currentDocumentNode.data;
	                    if (document.contains(term)) {
	                        matchingDocuments.append(document);
	                    }
	                    currentDocumentNode = currentDocumentNode.next;
	                }
	            }
	        }

	        outputFileWriter.write("query <" + queryString + ">");
	        outputFileWriter.newLine();
	        Node<String> matchedDocumentNode = matchingDocuments.head;
	        while (matchedDocumentNode != null) {
	            outputFileWriter.write(matchedDocumentNode.data);
	            outputFileWriter.newLine();
	            matchedDocumentNode = matchedDocumentNode.next;
	        }

	        outputFileWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void remove(String query, String filePath) {
		linkkk<String> documentsToKeep = new linkkk();

		linkkk<String> allDocuments = readDocumentsFromFile(filePath);

		Node<String> current = allDocuments.head;
		while (current != null) {
			String document = current.data;
			if (!document.contains(query)) {
				documentsToKeep.append(document);
			}
			current = current.next;
		}

		writeDocumentsToFile(filePath, documentsToKeep);
	}

	private linkkk<String> readDocumentsFromFile(String filePath) {
		linkkk<String> allDocuments = new linkkk();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			StringBuilder currentDocument = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("<") && line.endsWith(">")) {
					if (currentDocument.length() > 0) {
						allDocuments.append(currentDocument.toString());
						currentDocument.setLength(0);
					}
					currentDocument.append(line).append("\n");
				} else {
					currentDocument.append(line).append("\n");
				}
			}

			if (currentDocument.length() > 0) {
				allDocuments.append(currentDocument.toString());
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allDocuments;
	}

	private void writeDocumentsToFile(String filePath, linkkk<String> documentsToWrite) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			Node<String> current = documentsToWrite.head;
			while (current != null) {
				writer.write(current.data);
				writer.newLine();
				current = current.next;
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}