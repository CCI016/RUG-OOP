package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.SaveAndLoad;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoadAction extends AbstractAction {
	private final GraphModel graphModel;

	/**
	 * Creates a new action to load a file.
	 *
	 * @param graphModel the Graph Model
	 */
	public LoadAction(GraphModel graphModel) {
		this.graphModel = graphModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SaveAndLoad.loadGraph(graphModel, "");
	}
}
