package nl.rug.oop.grapheditor.controller.edits;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;

public class DeleteNodeEdit extends AbstractUndoableEdit {

	private final GraphModel graphModel;
	private final Node node;
	private final ArrayList<Edge> edges;

	/**
	 * Creates a new undoable delete to add a node.
	 * @param graphModel the Graph Model
	 */
	public DeleteNodeEdit(GraphModel graphModel) {
		this.graphModel = graphModel;
		node = graphModel.getSelectedNode();
		edges = new ArrayList<>();
		for (Edge edge : graphModel.getEdges()) {
			if (edge.connectedToNode(node)) {
				if (!edges.contains(edge)) {
					edges.add(edge);
				}
			}
		}
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
		if (node != null) {
			graphModel.addNode(node);
			if (edges != null) {
				for (Edge edge : edges) {
					graphModel.addToEdges(edge);
				}
			}
		}
	}

	@Override
	public void redo() throws CannotRedoException {
		if(super.canRedo()) {
			super.redo();
		}
		if (node != null) {
			graphModel.removeNode(node);
			for (Edge edge : edges) {
				graphModel.removeFromEdges(edge);
			}
		}
	}
}
