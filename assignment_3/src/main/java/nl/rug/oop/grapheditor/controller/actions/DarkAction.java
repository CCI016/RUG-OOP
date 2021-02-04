package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DarkAction extends AbstractAction {

	private final GraphModel graphModel;

	public DarkAction(GraphModel graphModel) {
		this.graphModel = graphModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		graphModel.setDarkButtonPressed(true);
	}
}
