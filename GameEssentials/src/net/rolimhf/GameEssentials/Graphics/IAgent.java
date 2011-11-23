package net.rolimhf.GameEssentials.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public interface IAgent {
	void onCreate(AgentScreen screen);
	void onDestroy();
	void onDraw(Graphics2D g);
	void onUpdate(long timePassed);
	void onMouse(MouseEvent event);
	void onKey(KeyEvent event);
}
