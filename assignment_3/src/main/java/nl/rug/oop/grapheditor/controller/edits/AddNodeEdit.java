package nl.rug.oop.grapheditor.controller.edits;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class AddNodeEdit extends AbstractUndoableEdit {
	private final GraphModel graphModel;
	private final Node node;

	/**
	 * Creates a new undoable action to add a node.
	 *
	 * @param graphModel the Graph Model
	 */
	public AddNodeEdit(GraphModel graphModel) {
		this.graphModel = graphModel;
		node = new Node(graphModel.getNodes().size());
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();
		graphModel.removeNode(node);
	}

	@Override
	public void redo() throws CannotRedoException {
		if (super.canRedo()) {
			super.redo();
		}
		graphModel.addNode(node);
	}
}
