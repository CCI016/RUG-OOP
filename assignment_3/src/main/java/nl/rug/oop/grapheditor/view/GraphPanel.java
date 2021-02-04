package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.controller.KeyboardController;
import nl.rug.oop.grapheditor.model.Edge;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {
	private static final Color BACKGROUND_COLOR = new Color(207, 208, 208);
	private static final Color DARK_COLOR = new Color(28, 27, 27);
	private final GraphModel graphModel;

	public GraphPanel(GraphModel graphModel) {
		setBackground(BACKGROUND_COLOR);
		setVisible(true);
		setOpaque(true);
		this.graphModel = graphModel;
		addKeyListener(new KeyboardController(graphModel));
		graphModel.addObserver(this);

	}

	public double getRatioX(){
		int startX = 1280;
		return (double)getWidth()/ startX;
	}

	public double getRatioY(){
		int startY = 720;
		return (double)getHeight()/ startY;
	}

	/**
	 * This function will draw all the nodes
	 */
	public void paintNodes(Graphics g) {
		Node nodeSelected = graphModel.getSelectedNode();

		if (graphModel.isDarkModeButtonPressed()) {
			setBackground(DARK_COLOR);
		}

		for (int i = 0; i < graphModel.getNodesNr(); i++) {
			Node node = graphModel.getNode(i);

			/* If a node is selected than make a yellow border for it*/
			if (nodeSelected == node) {
				g.setColor(new Color(225, 241, 4));
				g.drawRect((int) ((nodeSelected.getX() - 5) * getRatioX()),
						(int) ((nodeSelected.getY() - 5) * getRatioY()),
						(int) ((nodeSelected.getWidth() + 10) * getRatioX()),
						(int) ((nodeSelected.getHeight() + 10) * getRatioY()));
				g.fillRect((int) ((nodeSelected.getX() - 5) * getRatioX()),
						(int) ((nodeSelected.getY() - 5) * getRatioY()),
						(int) ((nodeSelected.getWidth() + 10) * getRatioX()),
						(int) ((nodeSelected.getHeight() + 10) * getRatioY()));
				g.setColor(new Color(88, 6, 75));
			} else {
				g.setColor(new Color(50, 5, 37));
			}
			if (nodeSelected == node && graphModel.isDarkModeButtonPressed()) {
				g.setColor(new Color(168, 108, 5));
			} else if (nodeSelected != node && graphModel.isDarkModeButtonPressed() ) {
				g.setColor(new Color(108, 68, 3));
			}

			g.drawRect((int) (node.getX() * getRatioX()), (int) (node.getY() * getRatioY()), (int) (node.getWidth() * getRatioX()), (int) (node.getHeight() * getRatioY()));
			g.fillRect((int) (node.getX() * getRatioX()), (int) (node.getY() * getRatioY()), (int) (node.getWidth() * getRatioX()), (int) (node.getHeight() * getRatioY()));

			int textSize = 1;
			g.setFont(new Font("Monospaced", Font.BOLD, textSize));
			int textWidth = g.getFontMetrics().stringWidth(node.getName());
			int textHeight = g.getFontMetrics().getHeight();

			/*This while determines the optimal font size for the name to be only inside the rectangle */
			while ((textWidth < (3 * node.getWidth()) / 4 ) && (textHeight < ((node.getHeight()) / 2))) {
				textSize += 1;
				g.setFont(new Font("Monospaced", Font.BOLD, textSize));
				textWidth = (int) (g.getFontMetrics().stringWidth(node.getName()) / getRatioX());
				textHeight = (int) (g.getFontMetrics().getHeight() / getRatioY());
			}

			g.setColor(Color.white);
			int startingX = node.getX() + (node.getWidth() - textWidth) / 2;
			int startingY = node.getY() + (node.getHeight() - (node.getHeight() - textHeight) / 2);
			g.drawString(node.getName(), (int) (startingX * getRatioX()), (int) (startingY * getRatioY()));
		}
	}

	/**
	 * Draws an edge that connects 2 nodes
	 */
	public void paintEdges(Graphics g) {
		for (Edge edge: graphModel.getEdges()) {
			g.setColor(new Color(135, 10, 73));
			Node node1 = edge.getFirstNode();
			Node node2 = edge.getSecondNode();

			// Values of the center's of each rectangle
			int xMidNode1 = (node1.getWidth() / 2) + node1.getX();
			int xMidNode2 = (node2.getWidth() / 2) + node2.getX();
			int yMidNode1 = (node1.getHeight() / 2) + node1.getY();
			int yMidNode2 = (node2.getHeight() / 2) + node2.getY();

			Graphics2D g2d = (Graphics2D) g;  /* used to make the edge's line look more thick */
			g2d.setStroke(new BasicStroke(5));
			g.drawLine((int) (xMidNode1 * getRatioX()), (int) (yMidNode1 * getRatioY()), (int) (xMidNode2 * getRatioX()), (int) (yMidNode2 *getRatioY()));
		}
	}

	/**
	 * Paint a line, starting at a Node an ending at mouse location
	 */
	public void paintAddEdge(Graphics g) {
		if (graphModel.getSelectedNode() == null && graphModel.isAddEdgeButtonPressed()) {
			g.setColor(new Color(135, 10, 73));
			Node node1 = graphModel.getFirstSelectedNode();
			int xMidNode1 = (node1.getWidth() / 2) + node1.getX();
			int yMidNode1 = (node1.getHeight() / 2) + node1.getY();

			Graphics2D g2d = (Graphics2D) g; /* used to make the edge's line look more thick */
			g2d.setStroke(new BasicStroke(5));
			g.drawLine(xMidNode1, yMidNode1, graphModel.getMouseLocationX(), graphModel.getMouseLocationY());
		}
	}

	/**
	 * Paints the text box, where the user inputs a text for the name of a node
	 */
	public void paintTextBox() {
		if (graphModel.isRenameButtonPressed() && graphModel.getSelectedNode() != null) {
			JTextField textField = new JTextField();
			int startX = (getWidth() - 150);
			int startY = (getHeight() - getHeight() + 50);
			textField.setBounds(startX, startY, 150, 50);
			textField.setMargin(new Insets(5, 5, 5, 5));
			textField.setToolTipText("<html><b><font color=red>"
					+ "Renames the selected node." + "</font></b></html>");
			add(textField);

			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					super.keyPressed(e);
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (graphModel.isRenameButtonPressed()) {
							graphModel.renameSelectedNode(textField.getText(), graphModel.getSelectedNode());
							textField.setEnabled(false);
						}
					}
				}
			});
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintAddEdge(g);
		paintEdges(g);
		paintNodes(g);
		paintTextBox();
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
