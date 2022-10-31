package br.com.eniac.eniac2f2022.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corundumstudio.socketio.SocketIOServer;

@RequestMapping("/rooms")
@RestController
public class WSController {
	
	@Autowired
	private SocketIOServer server;
	
	@GetMapping
	public List<String> ListRooms(){
		List<String> rooms = new ArrayList<>();
		 server.getAllNamespaces().forEach((namespace)->{
			System.out.println();
			rooms.add(namespace.getName());
		});;
		return rooms;
	}
	
}
