package nl.rug.oop.grapheditor.model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Scanner;

public class SaveAndLoad {

	/**
	 * Saves the graph in the requested format.
	 */
	public static void saveGraph(GraphModel graphModel) {

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Specify a file to save the graph");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files Only", "txt");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(filter);
		int returnVal = chooser.showDialog(null, "Save");

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File savingFile = chooser.getSelectedFile();
			chooser.setCurrentDirectory(savingFile);
			String saveFileName = checkExtension(chooser.getCurrentDirectory() + File.separator + chooser.getSelectedFile().getName());

			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(saveFileName, false)))) {
				String newLine = System.lineSeparator();
				out.printf("%d %d %s", graphModel.nodes.size(), graphModel.edges.size(), newLine);
				for (Node node : graphModel.nodes) {
					out.printf("%d %d %d %d %s %s", node.getX(),  node.getY(),  node.getHeight(),  node.getWidth(), node.getName(), newLine);
				}
				for (Edge edge : graphModel.edges) {
					out.printf("%d %d %s", edge.getFirstNodeId(), edge.getSecondNodeId(), newLine);
				}
			} catch (FileNotFoundException e) {
				System.out.println("File could not be found");
			} catch (IOException e) {
				System.out.println("could not write to file");
			}
		}
	}

	/**
	 * Checks if the user introduced the file he wants to create with .txt extension or not
	 */
	private static String checkExtension(String fileName) {
		String extension;
		if (fileName.length() > 4) {
			extension = fileName.substring(fileName.length() - 4);
		} else {
			return fileName + ".txt";
		}
		if (!".txt".equals(extension)) {
			fileName += ".txt";
		}
		return fileName;
	}

	/**
	 * Function to load the graph from a file.
	 */
	public static void loadGraph(GraphModel graphModel, String filepath) {
		int nodesNumber, edgesNumber;
		File loadingFile = null;
		if (filepath.equals("")) {
			/* In this case, the program wasn't loaded from the terminal so we'll use for loading JFileChooser*/
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose a file for loading the graph");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files Only", "txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(filter);
			int returnVal = fileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				loadingFile = fileChooser.getSelectedFile();
			}
		} else {
			loadingFile = new File(filepath);
		}
		if (loadingFile != null) {
			graphModel.nodes.clear();
			graphModel.edges.clear();
			try {
				Scanner scanner = new Scanner(loadingFile);
				nodesNumber = scanner.nextInt();
				edgesNumber = scanner.nextInt();
				for (int i = 0; i < nodesNumber; i++) {
					int x = scanner.nextInt();
					int y = scanner.nextInt();
					int height = scanner.nextInt();
					int width = scanner.nextInt();
					String name = scanner.nextLine();
					graphModel.addNode(new Node(x, y, width, height, name, i));
				}
				for (int i = 0; i < edgesNumber; i++) {
					int firstNode = scanner.nextInt();
					int secondNode = scanner.nextInt();
					graphModel.createEdge(graphModel.nodes.get(firstNode), graphModel.nodes.get(secondNode));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
