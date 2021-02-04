package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class EditButton extends JMenu {

	/**
	 * Button Edit (Menu containing rename).
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setText("Edit");
		setBorderPainted(false);
	}

	public EditButton(GraphModel graphModel) {
		add(new RenameButton(graphModel));
		add(new DarkModeButton(graphModel));
		setButtonProperties();
	}
}
