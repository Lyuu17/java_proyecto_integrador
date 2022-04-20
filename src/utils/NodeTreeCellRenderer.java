package utils;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class NodeTreeCellRenderer extends DefaultTreeCellRenderer {
	public NodeTreeCellRenderer(String cat) {
		ImageIcon icon = new ImageIcon(NodeTreeCellRenderer.class.getResource("/categorias/" + cat + ".png"));
		setClosedIcon(icon);
		setOpenIcon(icon);
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree,
	    Object value, boolean selected, boolean expanded,
	    boolean leaf, int row, boolean hasFocus) {
	        super.getTreeCellRendererComponent(tree, value, selected,expanded, leaf, row, hasFocus);
	        
	        URL resourceURL = NodeTreeCellRenderer.class.getResource("/categorias/" + row + ".png");
	        if (resourceURL != null) {
	        	ImageIcon imageIcon = new ImageIcon(resourceURL);
	        	if (imageIcon != null) {
	        		setIcon(imageIcon);	
	        	}
	        }
	        
	        return this;
	}
}
