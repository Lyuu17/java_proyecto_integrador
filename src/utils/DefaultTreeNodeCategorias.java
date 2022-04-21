package utils;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class DefaultTreeNodeCategorias extends DefaultMutableTreeNode {
	private int UUID = 0;
	
	public DefaultTreeNodeCategorias(Object userObject, int UUID) {
		super(userObject);
		
		this.UUID = UUID;
	}
	
	public int getUUID() {
		return UUID;
	}
}
