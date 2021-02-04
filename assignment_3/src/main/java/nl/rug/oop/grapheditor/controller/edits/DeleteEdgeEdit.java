package nl.rug.oop.grapheditor.controller.edits;

import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;

public class DeleteEdgeEdit extends AbstractUndoableEdit {
	private final GraphModel graphModel;
	private final ArrayList<Edge> edges;

	/**
	 * Creates a new undoable action to delete an edge.
	 *
	 * @param graphModel the Graph Model
	 * @param edge Arraylist with edges
	 */
	public DeleteEdgeEdit(GraphModel graphModel, ArrayList<Edge> edge) {
		this.graphModel = graphModel;
		//noinspection unchecked
		this.edges = (ArrayList<Edge>) edge.clone();
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
		for (Edge edge : edges) {
			if (!graphModel.getEdges().contains(edge)) {
				graphModel.addToEdges(edge);
			}
		}
	}

	@Override
	public void redo() throws CannotRedoException {
		if (super.canRedo()) {
			super.redo();
		}
		if (graphModel.getSelectedNode() != null) {
			graphModel.setRemoveEdgeButtonPressed(true);
		}
	}
}
