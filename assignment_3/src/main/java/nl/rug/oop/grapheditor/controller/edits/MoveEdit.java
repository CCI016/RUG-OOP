package nl.rug.oop.grapheditor.controller.edits;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;

public class MoveEdit extends AbstractUndoableEdit {
	private final GraphModel graphModel;
	private final Node node;
	private int x;
	private int y;

	/**
	 * Creates a new undoable action to move the nodes.
	 *
	 * @param graphModel the Graph Model
	 */
	public MoveEdit(GraphModel graphModel) {
		this.graphModel = graphModel;
		node = graphModel.getSelectedNode();
		if (node != null) {
			x = node.getX();
			y = node.getY();
		}
	}

	@Override
	public void undo() {
		super.undo();
		graphModel.moveNodeTo(node.getUniqueID(), x, y);
	}

	@Override
	public void redo() throws CannotRedoException {
		super.redo();
		graphModel.moveNodeTo(node.getUniqueID(), graphModel.getFinalX(), graphModel.getFinalY());
	}
}
