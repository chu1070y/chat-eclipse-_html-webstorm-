package org.salem.chat;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public enum ChatManager {
	
	INSTANCE;
	
	Set <ChatAgent> agentSet;
	
	ChatManager(){
		agentSet = new CopyOnWriteArraySet<>();
	}
	
	public void addAgent(ChatAgent agent) {
		agentSet.add(agent);
	}
	
	public void broadcast(String msg) {
		
		Iterator<ChatAgent> it = agentSet.iterator();
		
		while(it.hasNext()) {
			
			ChatAgent agent = it.next();
			try {
				agent.sendMsg(msg);
			} catch (Exception e) {
				agentSet.remove(agent);
				e.printStackTrace();
			}
			
		}
		
	}
	
	

}
