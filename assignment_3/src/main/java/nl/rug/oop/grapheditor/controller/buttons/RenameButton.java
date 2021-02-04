package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.RenameAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class RenameButton extends JButton {

	/**
	 * Button to rename a node.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setText("Rename");
		setBorderPainted(false);
	}

	public RenameButton(GraphModel graphModel) {
		super(new RenameAction(graphModel));
		setButtonProperties();
	}
}
