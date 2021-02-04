package nl.rug.oop.grapheditor.model;


import javax.swing.undo.UndoManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class GraphModel extends Observable implements Serializable {

	protected final ArrayList<Node> nodes;
	protected final ArrayList<Edge> edges;
	private final UndoManager undoManager;
	private Node selectedNode;
	private Node firstSelectedNode; /** Used for drawing the line when creating an edge, and  for deletion of  edges*/
	private boolean addEdgeButtonPressed;
	private boolean removeEdgeButtonPressed;
	private boolean renameButtonPressed;
	private boolean darkButtonPressed;
	private String renamed;
	private int x;
	private int y;
	private int finalY;
	private int finalX;

	public GraphModel() {
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
		undoManager = new UndoManager();
		updatePanel();
	}

	/**
	 * This function resets the graph
	 */
	public void resetGraph() {
		nodes.removeAll(nodes);
		edges.removeAll(edges);
		setSelectedNodeNull();
		firstSelectedNode = null;
		setAddEdgeButtonPressed(false);
		setRemoveEdgeButtonPressed(false);
		x = 0;
		y = 0;
		renamed = "";
		updatePanel();
	}

	/**
	 * Adds a new node to the graph
	 */
	public void addNode(Node node) {
		nodes.add(node);
		updatePanel();
	}

	/**
	 * Deletes a new node to the graph
	 */
	public void removeNode(Node node) {
		if (node != null) {
			for (Node value : nodes) {
				removeEdge(node, value);
			}
			changeNodesId(node);
			nodes.remove(node);
			setSelectedNodeNull();
			updatePanel();
		}
	}

	/**
	 * Creates a new edge between 2 nodes
	 */
	public void createEdge(Node firstNode, Node secondNode) {
		Edge edge = new Edge(firstNode,secondNode);
		if (!edges.contains(edge)) {
			edges.add(edge);
		}
		updatePanel();
	}

	/**
	 * Removes an edge between 2 edges
	 */
	public void removeEdge(Node firstNode, Node secondNode) {
		edges.removeIf(edge -> (edge.getFirstNode() == firstNode && edge.getSecondNode() == secondNode) ||
				(edge.getFirstNode() == secondNode && edge.getSecondNode() == firstNode));
	}

	/**
	 * After removing a node, we need to change the uniqueId's of other nodes, which have a higher index that removed node
	 */
	public void changeNodesId(Node node) {
		int index = nodes.indexOf(node);
		for (int i = index; i < nodes.size() && i > 0; i++) {
			nodes.get(i).setUniqueID();
		}
	}

	/**
	 * This function is used when a Node is selected and moved
	 */
	public void move( int x, int y) {
		if (selectedNode != null) {
			nodes.get(nodes.indexOf(selectedNode)).setX(x);
			nodes.get(nodes.indexOf(selectedNode)).setY(y);
			selectedNode.setX(x);
			selectedNode.setY(y);
			finalX = x;
			finalY = y;
			updatePanel();
		}
	}

	/**
	 * When redo or undo is pressed, a node is moved to it's previous position
	 */
	public void moveNodeTo(int node, int x, int y) {
		if (selectedNode != null) {
			if (node >= 0 && node <= nodes.size()) {
				nodes.get(node).setX(x);
				nodes.get(node).setY(y);
				updatePanel();
			}
		}
	}

	/**
	 * addToEdges and removeFromEdges are used for redo and undo
	 */

	public void addToEdges(Edge edge) {
		edges.add(edge);
		updatePanel();
	}

	public void removeFromEdges(Edge edge) {
		edges.remove(edge);
		updatePanel();
	}

	/**
	 * Function is used to rename a selected node
	 */
	public void renameSelectedNode(String newName, Node node) {
		int index = nodes.indexOf(node);
		if (newName.equals(renamed)) {
			renamed = "";
		} else {
			renamed = newName;
		}
		if (index >= 0 && index < nodes.size()) {
			nodes.get(index).setNewName(newName);
			setSelectedNodeNull();
			setRenameButtonPressed(false);
			updatePanel();
		}
	}

	public void undo() {
		if (undoManager.canUndo()) {
			undoManager.undo();
		}
	}

	public void redo() {
		if (undoManager.canRedo()) {
			undoManager.redo();
		}
	}

	/**
	 * Various getters - self explaining.
	 */

	public int getNodesNr() {
		return nodes.size();
	}

	public Node getNode(int index) {
		return nodes.get(index);
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public Node getSelectedNode() {
		return selectedNode;
	}

	public boolean isAddEdgeButtonPressed() {
		return addEdgeButtonPressed;
	}

	public Node getFirstSelectedNode() {
		return firstSelectedNode;
	}

	public boolean isRemoveEdgeButtonPressed() {
		return removeEdgeButtonPressed;
	}

	public boolean isRenameButtonPressed() {
		return renameButtonPressed;
	}

	public boolean isDarkModeButtonPressed() {
		return darkButtonPressed;
	}

	public int getMouseLocationX() {
		return x;
	}

	public int getMouseLocationY() {
		return y;
	}

	public int getFinalY() {
		return finalY;
	}

	public int getFinalX() {
		return finalX;
	}

	public String getRenamed() {
		return renamed;
	}

	public UndoManager getUndoManager() {
		return undoManager;
	}

	/**
	 * Various setters - self explaining.
	 */

	public void setSelectedNode(Node selectedNode) {
		this.selectedNode = selectedNode;
		if (isAddEdgeButtonPressed() && firstSelectedNode != null) {
			createEdge(firstSelectedNode, selectedNode);
			firstSelectedNode = null;
			setAddEdgeButtonPressed(false);
		}
		if (isRemoveEdgeButtonPressed() && firstSelectedNode != null && edges.size() > 0) {
			removeEdge(firstSelectedNode, selectedNode);
			firstSelectedNode = null;
			setRemoveEdgeButtonPressed(false);
		}
		updatePanel();
	}

	public void setSelectedNodeNull() {
		selectedNode = null;
		setChanged();
		notifyObservers();
	}

	public void setMouseLocation(int x, int y) {
		this.x = x;
		this.y = y;
		updatePanel();
	}

	/**
	 * When addEdge button is pressed, we need to memorize the selected node
	 */
	public void setAddEdgeButtonPressed(boolean addEdgeButtonPressed) {
		this.addEdgeButtonPressed = addEdgeButtonPressed;
		removeEdgeButtonPressed = false;
		firstSelectedNode = selectedNode;
		selectedNode = null;
		updatePanel();
	}

	public void setRemoveEdgeButtonPressed(boolean removeEdgeButtonPressed) {
		this.removeEdgeButtonPressed = removeEdgeButtonPressed;
		addEdgeButtonPressed = false;
		firstSelectedNode = selectedNode;
		selectedNode = null;
		updatePanel();
	}

	public void setRenameButtonPressed(boolean renameButtonPressed) {
		this.renameButtonPressed = renameButtonPressed;
		updatePanel();
	}

	public void setDarkButtonPressed( boolean darkButtonPressed) {
		this.darkButtonPressed = darkButtonPressed;
		updatePanel();
	}

	/**
	 * Used to update the panel when something has changed
	 */
	public void updatePanel() {
		setChanged();
		notifyObservers();
	}
}
