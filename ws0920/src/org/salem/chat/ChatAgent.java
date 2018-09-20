package org.salem.chat;

import java.util.Iterator;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.log4j.Log4j;


//chatagent는 클라이언트가 들어올 될때마다 하나씩 생성된다. 그래서 객체 이름이 다 다르다.
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
	
	//서버 닫기
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
