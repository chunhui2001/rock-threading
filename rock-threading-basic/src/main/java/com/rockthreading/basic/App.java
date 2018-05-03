package com.rockthreading.basic;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BasicThread basicThread = new BasicThread();
    	
    	// basicThread.hello(" thread 2 ########");
    	// basicThread.hello(" thread 1 ***");
    	

    	basicThread.count(" Count Increment 3 ########");
    	basicThread.count(" Count Increment 4 ***");
    	
    }
}
