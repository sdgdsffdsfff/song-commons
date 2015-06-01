package com.song.commons;

import java.util.Enumeration;
import java.util.Vector;

public class Tree<T> {

	/**
	 * 上级
	 */
	private Tree<T> parent;
	
	private T entity;
	
	private Vector<Tree<T>> entityVector = new Vector<Tree<T>>();
	
	public Tree<T> getParent() {
		return parent;
	}

	public void setParent(Tree<T> parent) {
		this.parent = parent;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public void add(Tree<T> tree) {
		entityVector.addElement(tree);
	}
	
	public void remove(Tree<T> tree) {
		entityVector.removeElement(tree);
	}
	
	public Enumeration<Tree<T>> components() {
		return entityVector.elements();
	}
	
	public boolean isRoot() {
		if (parent == null) {
			return true;
		}
		return false;
	}
	
	public boolean isLeaf() {
		if (entityVector.size() == 0) {
			return true;
		}
		return false;
	}
}
