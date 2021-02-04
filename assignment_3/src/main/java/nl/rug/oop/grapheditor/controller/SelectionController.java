package nl.rug.oop.grapheditor.controller;

import nl.rug.oop.grapheditor.controller.edits.MoveEdit;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.Node;
import nl.rug.oop.grapheditor.view.GraphPanel;

import javax.swing.undo.UndoManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionController extends MouseAdapter {
	private final GraphModel graphModel;
	private boolean selected;
	private boolean check;

	/**
	 * Constructor
	 *
	 * @param graphModel The Graph Model
	 */
	public SelectionController(GraphModel graphModel, GraphPanel graphPanel) {
		this.graphModel = graphModel;
		graphPanel.addMouseListener(this);
		graphPanel.addMouseMotionListener(this);
		selected = false;
		check = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (Node node : graphModel.getNodes()) {
			if (e.getX() >= node.getX() &&
					e.getX() <= (node.getX() + node.getWidth()) &&
					e.getY() >= node.getY() && e.getY() <= (node.getY() + node.getHeight())) {
				selected = true;
				check = false;
				graphModel.setSelectedNode(node);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		selected = false;
		check = false;
		graphModel.setSelectedNodeNull();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		if (graphModel.getSelectedNode() == null && graphModel.isAddEdgeButtonPressed()) {
			graphModel.setMouseLocation(e.getX() , e.getY());
		}
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		super.mouseDragged(event);
		if (selected && !check) {
			UndoManager undoManager = graphModel.getUndoManager();
			MoveEdit moveEdit = new MoveEdit(graphModel);
			undoManager.addEdit(moveEdit);
			check = true;
		}
			if (selected) {
				graphModel.move(event.getX(),
						event.getY());
			}
	}
}
