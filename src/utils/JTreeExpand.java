package utils;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class JTreeExpand extends JTree {
	public JTreeExpand(DefaultMutableTreeNode node) {
		super(node);
	}

	public void expandTree(boolean expand) {
        TreeNode root = (TreeNode) getModel().getRoot();
        expandAll(new TreePath(root), expand);
    }

    @SuppressWarnings("rawtypes")
	public void expandAll(TreePath path, boolean expand) {
        TreeNode node = (TreeNode) path.getLastPathComponent();

        if (node.getChildCount() >= 0) {
            Enumeration enumeration = node.children();
            while (enumeration.hasMoreElements()) {
                TreeNode n = (TreeNode) enumeration.nextElement();
                TreePath p = path.pathByAddingChild(n);

                expandAll(p, expand);
            }
        }

        if (expand) {
            expandPath(path);
        } else {
            collapsePath(path);
        }
    }
}
