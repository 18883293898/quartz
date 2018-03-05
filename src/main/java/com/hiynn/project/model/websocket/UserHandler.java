/**
 * 
 */
package com.hiynn.project.model.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.hiynn.project.model.service.UserService;
import com.hiynn.project.model.util.Constants;
import com.hiynn.project.model.util.ServerConfigUtil;

/**
 * <p>Title: UserHandler </p>
 * <p>Description: TODO </p>
 * Date: 2017年7月19日 下午4:52:10
 * @author loulvlin@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年7月19日         loulvlin         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class UserHandler extends TextWebSocketHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserHandler.class);
	
	@Autowired
	private UserService userService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		LOGGER.info("UserHandler is connected");
		while (true) {
			session.sendMessage(new TextMessage(Constants.COMM_GSON.toJson(userService.getAllUsers())));
			Thread.sleep(ServerConfigUtil.getServerConf().getUserInteval());
		}
	}
	
	

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("handleMessage" + message.toString());
	}



	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
	}
	
	
	
}
