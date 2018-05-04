package com.rockthreading.philosopher;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.RED;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {

		Chopstick[] chopstickArray = new Chopstick[5];

		for (int i = 0; i < 5; i++) {
			chopstickArray[i] = new Chopstick(i + 1);
		}

		Philosopher philosopher1 = new Philosopher("张春晖", chopstickArray[0],
				chopstickArray[1]);
		Philosopher philosopher2 = new Philosopher("李莫愁", chopstickArray[1],
				chopstickArray[2]);
		Philosopher philosopher3 = new Philosopher("王重阳", chopstickArray[2],
				chopstickArray[3]);
		Philosopher philosopher4 = new Philosopher("赵姑娘", chopstickArray[3],
				chopstickArray[4]);
		Philosopher philosopher5 = new Philosopher("孙不二", chopstickArray[4],
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
