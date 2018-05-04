package com.rockthreading.echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer implements Runnable {

	InputStream in;
	OutputStream out;
	
	public EchoServer(Socket socket) throws IOException {
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}
	
	public void run() {
		
		byte[] buffer = new byte[1024];
		int n = 0;
		
		try {
			while ((n = in.read(buffer)) != -1) {
				out.write(buffer, 0, n);
				out.flush();
			}
		} catch (IOException e) {
			
		} 
		
	}
	
	public static void setup(int port) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		while (true) {
			System.out.println("Your server is running on port: " + port);
			Thread handler = new Thread(new EchoServer(serverSocket.accept()));
			handler.start();
		}
		
		
	}
	
	public static void listen(int port) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		int poolSize = Runtime.getRuntime().availableProcessors() * 2;
		ExecutorService executor = Executors.newFixedThreadPool(poolSize);
		
		while (true) {
			System.out.println("Your server is running on port: " + port);
			executor.execute(new EchoServer(serverSocket.accept()));
		}
		
	}

	
}
