package com.relative.midas.websocket;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.relative.quski.wrapper.MessageWrapper;

@ServerEndpoint("/midasws/{hash}")
public class WebSocket {

	
	@Inject
	Logger log;

	static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(Session session) {
		log.info("cliente conectadooo " + session.getId());
		peers.add(session);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			Gson gson = new Gson();
			MessageWrapper mw = gson.fromJson(message, MessageWrapper.class);
			log.info("received message from client getFechaDesde " + mw.getFecha());
			log.info("received message from client getCodigo " + mw.getCodigo());
			log.info("received message from client getMensaje " + mw.getMensaje());
			log.info("received message from client getId " + session.getId());
			log.info("received message from client session reveiver " + mw.getSessionIdReceiver());
			log.info("received message from client hash origen " + mw.getHash());
			log.info("received message from client peers " + peers);
			for (Session peer : peers) {				
					peer.getBasicRemote().sendText(gson.toJson(mw));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		try {
			log.info("cliente desconectado " + session.getId());
			peers.remove(session);
			// notify peers about leaving the chat room
			for (Session peer : peers) {
				MessageWrapper message = new MessageWrapper();
				Gson gson = new Gson();
				message.setCodigo("Server");
				message.setMensaje("%s left the chat room" + (String) session.getUserProperties().get("user"));
				message.setFecha(new Date());
				peer.getBasicRemote().sendText(gson.toJson(message));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
