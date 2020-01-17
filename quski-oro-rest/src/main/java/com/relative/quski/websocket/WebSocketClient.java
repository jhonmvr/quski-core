package com.relative.quski.websocket;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ClientEndpoint
public class WebSocketClient {

	
	private static final Log log = LogFactory.getLog(WebSocketClient.class);

	

	Session userSession = null;

	public WebSocketClient() {

	}

	public WebSocketClient(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			userSession =container.connectToServer(this, endpointURI);
			log.info("===> termina Inicializando ReservaWebSocketClient "+ container );
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static WebSocketClient getInstance( String wsUrl ) {
			try {
				log.info("===> Inicializando ReservaWebSocketClient "+ wsUrl);
				return new WebSocketClient(new URI(wsUrl));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
	}

	@OnOpen
	public void onOpen(Session userSession) {
		log.info("=====>client: opening websocket ");
		this.userSession = userSession;
	}

	/**
	 * Callback hook for Connection close events.
	 *
	 * @param userSession
	 *            the userSession which is getting closed.
	 * @param reason
	 *            the reason for connection close
	 * @throws IOException 
	 */
	@OnClose
	public void onClose( ) throws IOException {
		log.info("client: closing websocket");
		this.userSession = null;
	}

	/**
	 * Callback hook for Message Events. This method will be invoked when a client
	 * send a message.
	 *
	 * @param message
	 *            The text message
	 */
	@OnMessage
	public void onMessage(String message) {
		log.info("client: received message " + message);
	}

	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message);
	}
}
