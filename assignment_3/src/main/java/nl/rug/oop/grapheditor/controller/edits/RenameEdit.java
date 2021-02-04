package nl.rug.oop.grapheditor.controller.edits;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.undo.AbstractUndoableEdit;

public class RenameEdit extends AbstractUndoableEdit {
	private final GraphModel graphModel;
	private final Node node;
	private String previousName;

	/**
	 * Creates a new undoable action to rename a node.
	 *
	 * @param graphModel the Graph Model
	 */
	public RenameEdit(GraphModel graphModel) {
		this.graphModel = graphModel;
		node = graphModel.getSelectedNode();
		if (node != null) {
			previousName = node.getName();
		}
	}

	@Override
	public void undo() {
		super.undo();
		if (node != null) {
			graphModel.getNodes().get(node.getUniqueID()).setNewName(previousName);
			graphModel.updatePanel();
		}
	}

	@Override
	public void redo() {
		if (super.canRedo()) {
			super.redo();
		}
		if (graphModel.getSelectedNode() == null && !graphModel.getRenamed().equals("")) {
			graphModel.getNodes().get(node.getUniqueID()).setNewName(graphModel.getRenamed());
			graphModel.updatePanel();
		} else {
			graphModel.setRenameButtonPressed(true);
		}
	}
}
