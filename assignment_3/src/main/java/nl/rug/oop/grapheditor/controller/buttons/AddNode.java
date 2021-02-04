package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.NodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AddNode extends JButton {

	/**
	 * Button to add a node.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Creates a new Node");
		setMnemonic(KeyEvent.VK_A);
		setText("Add Node");
		setBorderPainted(false);
	}

	public AddNode(GraphModel graphModel) {
		super(new NodeAction(graphModel));
		setButtonProperties();
	}
}
