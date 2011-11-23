package net.rolimhf.GameEssentials.Graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface Scene extends KeyListener, MouseMotionListener, MouseListener {
	void onActivate(SceneGameFrame sceneGameFrame);
	void onDisable();
	void onDraw(Graphics2D g);
	void onUpdate(long timePassed);

}
