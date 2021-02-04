package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractAction {
	private final GraphModel graphModel;
	/**
	 * Creates a new action to undo a change.
	 * @param graphModel the Graph Model
	 */
	public UndoAction(GraphModel graphModel) {
		this.graphModel = graphModel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		graphModel.undo();
	}
}
