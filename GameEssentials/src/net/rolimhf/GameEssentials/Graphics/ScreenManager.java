/**
 * THIS CLASS IS FROM thenewboston. THX FOR THE TUTORIAL.
 * Link: http://www.youtube.com/thenewboston
 */

package net.rolimhf.GameEssentials.Graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class ScreenManager {
	
	private GraphicsDevice vc;
	private boolean antialiasing;
	
	// Get Screen-Access
	protected ScreenManager(Component component) {}
	
	ScreenManager(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();
		this.antialiasing = false;
	}
	
	public DisplayMode[] getCompatibleDisplayModes(){
		return vc.getDisplayModes();
	}
	
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
		DisplayMode[] goodModes = vc.getDisplayModes();
		for (DisplayMode mode : modes)
			for (DisplayMode goodmode : goodModes)
				if (displayModesMatch(mode, goodmode))
					return mode;
		return null;
	}

	public DisplayMode getCurrentDisplayMode(){
		return vc.getDisplayMode();
	}

	private boolean displayModesMatch(DisplayMode m1, DisplayMode m2) {
		if (m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight())
			return false;
		if (m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth())
			return false;
		if (m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate())
			return false;
		return true;
	}
	
	public void setFullScreen(DisplayMode dm) {
		JFrame jframe = new JFrame();
		jframe.setUndecorated(true);
		jframe.setResizable(false);
		setFullScreen(dm, jframe);
	}
	
	/**
	 * Set it fullscreen.	
	 * @param dm
	 * @param w
	 */
	public void setFullScreen(DisplayMode dm, Window window) {
		window.setIgnoreRepaint(true);
		vc.setFullScreenWindow(window);
		
		if (dm != null && vc.isDisplayChangeSupported()) {
			try {
				vc.setDisplayMode(dm);
			} catch (Exception ex) {}
		}
		
		window.createBufferStrategy(2);
	}
	
	/**
	 * get the Graphics.
	 */
	public Graphics2D getGraphics() {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			BufferStrategy s = window.getBufferStrategy();
			Graphics2D result = (Graphics2D)s.getDrawGraphics();
			if (antialiasing) {
				result.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			}
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * Updates the Window.
	 */
	public void update() {
		Window window = vc.getFullScreenWindow();
		if (window != null){
			BufferStrategy s = window.getBufferStrategy();
			if (!s.contentsLost()){
				s.show();
			}
		}
	}
	
	/**
	 * Get the Fullscreen Window.
	 */
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}
	
	public int getWidth() {
		Window w = vc.getFullScreenWindow();
		if (w != null){
			return w.getWidth();
		} else {
			return 0;
		}
	}
	
	public int getHeight() {
		Window w = vc.getFullScreenWindow();
		if (w != null){
			return w.getHeight();
		} else {
			return 0;
		}
	}
	
	/**
	 * Restores the Screen
	 */
	public void restoreScreen() {
		Window window = vc.getFullScreenWindow();
		if (window != null)
			window.dispose();
		vc.setFullScreenWindow(null);
	}
	
	public BufferedImage createCompatibleImage(int width, int height, int alpha) {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			GraphicsConfiguration gc = window.getGraphicsConfiguration();
			return gc.createCompatibleImage(width, height, alpha);
		}
		return null;
	}
	
	public void setAntialiasing(boolean flag) {
		this.antialiasing = flag;
	}
}
