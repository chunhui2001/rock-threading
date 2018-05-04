package com.rockthreading.concurrentsortedlist;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSortedList {

	public class Node {

		int value;
		Node prev;
		Node next;
		ReentrantLock lock = new ReentrantLock();

		Node() {

		}

		Node(int value, Node prev, Node next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}

	}

	private final Node head;
	private final Node tail;

	public ConcurrentSortedList() {
		this.head = new Node();
		this.tail = new Node();

		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	public void insert(int value) {

		Node current = this.head;
		current.lock.lock();
		Node next = current.next;

		try {
			while (true) {

				next.lock.lock();
				
				current = next;
				next = current.next;

			}
		} finally {
			next.lock.unlock();
		}

	}

}
