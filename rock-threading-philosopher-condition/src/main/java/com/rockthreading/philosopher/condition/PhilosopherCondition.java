package com.rockthreading.philosopher.condition;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherCondition extends Thread {

	private String name;
	private boolean eating;
	private PhilosopherCondition left;
	private PhilosopherCondition right;
	private ReentrantLock table;
	private Condition condition;
	private Random random;
	private int eatTimes;
	
	public PhilosopherCondition(String name, ReentrantLock table) {
		this.name = name;
		this.table = table;
		this.random = new Random();
		this.eatTimes = 0;
		this.eating = false;
		this.condition = table.newCondition();
	}
	
	public void setLeft(PhilosopherCondition left) {
		this.left = left;
	}
	
	public void setRight(PhilosopherCondition right) {
		this.right = right;
	}
	
	public void run() {
		
		while (true) {
			try {
				thinking();
				eating();
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public void thinking() throws InterruptedException {
		
		this.table.lock();
		
		try {
			this.eating = false;
			this.left.condition.signal();
			this.right.condition.signal();
		} finally {
			this.table.unlock();
		}
		
		int sleep_think = this.random.nextInt(1000);

		System.out.println(ansi().fg(RED)
				.a("[" + name + "] 思考 " + sleep_think + " 毫秒").reset());
		
		Thread.sleep(this.random.nextInt(sleep_think));
		
	}
	
	public void eating() throws InterruptedException {
		
		this.table.lock();
		
		try {
			
			while (this.left.eating || this.right.eating) {
				this.condition.await();
			}
			
			this.eating = true;
		} finally {
			this.table.unlock();
		}

		int sleep_eating = this.random.nextInt(1000);
		System.out.println(ansi()
				.fg(GREEN)
				.a("[" + name + "] 进餐 " + sleep_eating
						+ " 毫秒, 第" + ++this.eatTimes + "次用餐"));
		Thread.sleep(sleep_eating);
		
	}
	
}
