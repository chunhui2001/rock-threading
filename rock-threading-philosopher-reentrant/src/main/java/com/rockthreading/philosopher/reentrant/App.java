package com.rockthreading.philosopher.reentrant;

import java.util.concurrent.locks.ReentrantLock;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	ReentrantLock[] chopstickArray = new ReentrantLock[5];

		for (int i = 0; i < 5; i++) {
			chopstickArray[i] = new ReentrantLock();
		}

		PhilosopherFixed philosopher1 = new PhilosopherFixed("张春晖", chopstickArray[0],
				chopstickArray[1]);
		PhilosopherFixed philosopher2 = new PhilosopherFixed("李莫愁", chopstickArray[1],
				chopstickArray[2]);
		PhilosopherFixed philosopher3 = new PhilosopherFixed("王重阳", chopstickArray[2],
				chopstickArray[3]);
		PhilosopherFixed philosopher4 = new PhilosopherFixed("赵姑娘", chopstickArray[3],
				chopstickArray[4]);
		PhilosopherFixed philosopher5 = new PhilosopherFixed("孙不二", chopstickArray[4],
				chopstickArray[0]);

		Thread.yield();

		philosopher1.start();
		philosopher2.start();
		philosopher3.start();
		philosopher4.start();
		philosopher5.start();

		philosopher1.join();
		philosopher2.join();
		philosopher3.join();
		philosopher4.join();
		philosopher5.join();

		System.out.println("Hello World!");
    }
}
