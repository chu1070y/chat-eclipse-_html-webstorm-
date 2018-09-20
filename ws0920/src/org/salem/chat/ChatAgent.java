package org.salem.chat;

import java.util.Iterator;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.log4j.Log4j;


//chatagent�� Ŭ���̾�Ʈ�� ���� �ɶ����� �ϳ��� �����ȴ�. �׷��� ��ü �̸��� �� �ٸ���.
@ServerEndpoint("/chat")
@Log4j
public class ChatAgent {
	
	private Session session;
	private String userIP;
	
	@OnOpen
	public void open(Session session) {
		
		log.info(userIP + "connected...............");
		log.info(session);
	    this.session = session;
	    
//	    userIP = session.getUserProperties()
//	    .get("javax.websocket.endpoint.remoteAddress").toString();
	    
	    
	    
		log.info(this);
		ChatManager.INSTANCE.broadcast(userIP + "connected..............."); 
		ChatManager.INSTANCE.addAgent(this); 

		
	}
	
	//���� �ݱ�
	@OnClose
	public void close() {
		log.info("a user disconnected");
		ChatManager.INSTANCE.broadcast(userIP + "disconnected...............");
	}
	
	@OnMessage
	public void onMessage(String msg)throws Throwable{
		log.info("send message...............");
		
		ChatManager.INSTANCE.broadcast(userIP +msg);
	}

	public void sendMsg(String msg) throws Exception{
		
		session.getBasicRemote().sendText(msg);
				
	}
	

}
