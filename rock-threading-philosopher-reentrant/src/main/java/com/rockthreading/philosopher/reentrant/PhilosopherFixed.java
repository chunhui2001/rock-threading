package com.rockthreading.philosopher.reentrant;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherFixed extends Thread {

	private String name;
	private ReentrantLock left, right;
	private int eatTimes;
	private Random random;

	public PhilosopherFixed(String name, ReentrantLock left, ReentrantLock right) {
		this.name = name;
		this.left = left;
		this.right = right;
		this.random = new Random();
	}

	public void run() {

		int sleep_think = random.nextInt(1000);
		int sleep_eat = random.nextInt(1000);

		try {
			
			while (true) {

				System.out.println(ansi().fg(RED)
						.a("[" + name + "] 思考 " + sleep_think + " 毫秒").reset());
				
				Thread.sleep(sleep_think);

				left.lock();

				try {
					if (right.tryLock(1000, TimeUnit.MILLISECONDS)) {
						System.out.println(ansi()
								.fg(GREEN)
								.a("[" + name + "] 进餐 " + sleep_think
										+ " 毫秒, 第" + ++this.eatTimes + "次用餐"));
						Thread.sleep(sleep_eat);
					}
				} finally {
					left.unlock();
					
					if (right.isLocked())
						right.unlock();
				}

			}

		} catch (InterruptedException e1) {
			
		}
	}

}
