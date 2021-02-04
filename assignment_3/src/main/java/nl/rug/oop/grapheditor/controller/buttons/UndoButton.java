package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.UndoAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class UndoButton extends JButton {

	/**
	 * Button to load undo a change.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setText("Undo");
		setBorderPainted(false);
	}

	public UndoButton(GraphModel graphModel) {
		super(new UndoAction(graphModel));
		setButtonProperties();
	}
}
