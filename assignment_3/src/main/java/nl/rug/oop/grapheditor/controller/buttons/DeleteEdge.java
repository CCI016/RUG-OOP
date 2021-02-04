package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.EdgeDeletion;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class DeleteEdge extends JButton {

	/**
	 * Button to delete an edge.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Deletes a selected Edge");
		setText("Delete Edge");
		setBorderPainted(false);
	}

	public DeleteEdge(GraphModel graphModel) {
		super(new EdgeDeletion(graphModel));
		setButtonProperties();
	}
}
