package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class FileButton extends JMenu {

	/**
	 * Button (Menu containing reset, save and load buttons).
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setText("File");
		setBorderPainted(false);
	}

	public FileButton(GraphModel graphModel) {
		add(new ResetButton(graphModel));
		add(new SaveButton(graphModel));
		add(new LoadButton(graphModel));
		setButtonProperties();
	}
}
