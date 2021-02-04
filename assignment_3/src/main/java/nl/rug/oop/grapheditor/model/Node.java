package nl.rug.oop.grapheditor.model;

import java.io.Serializable;
import java.util.Observable;

public class Node extends Observable implements Serializable {
	private  String name;
	private int uniqueID;
	private int x;
	private int y;
	private final int width;
	private final int height;


	public Node(int x, int y, int width, int height,String nodeName, int ID) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = nodeName;
		uniqueID = ID;
	}

	public Node(int uniqueId) {
		x = 0;
		y = 0;
		width = 200;
		height = 100;
		name = "New Node";
		this.uniqueID = uniqueId;
	}

	/**
	 * Getters - self explaining.
	 */
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public String getName() {
		return name;
	}

	public void setNewName(String newName) {
		this.name = newName;
	}

	public void setUniqueID() {
		this.uniqueID--;
	}

	/**
	 * Change the number of pixels this node has been moved
	 */
	public void setX(int x) {
		this.x = x;
		setChanged();
		notifyObservers();
	}

	/**
	 * Change the number of pixels this node has been moved
	 */
	public void setY(int y) {
		this.y = y;
		setChanged();
		notifyObservers();
	}
}
