package serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread {

	 static ServerSocket servidor;
		// TODO Auto-generated method stub
		public void run() {
			
			try (ServerSocket servidor = new ServerSocket(4000)) {
				System.out.println("Servidor iniciado");
				int contador = 0;
				Socket cliente;
				System.out.print("");
				while(true) {
					cliente = servidor.accept();
					contador ++;
					System.out.println("[Clientes conectados: " + contador + "]");
					HiloServidor hilo = new HiloServidor(cliente);
					hilo.start();
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
		
		
		
	

