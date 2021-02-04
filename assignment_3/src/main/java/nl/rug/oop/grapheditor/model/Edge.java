package nl.rug.oop.grapheditor.model;

public class Edge {
	public final Node firstNode;
	public final Node secondNode;

	public Edge(Node first, Node second) {
		firstNode = first;
		secondNode = second;
	}

	/**
	 * Getters - self explaining.
	 */

	public int getFirstNodeId() {
		return firstNode.getUniqueID();
	}

	public int getSecondNodeId() {
		return secondNode.getUniqueID();
	}

	public Node getSecondNode() {
		return secondNode;
	}

	public Node getFirstNode() {
		return firstNode;
	}

	/**
	 * Checks if
	 */
	public boolean connectedToNode(Node node) {
		return firstNode == node || secondNode == node;
	}
}
