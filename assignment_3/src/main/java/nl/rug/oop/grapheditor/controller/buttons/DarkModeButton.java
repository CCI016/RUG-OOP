package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.DarkAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class DarkModeButton extends JButton {
	/**
	 * Button to enter dark mode.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Dark mode of the app");
		setText("Dark Mode");
		setBorderPainted(false);
	}

	public DarkModeButton(GraphModel graphModel) {
		super(new DarkAction(graphModel));
		setButtonProperties();
	}
}
