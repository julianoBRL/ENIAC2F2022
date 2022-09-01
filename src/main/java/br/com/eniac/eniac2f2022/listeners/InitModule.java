package br.com.eniac.eniac2f2022.listeners;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitModule {
    
    public InitModule(SocketIOServer server) {
    	server.addConnectListener((client) -> {onConnected(client);});
    	server.addDisconnectListener((client) -> {onDisconnected(client);});
    	server.startAsync();
    	
    	final SocketIONamespace chat2namespace = server.addNamespace("/chat2");
        chat2namespace.addEventListener("message", Object.class, new DataListener<Object>() {
            @Override
            public void onData(SocketIOClient client, Object data, AckRequest ackRequest) {
                // broadcast messages to all clients
                log.info("msg on pos start namespace");
            }
        });
        
        server.removeNamespace("/chat2");
        
    }
	
	private void onConnected(SocketIOClient client) {
			 HandshakeData handshakeData = client.getHandshakeData();
			 log.debug("Client[{}] - Connected to system through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
	}

	private void onDisconnected(SocketIOClient client) {
	        log.debug("Client[{}] - Disconnected from system.", client.getSessionId().toString());
	}
	
}
