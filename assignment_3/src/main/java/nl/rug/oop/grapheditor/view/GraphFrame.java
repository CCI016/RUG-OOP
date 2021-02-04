package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.controller.ButtonBar;
import nl.rug.oop.grapheditor.controller.KeyboardController;
import nl.rug.oop.grapheditor.controller.SelectionController;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {
	public GraphFrame(GraphModel graphModel) {
		super("Graph Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphPanel graphPanel = new GraphPanel(graphModel);
		add(graphPanel);
		ButtonBar buttonBar = new ButtonBar(graphModel);
		setJMenuBar(buttonBar);
		new SelectionController(graphModel, graphPanel);
		new KeyboardController(graphModel);
		setPreferredSize(new Dimension(1280, 720));
		pack();
		graphPanel.requestFocusInWindow();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
