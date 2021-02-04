package nl.rug.oop.grapheditor.controller.buttons;

import nl.rug.oop.grapheditor.controller.actions.LoadAction;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;

public class LoadButton extends JButton{

	/**
	 * Button to load from a file.
	 */
	private void setButtonProperties() {
		setVerticalTextPosition(AbstractButton.CENTER);
		setHorizontalTextPosition(AbstractButton.CENTER);
		setToolTipText("Loads a graph from a given file");
		setText("Load Graph");
		setBorderPainted(false);
	}

	public LoadButton(GraphModel graphModel) {
		super(new LoadAction(graphModel));
		setButtonProperties();
	}
}
