package net.rolimhf.GameEssentials.Graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;

import net.rolimhf.GameEssentials.Resources.*;

public class SceneGameFrame extends GameFrame {
	protected Scene currentScene;
	protected Image background;
	protected boolean useBackground;
	
	public SceneGameFrame(){
		super();
		setScene(null);
	}
	
	@Override
	public void draw(Graphics2D g) {
		currentScene.onDraw(g);
	}

	@Override
	public void update(long timePassed) {
		currentScene.onUpdate(timePassed);
		
	}

	@Override
	public void init() {
		currentScene = new NullScene();
		
	}
	
	public void mainloop(){
		long startTime = System.currentTimeMillis();
		long tickTime = startTime;
		
		getGraphics().clearRect(0, 0, getWindow().getWidth(), getWindow().getHeight());
		s.update();
		
		while (running){
			long timePassed = System.currentTimeMillis() - tickTime;
			tickTime += timePassed;
			
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			clearScreen();
			draw(g);
			
			g.dispose();
			s.update();
			
		}

	}
	
	public void setScene(Scene scene){
		Scene oldScene = currentScene;
		if (currentScene != null){
			currentScene.onDisable();
		}
		if (scene!=null)
			currentScene = scene;
		else
			currentScene = new NullScene();
		setListeners(currentScene, oldScene);
		setBackground(null);
		currentScene.onActivate(this);
		
		clearScreen();
		s.update();
		
	}
	
	protected void setListeners(Scene n, Scene o){
		Component w = (Component) s.getFullScreenWindow();
		
		if (o!=null){
			w.removeMouseMotionListener(o);
			w.removeKeyListener(o);
			w.removeMouseListener(o);
		}
		
		
		w.addMouseListener(n);
		w.addMouseMotionListener(n);
		w.addKeyListener(n);
	}
	
	public Frame getWindow(){
		return (Frame) s.getFullScreenWindow();
	}
	
	public Graphics2D getGraphics(){
		return s.getGraphics();
	}

	public void updateGraphics() {
		s.update();
		
	}

	@Override
	public void close() {}

	public void clearScreen() {
		//Delete Screen
		getGraphics().clearRect(0, 0, getWindow().getWidth(), getWindow().getHeight());
		if (useBackground) {
			GraphicHelper.drawCenteredImage(getWindow(), getGraphics(), background);
		}
	}
	
	public void setBackground(Image background){
		useBackground = background != null;
		if (background != null) {
			this.background = background;
		}
	}
	
	public void setBackground(Object source, String resource) {
		useBackground = resource != null;
		if (resource != null)
			background = ResourceHelper.getResourceImage(source.getClass(), resource);
	}

	public void setAliasing(boolean b) {
		s.setAntialiasing(!b);
	}

	public void clearScreen(Color c) {
		Graphics2D g = getGraphics();
		Color old = g.getBackground();
		g.setBackground(c);
		g.clearRect(0, 0, getWindow().getWidth(), getWindow().getHeight());
		if (useBackground) {
			g.drawImage(background, 0, 0, getWindow());
		}
		g.setBackground(old);
	}

}
