package nl.rug.oop.grapheditor.controller;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
	private final GraphModel graphModel;

	/**
	 * Constructor
	 *
	 * @param graphModel The Graph Model
	 */
	public KeyboardController(GraphModel graphModel) {
		this.graphModel = graphModel;
	}

	/**
	 * Method that is called when a key is pressed
	 *
	 * @param e contains all the necessary information about the key that was pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		Node selectedNode = graphModel.getSelectedNode();
			int x = selectedNode.getX();
			int y = selectedNode.getY();

			/* Switch through the input key */
			int MOVE_SPEED = 20;
			switch (e.getKeyCode()) {

				case KeyEvent.VK_RIGHT:
					graphModel.move(x + MOVE_SPEED, y);
					break;

				case KeyEvent.VK_LEFT:
					graphModel.move(x - MOVE_SPEED, y);
					break;

				case KeyEvent.VK_DOWN:
					graphModel.move(x, y + MOVE_SPEED);
					break;

				case KeyEvent.VK_UP:
					graphModel.move(x, y - MOVE_SPEED);
					break;
			}
		}
	}
