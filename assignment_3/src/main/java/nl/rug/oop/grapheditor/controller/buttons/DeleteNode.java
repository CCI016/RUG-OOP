package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.NodeDeletion;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class DeleteNode extends JButton {

	/**
	 * Button to delete a node.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Deletes selected Node");
		setText("Delete Node");
		setBorderPainted(false);
	}

	public DeleteNode(GraphModel graphModel) {
		super(new NodeDeletion(graphModel));
		setButtonProperties();
	}
}
