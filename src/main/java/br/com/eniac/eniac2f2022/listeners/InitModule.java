package br.com.eniac.eniac2f2022.listeners;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitModule {
    
    public InitModule(SocketIOServer server) {
    	server.addConnectListener((client) -> {onConnected(client);});
    	server.addDisconnectListener((client) -> {onDisconnected(client);});
    	server.startAsync();        
    }
	
	private void onConnected(SocketIOClient client) {
			 HandshakeData handshakeData = client.getHandshakeData();
			 log.debug("Client[{}] - Connected to system through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
	}

	private void onDisconnected(SocketIOClient client) {
	        log.debug("Client[{}] - Disconnected from system.", client.getSessionId().toString());
	}
	
}
