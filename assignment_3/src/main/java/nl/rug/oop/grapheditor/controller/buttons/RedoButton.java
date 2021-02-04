package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.RedoAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class RedoButton extends JButton {

	/**
	 * Button to load redo a change.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setText("Redo");
		setBorderPainted(false);
	}

	public RedoButton(GraphModel graphModel) {
		super(new RedoAction(graphModel));
		setButtonProperties();
	}
}
