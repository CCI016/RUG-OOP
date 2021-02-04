package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.ResetAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class ResetButton extends JButton {

	/**
	 * Button to reset the graph.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Resets the current graph");
		setText("Reset Graph");
		setBorderPainted(false);
	}

	public ResetButton(GraphModel graphModel) {
		super(new ResetAction(graphModel));
		setButtonProperties();
	}
}
