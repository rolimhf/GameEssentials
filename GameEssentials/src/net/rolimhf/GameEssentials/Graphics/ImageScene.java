package net.rolimhf.GameEssentials.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;

public class ImageScene extends SimpleScene {
	long lastPicShown;
	boolean showNext;
	boolean valid = true;
	int index = -1;
	int interval = -1;
	Image[] images;
	Scene nextScene;
	SceneGameFrame gameFrame;
	
	public ImageScene(Image[] images, int interval, Scene nextScene){
		this.interval = interval;
		this.images = images;
		this.nextScene = nextScene;
		this.valid = true;
	}
	
	public ImageScene(Object source, String[] resources, int interval, Scene nextScene) {
		this.interval = interval;
		this.nextScene = nextScene;

		try {
			this.images = new Image[resources.length];
			for (int i = 0; i<resources.length; i++) {
				images[i] = ImageIO.read(source.getClass().getResource(resources[i]));
			}
		} catch (Exception e) {
			this.valid = false;
			e.printStackTrace();
			return;
		}
		
		if (!valid)
			this.valid = true;
	}
	
	@Override
	public void onActivate(SceneGameFrame sceneGameFrame) {
		gameFrame = sceneGameFrame;

	}

	public void onDisable() {}

	@Override
	public void onDraw(Graphics2D g) {
		try {
			GraphicHelper.drawCenteredImage(gameFrame.getWindow(), g, images[index]);
		} catch (Exception e) {
			
		}
		showNext = false;
	}

	@Override
	public void onUpdate(long timePassed) {
		if (!valid) {
			gameFrame.setScene(nextScene);
			return;
		}
		
		if (lastPicShown == -1 || System.currentTimeMillis() - lastPicShown > interval) {
			index++;
			if (index >= images.length) {
				gameFrame.setScene(nextScene);
				return;
			}
			lastPicShown = System.currentTimeMillis();
			showNext = true;
		}

	}

	@Override
	public void onMouse(MouseEvent evt) {
		if (evt.getID() == MouseEvent.MOUSE_PRESSED) {
			gameFrame.setScene(nextScene);
		}
	}

	@Override
	public void onKey(KeyEvent evt) {
		if (evt.getID() == KeyEvent.KEY_RELEASED) {
			if (evt.getKeyCode() == KeyEvent.VK_ESCAPE || evt.getKeyCode() == KeyEvent.VK_SPACE)
				gameFrame.setScene(nextScene);
		}
	}

}
