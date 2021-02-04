package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.EdgeAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class AddEdge extends JButton {

	/**
	 * Button to add an edge.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Creates a new Edge");
		setText("Add Edge");
		setBorderPainted(false);
	}

	public AddEdge(GraphModel graphModel) {
		super(new EdgeAction(graphModel));
		setButtonProperties();
	}
}