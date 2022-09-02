package br.com.eniac.eniac2f2022;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

@Component
public class WSInitializer {
	
	@Value("${rt-server.host}")
    private String host;

    @Value("${rt-server.port}")
    private Integer port;

    @Bean
    SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        return new SocketIOServer(config);
    }
    
}
