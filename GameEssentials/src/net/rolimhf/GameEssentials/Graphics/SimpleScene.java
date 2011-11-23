package net.rolimhf.GameEssentials.Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class SimpleScene implements Scene {
	
	public void keyPressed(KeyEvent arg0) {
		onKey(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		onKey(arg0);
	}

	public void keyTyped(KeyEvent arg0) {} // IGNORE IT
	public void mouseDragged(MouseEvent arg0) {} // IGNORE IT
	
	public void mouseMoved(MouseEvent arg0) {
		onMouse(arg0);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {} // IGNORE IT
	public void mouseEntered(MouseEvent arg0) {} // IGNORE IT
	public void mouseExited(MouseEvent arg0) {} // IGNORE IT

	@Override
	public void mousePressed(MouseEvent arg0) {
		onMouse(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		onMouse(arg0);
	}
	
	public abstract void onMouse(MouseEvent evt);
	public abstract void onKey(KeyEvent evt);
}
