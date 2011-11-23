package net.rolimhf.GameEssentials.Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;


public abstract class GameFrame{
	protected boolean running;
	
	protected ScreenManager s;

	public GameFrame(){
		s = new ScreenManager();
		s.setFullScreen(null);
		
	}
	
	public synchronized void stop(){
		running = false;
	}
	public void run(){
		try{
			// init stuff
			Window w = (Window) s.getFullScreenWindow();
			w.setFont(new Font("Arial", Font.PLAIN, 20));
			w.setBackground(Color.BLACK);
			w.setForeground(Color.RED);
			w.setAlwaysOnTop(true);
			
			running = true;
			//init();
			mainloop();
			//close();
		} finally {
			s.restoreScreen();
		}
	}
	
	public void mainloop(){
		long startTime = System.currentTimeMillis();
		long tickTime = startTime;
		
		while (running){
			long timePassed = System.currentTimeMillis() - tickTime;
			tickTime += timePassed;
			
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			
			g.clearRect(0, 0, s.getFullScreenWindow().getWidth(), s.getFullScreenWindow().getHeight());
			
			draw(g);
			
			g.dispose();
			s.update();
		}
	}
	
	public abstract void update(long timePassed);
	public abstract void draw(Graphics2D g);
	public abstract void init();
	public abstract void close();
}