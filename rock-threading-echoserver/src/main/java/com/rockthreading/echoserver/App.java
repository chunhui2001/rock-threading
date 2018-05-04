package com.rockthreading.echoserver;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {
    	
    	
    	new Thread(new Runnable() {

			public void run() {
				try {
					EchoServer.setup(4567);
				} catch (IOException e) {
					
				}
			}
    		
    	}).start();
    	
    	new Thread(new Runnable() {

			public void run() {
				try {

			    	EchoServer.listen(4568);
				} catch (IOException e) {
					
				}
			}
    		
    	}).start();
    }
    
}
