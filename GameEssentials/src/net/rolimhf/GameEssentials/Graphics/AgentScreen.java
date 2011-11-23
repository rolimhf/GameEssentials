package net.rolimhf.GameEssentials.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AgentScreen extends SimpleScene {
	public SceneGameFrame sgf;
	protected ArrayList<IAgent> agents =  new ArrayList<IAgent>();

	@Override
	public void onDisable() {
		for (int index = 0; index<agents.size(); index++) {
			removeAgent(agents.get(index));
		}
	}

	@Override
	public void onDraw(Graphics2D g) {
		for (int index = 0; index<agents.size(); index++) {
			agents.get(index).onDraw(g);
		}
	}

	@Override
	public void onUpdate(long timePassed) {
		for (int index = 0; index<agents.size(); index++) {
			agents.get(index).onUpdate(timePassed);
		}
	}

	@Override
	public void onMouse(MouseEvent evt) {
		for (int index = 0; index<agents.size(); index++) {
			agents.get(index).onMouse(evt);
		}
	}

	@Override
	public void onKey(KeyEvent evt) {
		for (int index = 0; index<agents.size(); index++) {
			agents.get(index).onKey(evt);
		}	
	}
	
	/**
	 * Registers a Agent.
	 * @param agent
	 */
	protected void addAgent(IAgent agent) {
		agents.add(agent);
		agent.onCreate(this);
	}
	
	/**
	 * Deletes an agent.
	 * @param agent
	 */
	protected void removeAgent(IAgent agent) {
		agent.onDestroy();
		agents.remove(agent);
	}

	@Override
	public void onActivate(SceneGameFrame sceneGameFrame) {
		sgf = sceneGameFrame;
		
	}
}
