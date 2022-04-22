package app;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.UIManager;

/*
 * @see: <a href="https://www.logicbig.com/tutorials/java-swing/combo-box-icons.html">LOGICBIG</a>
 */

@SuppressWarnings("serial")
public class IdiomaRenderer extends DefaultListCellRenderer {
	  private Color background = new Color(0, 100, 255, 15);
	  private Color defaultBackground = (Color) UIManager.get("List.background");

	  @Override
	  public Component getListCellRendererComponent(JList<?> list, Object value, int index,
	                                                boolean isSelected, boolean cellHasFocus) {
	      super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	      if (this.getText() != "") {
	    	  ImageIcon icon = Idiomas.getIdiomaIcono(this.getText());
		      if (icon != null) this.setIcon(icon);
	      }

	      if (!isSelected) {
	          this.setBackground(index % 2 == 0 ? background : defaultBackground);
	      }
	      return this;
	  }
}
