package utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * 
 * @see: <a href="https://stackoverflow.com/a/18335435>StackOverflow</a>
 *
 */
@SuppressWarnings("serial")
public class ImageIconResize extends ImageIcon {
	public ImageIconResize(URL url) {
		super(url);
	}
	
	public ImageIconResize(Image image) {
		super(image);
	}
	
	public ImageIconResize getScaledInstance(int x, int y, int scale) {
		Image newImg = getImage().getScaledInstance(x, y, scale);
		return new ImageIconResize(newImg);
	}
}
