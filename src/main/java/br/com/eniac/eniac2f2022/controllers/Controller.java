package br.com.eniac.eniac2f2022.controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.github.javafaker.Faker;

import br.com.eniac.eniac2f2022.enums.OutputTypes;
import br.com.eniac.eniac2f2022.objects.WSResponse;
import br.com.eniac.eniac2f2022.utils.ObjectGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/data")
@RestController
public class Controller {
	
	@Autowired
	private SocketIOServer server;
	
	@Autowired
	private Faker faker;

	@GetMapping("/streaming/ws/{type}")
	public ResponseEntity<?> getStremWS(@Validated @PathVariable(name="type") OutputTypes type) {
		
		String uuid = UUID.randomUUID().toString();
		SocketIONamespace uniqueChannel = server.addNamespace("/"+uuid);
		uniqueChannel.addEventListener("ready", Object.class, new DataListener<Object>() {
            @Override
            public void onData(SocketIOClient client, Object data, AckRequest ackRequest) {
							log.info("Ready recived startind stream on channel: "+ uuid);
							for(int i = 0; i < 10; i++) {
								try {
									client.sendEvent("message", ObjectGenerator.generateRandomDemoObject(type, faker));
								} catch (Exception e) {
									client.sendEvent("error", e.getMessage());
								}
							}

							client.disconnect();
							server.removeNamespace("/"+uuid);
            }
        });
        
        return ResponseEntity.ok(WSResponse.builder()
        		.status("Created")
        		.statusN(200)
        		.message("Room created, waiting WS connection!")
        		.uuid(uuid)
        		.expired(false)
        		.expireIn(LocalDateTime.now().plusMinutes(5).toString())
        		.build());

		
	}
	
	
	
	
}
