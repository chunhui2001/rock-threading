package com.rockthreading.philosopher;

import java.util.Random;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Philosopher extends Thread {

	private String name;
	private Chopstick left, right;
	private Random random;
	private int eatCount;

	public Philosopher(String name, Chopstick left, Chopstick right) {
		this.left = left;
		this.right = right;
		random = new Random();
		this.name = name;
		this.eatCount = 0;
	}

	public void run() {

		int sleep_think = 0;
		int sleep_eat = 0;

		while (true) {

			sleep_think = random.nextInt(1000);
			sleep_eat = random.nextInt(1000);

			try {

				System.out.println(ansi().fg(RED)
						.a("[" + name + "] 思考 " + sleep_think + " 毫秒").reset());

				Thread.sleep(sleep_think);

				synchronized (left) {
					synchronized (right) {
						System.out.println(ansi()
								.fg(GREEN)
								.a("[" + name + "] 进餐 " + sleep_think
										+ " 毫秒, 第" + ++this.eatCount + "次用餐")
								.reset());

						Thread.sleep(sleep_eat);
					}
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
