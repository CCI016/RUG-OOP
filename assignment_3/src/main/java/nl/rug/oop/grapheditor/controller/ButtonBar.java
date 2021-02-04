package nl.rug.oop.grapheditor.controller;

import nl.rug.oop.grapheditor.controller.buttons.*;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with the buttons for the draw-class controllers
 */

public class ButtonBar extends JMenuBar {

	/**
	 * Create a new ButtonBar with all the necessary buttons.
	 */
	public ButtonBar(GraphModel graphModel) {
		add(new FileButton(graphModel));
		add(new EditButton(graphModel));
		add(Box.createHorizontalGlue());
		add(new AddNode(graphModel));
		add(new DeleteNode(graphModel));
		add(new AddEdge(graphModel));
		add(new DeleteEdge(graphModel));
		add(Box.createHorizontalGlue());
		add(new UndoButton(graphModel));
		add(new RedoButton(graphModel));
		setBackground(new Color(186, 188, 229));
		setOpaque(true);
	}
}
