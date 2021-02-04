package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.SaveAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class SaveButton extends JButton {

	/**
	 * Button to save the graph into a file.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Saves the graph in a specified file");
		setText("Save Graph");
		setBorderPainted(false);
	}

	public SaveButton(GraphModel graphModel) {
		super(new SaveAction(graphModel));
		setButtonProperties();
	}
}
