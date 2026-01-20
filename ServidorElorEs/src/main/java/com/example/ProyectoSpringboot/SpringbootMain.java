package com.example.ProyectoSpringboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import serversocket.SocketServer;



@SpringBootApplication
public class SpringbootMain {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMain.class, args);
		SocketServer socketserver = new SocketServer();
		socketserver.start();

	}

}
