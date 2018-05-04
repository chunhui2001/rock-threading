package com.rockthreading.philosopher.condition;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {

		ReentrantLock table = new ReentrantLock();

		PhilosopherCondition philosopher1 = new PhilosopherCondition("张春晖",
				table);
		PhilosopherCondition philosopher2 = new PhilosopherCondition("李莫愁",
				table);
		PhilosopherCondition philosopher3 = new PhilosopherCondition("王重阳",
				table);
		PhilosopherCondition philosopher4 = new PhilosopherCondition("赵姑娘",
				table);
		PhilosopherCondition philosopher5 = new PhilosopherCondition("孙不二",
				table);
		
		philosopher1.setLeft(philosopher5);
		philosopher1.setRight(philosopher2);

		philosopher2.setLeft(philosopher1);
		philosopher2.setRight(philosopher3);

		philosopher3.setLeft(philosopher2);
		philosopher3.setRight(philosopher4);

		philosopher4.setLeft(philosopher3);
		philosopher4.setRight(philosopher5);

		philosopher5.setLeft(philosopher4);
		philosopher5.setRight(philosopher1);

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

	}
}
